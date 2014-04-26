package controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.DimStudents;
import model.DimSubjectGroups;
import model.DimSubjects;
import model.DimTimes;
import model.FactStudentGrades;
import weka.core.Attribute;
import weka.core.FastVector;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import apriori.console.Apriori;
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

public class DataMiningMain {

	private final static DimStudentsDao studentDao = new DimStudentsDaoImpl();
	private final static DimSubjectsDao subjectDao = new DimSubjectsDaoImpl();
	private final static DimTimesDao timeDao = new DimTimesDaoImpl();
	private final static DimSubjectGroupsDao subjectGroupDao = new DimSubjectGroupsDaoImpl();
	private final static FactStudentGradesDao factStudentGradesDao = new FactStudentGradesDaoImpl();

	/**
	 * @param args
	 */
	@SuppressWarnings("unused")
	public static void main(final String[] args) {
		/**
		 * Metric type
		 *
		 * 0: Confidence
		 * 1: Lift
		 * 2: Leverage
		 * 3: Conviction
		 */
		final int metricType = 0;
		final double lowerMinSup = 0.1;
		final double upperMinSup = 1;
		final int numRules = 1000;
		final double metricScore = 0.3;
		final int firstYear = 2007;
		final int secondYear = 2007;

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

		System.out.println("Number of instances (students): "
				+ instances.numInstances());
		System.out.println("Number of attributes (subjects): "
				+ instances.numAttributes());

		// final StringBuilder results = new StringBuilder();.

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
			e.printStackTrace();
		}
		apriori.setOutputItemSets(true);

		System.out.println(apriori.toString());
	}
}
