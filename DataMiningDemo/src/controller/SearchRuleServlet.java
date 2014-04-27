package controller;

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

import apriori.web.Rule;
import constant.DMConstant;

/**
 * Servlet implementation class SearchRuleServlet
 */
@WebServlet("/SearchRuleServlet")
public class SearchRuleServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void processRequest(final HttpServletRequest request, final HttpServletResponse response) throws IOException {
		final String input = request.getParameter("input");
        final String option = request.getParameter("option");
        final String gradeClassification = request.getParameter("gradeClassification");
        final int metricType = Integer.parseInt(request.getParameter("rankingType"));

        response.setContentType("text/html");
		final PrintWriter printWriter = response.getWriter();

        final Map map = new HashMap();

        List<Rule> ruleList = DataMiningServlet.getRuleList();

        List<Rule> returnList = null;

        if(!input.equals("")){
            if(option.equals("antecedent")){
                returnList = this.findRulesByAntecedent(ruleList, input);
            } else if(option.equals("consequent")){
                returnList = this.findRulesByConsequent(ruleList, input);
            } else {
                returnList = this.findRulesByBoth(ruleList, input);
            }
        }

        map.put("ruleList", returnList);
        map.put("isSearching", true);
        map.put("metricType", metricType);

        request.setAttribute("map", map);

        try {
			this.goToPage(DMConstant.RULE_JSP, request, response);
		} catch (final ServletException e) {
			printWriter.print("<div class=\"alert alert-danger\">Error while loading results! Please try again!</div>");
		}
	}

	 private List<Rule> findRulesByAntecedent(final List<Rule> ruleList, final String antecedent){
	        final List<Rule> newRuleList = new ArrayList<Rule>();
	        for(final Rule rule: ruleList){
	            String ruleAntecedent = rule.getAntecedent();
	            String ruleConsequent = rule.getConsequent();
	            if(ruleAntecedent.contains(antecedent)){

	                ruleAntecedent = ruleAntecedent.replaceAll("<b>", "");
	                ruleAntecedent = ruleAntecedent.replaceAll("</b>", "");

	                ruleConsequent = ruleConsequent.replaceAll("<b>", "");
	                ruleConsequent = ruleConsequent.replaceAll("</b>", "");

	                rule.setAntecedent(ruleAntecedent.replaceAll(antecedent, "<b>" + antecedent + "</b>"));
	                rule.setConsequent(ruleConsequent);
	                newRuleList.add(rule);
	            }
	        }
	        return newRuleList;
	    }

	    private List<Rule> findRulesByConsequent(final List<Rule> ruleList, final String consequent){
	        final List<Rule> newRuleList = new ArrayList<Rule>();
	        for(final Rule rule: ruleList){
	            String ruleConsequent = rule.getConsequent();
	            String ruleAntecedent = rule.getAntecedent();
	            if(ruleConsequent.contains(consequent)){

	                ruleConsequent = ruleConsequent.replaceAll("<b>", "");
	                ruleConsequent = ruleConsequent.replaceAll("</b>", "");

	                ruleAntecedent = ruleAntecedent.replaceAll("<b>", "");
	                ruleAntecedent = ruleAntecedent.replaceAll("</b>", "");

	                rule.setConsequent(ruleConsequent.replaceAll(consequent, "<b>" + consequent + "</b>"));
	                rule.setAntecedent(ruleAntecedent);
	                newRuleList.add(rule);
	            }
	        }
	        return newRuleList;
	    }

	    private List<Rule> findRulesByBoth(final List<Rule> ruleList, final String input){
	        List<Rule> newRuleList;
	        final List<Rule> foundRuleByAntecedent = this.findRulesByAntecedent(ruleList, input);
	        final List<Rule> foundRuleByConsequent = this.findRulesByConsequent(ruleList, input);

	        newRuleList = foundRuleByAntecedent;
	        for(final Rule rule: foundRuleByConsequent){
	            if(!newRuleList.contains(rule)){
	                newRuleList.add(rule);
	            }
	        }

	        return newRuleList;
	    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchRuleServlet() {
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
