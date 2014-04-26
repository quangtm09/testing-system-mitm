/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package apriori.web;

/**
 *
 * @author Quang
 */
public class Rule {
    private String antecedent;
    private String consequent;

    private double antecedentSupport;
    private double consequentSupport;

    private double confidence;
    private double lift;
    private double leverage;
    private int leverageExamples;
    private double conviction;

    private boolean findQuickRule;

    public String getAntecedent() {
        return this.antecedent;
    }

    public String getConsequent() {
        return this.consequent;
    }

    public double getAntecedentSupport() {
        return this.antecedentSupport;
    }

    public double getConsequentSupport() {
        return this.consequentSupport;
    }

    public double getConfidence() {
        return this.confidence;
    }

    public double getLift() {
        return this.lift;
    }

    public double getLeverage() {
        return this.leverage;
    }

    public double getConviction() {
        return this.conviction;
    }

    public boolean isFindQuickRule() {
        return this.findQuickRule;
    }

    public void setAntecedent(final String antecedent) {
        this.antecedent = antecedent;
    }

    public void setConsequent(final String consequent) {
        this.consequent = consequent;
    }

    public void setAntecedentSupport(final double antecedentSupport) {
        this.antecedentSupport = antecedentSupport;
    }

    public void setConsequentSupport(final double consequentSupport) {
        this.consequentSupport = consequentSupport;
    }

    public void setConfidence(final double confidence) {
        this.confidence = confidence;
    }

    public void setLift(final double lift) {
        this.lift = lift;
    }

    public void setLeverage(final double leverage) {
        this.leverage = leverage;
    }

    public void setConviction(final double conviction) {
        this.conviction = conviction;
    }

    public void setFindQuickRule(final boolean findQuickRule) {
        this.findQuickRule = findQuickRule;
    }

    public int getLeverageExamples() {
        return this.leverageExamples;
    }

    public void setLeverageExamples(final int leverageExamples) {
        this.leverageExamples = leverageExamples;
    }


}
