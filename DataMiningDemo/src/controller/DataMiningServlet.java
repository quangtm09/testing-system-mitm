package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DimStudents;
import model.DimSubjectGroups;
import model.DimSubjects;
import model.DimTimes;
import model.FactStudentGrades;

import org.apache.log4j.Logger;

import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import apriori.web.Apriori;
import apriori.web.Rule;
import constant.DMConstant;
import dao.DimStudentsDao;
import dao.DimSubjectGroupsDao;
import dao.DimSubjectsDao;
import dao.DimTimesDao;
import dao.FactStudentGradesDao;
import dao.impl.DimStudentsDaoImpl;
import dao.impl.DimSubjectGroupsDaoImpl;
import dao.impl.DimSubjectsDaoImpl;
import dao.impl.DimTimesDaoImpl;
import dao.impl.FactStudentGradesDaoImpl;

/**
 * Servlet implementation class DataMiningServlet
 */
@WebServlet(
		description = "Data Mining Servlet",
		urlPatterns = {
				"/DataMiningServlet"
		})
public final class DataMiningServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Logger log = Logger.getLogger(DataMiningServlet.class);

	private final static DimStudentsDao studentDao = new DimStudentsDaoImpl();
	private final static DimSubjectsDao subjectDao = new DimSubjectsDaoImpl();
	private final static DimTimesDao timeDao = new DimTimesDaoImpl();
	private final static DimSubjectGroupsDao subjectGroupDao = new DimSubjectGroupsDaoImpl();
	private final static FactStudentGradesDao factStudentGradesDao = new FactStudentGradesDaoImpl();

	private static List<Rule> ruleList = new ArrayList<Rule>();

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final int metricType = Integer.parseInt(request.getParameter("rankingType"));
        final double lowerMinSup = Double.parseDouble(request.getParameter("lowerMinSup"));
        final double upperMinSup = Double.parseDouble(request.getParameter("upperMinSup"));
        final int numRules = Integer.parseInt(request.getParameter("numRules"));
        final double metricScore = Double.parseDouble(request.getParameter("metricScore"));
        final int firstYear = Integer.parseInt(request.getParameter("from"));
        final int secondYear = Integer.parseInt(request.getParameter("to"));

        final List<DimSubjects> subjectList = subjectDao.findAll();
		final List<String> subjectNameList = new ArrayList<String>();

		List<DimStudents> students = null;

		// Get List of unique subject string
		for (final DimSubjects subject : subjectList) {
			final String subjectName = subject.getSubjectName();
			final DimSubjectGroups subjectGroup = subject.getDimSubjectGroups();
			String subjectGroupName = null;

			if (null != subjectGroup) {
				subjectGroupName = subjectGroup.getSubjectGroupName();
			}

			String toAdd = "";

			if (subjectGroupName == null || subjectGroupName.equals("")) {
				toAdd = subjectName;
			} else {
				toAdd = subjectGroupName;
			}

			if (!subjectNameList.contains(toAdd)) {
				subjectNameList.add(toAdd);
			}
		}

		// Add attribute value for each attribute (subject)
		final FastVector fastVector = new FastVector(9);
		fastVector.addElement("F");
		fastVector.addElement("D");
		fastVector.addElement("D+");
		fastVector.addElement("C");
		fastVector.addElement("C+");
		fastVector.addElement("B");
		fastVector.addElement("B+");
		fastVector.addElement("A-");
		fastVector.addElement("A");

		final FastVector fVector = new FastVector(subjectList.size());

		for (final String subjectName : subjectNameList) {
			final Attribute attribute = new Attribute(subjectName, fastVector);
			fVector.addElement(attribute);
		}

		// Declare how many instances to be generated large item set
		final Instances instances = new Instances("Courses", fVector,
				factStudentGradesDao.count());

		// If student in the same year
		if (firstYear == secondYear) {
			students = studentDao.getStudentsByAcademicYear(firstYear);
			for (final DimStudents studentData : students) {

				final List<FactStudentGrades> gradeList = factStudentGradesDao
						.getFactStudentGradeByStudent(studentData);
				final Instance instance = new Instance(fVector.size());

				for (final FactStudentGrades grade : gradeList) {
					final DimSubjects subject = grade.getDimSubjects();
					final DimSubjectGroups subjectGroup = subject
							.getDimSubjectGroups();
					String subjectGroupName = null;

					if (subjectGroup != null) {
						subjectGroupName = subjectGroup.getSubjectGroupName();
					}

					String attributeName = "";

					if (subjectGroupName == null || subjectGroupName.equals("")) {
						attributeName = subject.getSubjectName();
					} else {
						attributeName = subjectGroupName;
					}

					final Attribute attribute = instances
							.attribute(attributeName);
					instance.setValue(attribute,
							grade.getSummarizedGradeLetter());
				}
				instances.add(instance);
			}
		} else {

			for (int i = firstYear; i <= secondYear; i++) {
				final List<DimTimes> years = timeDao.getDimTimeByYear(i);

				if (null != years && years.size() > 0) {
					students = studentDao.getStudentsByAcademicYear(i);
					for (final DimStudents studentData : students) {
						final List<FactStudentGrades> gradeList = factStudentGradesDao
								.getFactStudentGradeByStudent(studentData);
						final Instance instance = new Instance(fVector.size());

						for (final FactStudentGrades grade : gradeList) {
							final DimSubjects subject = grade.getDimSubjects();
							final DimSubjectGroups subjectGroup = subject
									.getDimSubjectGroups();
							String subjectGroupName = null;

							if (subjectGroup != null) {
								subjectGroupName = subjectGroup
										.getSubjectGroupName();
							}

							String attributeName = "";

							if (subjectGroupName == null
									|| subjectGroupName.equals("")) {
								attributeName = subject.getSubjectName();
							} else {
								attributeName = subjectGroupName;
							}

							final Attribute attribute = instances
									.attribute(attributeName);
							instance.setValue(attribute,
									grade.getSummarizedGradeLetter());
						}
						instances.add(instance);
					} // End for
				} // End if
			} // End for
		} // End else

		final File arffFile = new File("test.arff");

		if (arffFile.exists()) {
			arffFile.delete();
		}

		final ArffSaver saver = new ArffSaver();
		saver.setInstances(instances);
		try {
			saver.setFile(arffFile);
			saver.writeBatch();
		} catch (final IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		response.setContentType("text/html");
		final PrintWriter printWriter = response.getWriter();


		final Apriori apriori = new Apriori();
		apriori.setMetricType(metricType);
		apriori.setNumRules(numRules);
		apriori.setLowerBoundMinSupport(lowerMinSup);
		apriori.setUpperBoundMinSupport(upperMinSup);
		apriori.setDelta(0.01);
		apriori.setMinMetric(metricScore);


		try {
			apriori.buildAssociations(instances);
		} catch (final Exception e) {
			printWriter.print("<div class=\"alert alert-danger\">Error while building associations! Please try again!</div>");
		}

		apriori.addRulesToList();

		ruleList = apriori.getRuleList();

		final Map map = new HashMap();
        map.put("ruleList", ruleList);
        map.put("isSearching", false);
        map.put("numStudents", instances.numInstances());
        map.put("numSubjects", instances.numAttributes());
        map.put("metricType", metricType);

        request.setAttribute("map", map);

		try {
			this.goToPage(DMConstant.RULE_JSP, request, response);
		} catch (final ServletException e) {
			printWriter.print("<div class=\"alert alert-danger\">Error while loading results! Please try again!</div>");
		}
	}

    public static List<Rule> getRuleList() {
		return ruleList;
	}

	/**
     * @see HttpServlet#HttpServlet()
     */
    public DataMiningServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doGet(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@Override
	protected void doPost(final HttpServletRequest request, final HttpServletResponse response) throws ServletException, IOException {
		this.processRequest(request, response);
	}

	public void goToPage(final String page, final HttpServletRequest request,
			final HttpServletResponse response) throws ServletException,
			IOException {
		final RequestDispatcher dispatcher = this.getServletContext()
				.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

}
