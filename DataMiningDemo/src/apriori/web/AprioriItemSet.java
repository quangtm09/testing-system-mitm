/*
 *    This program is free software; you can redistribute it and/or modify
 *    it under the terms of the GNU General Public License as published by
 *    the Free Software Foundation; either version 2 of the License, or
 *    (at your option) any later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU General Public License for more details.
 *
 *    You should have received a copy of the GNU General Public License
 *    along with this program; if not, write to the Free Software
 *    Foundation, Inc., 675 Mass Ave, Cambridge, MA 02139, USA.
 */

/*
 *    AprioriItemSet.java
 *    Copyright (C) 2004 University of Waikato, Hamilton, New Zealand
 *
 */

package apriori.web;

import java.io.Serializable;
import java.util.Enumeration;
import java.util.Hashtable;

import weka.core.ContingencyTables;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.RevisionHandler;
import weka.core.RevisionUtils;


/**
 * Class for storing a set of items. Item sets are stored in a lexicographic
 * order, which is determined by the header information of the set of instances
 * used for generating the set of items. All methods in this class assume that
 * item sets are stored in lexicographic order.
 * The class provides methods that are used in the Apriori algorithm to construct
 * association rules.
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @author Stefan Mutter (mutter@cs.waikato.ac.nz)
 * @version $Revision: 1.6 $
 */
public class AprioriItemSet
  extends ItemSet
  implements Serializable, RevisionHandler {

  /** for serialization */
  static final long serialVersionUID = 7684467755712672058L;

  /**
   * Constructor
   *
   * @param totalTrans the total number of transactions in the data
   */
  public AprioriItemSet(final int totalTrans) {
    super(totalTrans);
  }


  /**
   * Outputs the confidence for a rule.
   *
   * @param premise the premise of the rule
   * @param consequence the consequence of the rule
   * @return the confidence on the training data
   */
  public static double confidenceForRule(final AprioriItemSet premise,
					 final AprioriItemSet consequence) {

    return (double)consequence.m_counter/(double)premise.m_counter;
  }

  /**
   * Outputs the lift for a rule. Lift is defined as:<br>
   * confidence / prob(consequence)
   *
   * @param premise the premise of the rule
   * @param consequence the consequence of the rule
   * @param consequenceCount how many times the consequence occurs independent
   * of the premise
   * @return the lift on the training data
   */
  public double liftForRule(final AprioriItemSet premise,
			    final AprioriItemSet consequence,
			    final int consequenceCount) {
    final double confidence = confidenceForRule(premise, consequence);

   return confidence / ((double)consequenceCount /
	  (double)this.m_totalTransactions);
  }

  /**
   * Outputs the leverage for a rule. Leverage is defined as: <br>
   * prob(premise & consequence) - (prob(premise) * prob(consequence))
   *
   * @param premise the premise of the rule
   * @param consequence the consequence of the rule
   * @param premiseCount how many times the premise occurs independent
   * of the consequent
   * @param consequenceCount how many times the consequence occurs independent
   * of the premise
   * @return the leverage on the training data
   */
  public double leverageForRule(final AprioriItemSet premise,
				final AprioriItemSet consequence,
				final int premiseCount,
				final int consequenceCount) {
    final double coverageForItemSet = (double)consequence.m_counter /
      (double)this.m_totalTransactions;
    final double expectedCoverageIfIndependent =
      (double)premiseCount / (double)this.m_totalTransactions *
      ((double)consequenceCount / (double)this.m_totalTransactions);
    final double lev = coverageForItemSet - expectedCoverageIfIndependent;
    return lev;
  }

  /**
   * Outputs the conviction for a rule. Conviction is defined as: <br>
   * prob(premise) * prob(!consequence) / prob(premise & !consequence)
   *
   * @param premise the premise of the rule
   * @param consequence the consequence of the rule
   * @param premiseCount how many times the premise occurs independent
   * of the consequent
   * @param consequenceCount how many times the consequence occurs independent
   * of the premise
   * @return the conviction on the training data
   */
  public double convictionForRule(final AprioriItemSet premise,
				   final AprioriItemSet consequence,
				   final int premiseCount,
				   final int consequenceCount) {
    final double num =
      (double)premiseCount * (double)(this.m_totalTransactions - consequenceCount) /
       this.m_totalTransactions;
    final double denom =
      premiseCount - consequence.m_counter+1;

    if (num < 0 || denom < 0) {
      System.err.println("*** "+num+" "+denom);
      System.err.println("premis count: "+premiseCount+" consequence count "+consequenceCount+" total trans "+this.m_totalTransactions);
    }
    return num / denom;
  }



  /**
   * Generates all rules for an item set.
   *
   * @param minConfidence the minimum confidence the rules have to have
   * @param hashtables containing all(!) previously generated
   * item sets
   * @param numItemsInSet the size of the item set for which the rules
   * are to be generated
   * @return all the rules with minimum confidence for the given item set
   */
  public FastVector[] generateRules(final double minConfidence,
					  final FastVector hashtables,
					  final int numItemsInSet) {

    final FastVector premises = new FastVector(),consequences = new FastVector(),
      conf = new FastVector();
    final FastVector[] rules = new FastVector[3];
	FastVector[] moreResults;
    AprioriItemSet premise, consequence;
    final Hashtable hashtable = (Hashtable)hashtables.elementAt(numItemsInSet - 2);

    // Generate all rules with one item in the consequence.
    for (int i = 0; i < this.m_items.length; i++) {
		if (this.m_items[i] != -1) {
		premise = new AprioriItemSet(this.m_totalTransactions);
		consequence = new AprioriItemSet(this.m_totalTransactions);
		premise.m_items = new int[this.m_items.length];
		consequence.m_items = new int[this.m_items.length];
		consequence.m_counter = this.m_counter;

		for (int j = 0; j < this.m_items.length; j++) {
			consequence.m_items[j] = -1;
		}
		System.arraycopy(this.m_items, 0, premise.m_items, 0, this.m_items.length);
		premise.m_items[i] = -1;

		consequence.m_items[i] = this.m_items[i];
		premise.m_counter = ((Integer)hashtable.get(premise)).intValue();
		premises.addElement(premise);
		consequences.addElement(consequence);
		conf.addElement(new Double(confidenceForRule(premise, consequence)));
		  }
	}
    rules[0] = premises;
    rules[1] = consequences;
    rules[2] = conf;
    pruneRules(rules, minConfidence);

    // Generate all the other rules
    moreResults = this.moreComplexRules(rules, numItemsInSet, 1, minConfidence,
				   hashtables);
    if (moreResults != null) {
		for (int i = 0; i < moreResults[0].size(); i++) {
		rules[0].addElement(moreResults[0].elementAt(i));
		rules[1].addElement(moreResults[1].elementAt(i));
		rules[2].addElement(moreResults[2].elementAt(i));
		  }
	}
    return rules;
  }



  /**
   * Generates all significant rules for an item set.
   *
   * @param minMetric the minimum metric (confidence, lift, leverage,
   * improvement) the rules have to have
   * @param metricType (confidence=0, lift, leverage, improvement)
   * @param hashtables containing all(!) previously generated
   * item sets
   * @param numItemsInSet the size of the item set for which the rules
   * are to be generated
   * @param numTransactions
   * @param significanceLevel the significance level for testing the rules
   * @return all the rules with minimum metric for the given item set
   * @exception Exception if something goes wrong
   */
  public final FastVector[] generateRulesBruteForce(final double minMetric,
						    final int metricType,
						final FastVector hashtables,
						final int numItemsInSet,
						final int numTransactions,
						final double significanceLevel)
  throws Exception {

    final FastVector premises = new FastVector(),consequences = new FastVector(),
      conf = new FastVector(), lift = new FastVector(), lev = new FastVector(),
      conv = new FastVector();
    final FastVector[] rules = new FastVector[6];
    AprioriItemSet premise, consequence;
    Hashtable hashtableForPremise, hashtableForConsequence;
    int numItemsInPremise, help, max, consequenceUnconditionedCounter;
    final double[][] contingencyTable = new double[2][2];
    double metric, chiSquared;

    // Generate all possible rules for this item set and test their
    // significance.
    max = (int)Math.pow(2, numItemsInSet);
    for (int j = 1; j < max; j++) {
      numItemsInPremise = 0;
      help = j;
      while (help > 0) {
	if (help % 2 == 1) {
		numItemsInPremise++;
	}
	help /= 2;
      }
      if (numItemsInPremise < numItemsInSet) {
	hashtableForPremise =
	  (Hashtable)hashtables.elementAt(numItemsInPremise-1);
	hashtableForConsequence =
	  (Hashtable)hashtables.elementAt(numItemsInSet-numItemsInPremise-1);
	premise = new AprioriItemSet(this.m_totalTransactions);
	consequence = new AprioriItemSet(this.m_totalTransactions);
	premise.m_items = new int[this.m_items.length];

	consequence.m_items = new int[this.m_items.length];
	consequence.m_counter = this.m_counter;
	help = j;
	for (int i = 0; i < this.m_items.length; i++) {
		if (this.m_items[i] != -1) {
		    if (help % 2 == 1) {
		      premise.m_items[i] = this.m_items[i];
		      consequence.m_items[i] = -1;
		    } else {
		      premise.m_items[i] = -1;
		      consequence.m_items[i] = this.m_items[i];
		    }
		    help /= 2;
		  } else {
		    premise.m_items[i] = -1;
		    consequence.m_items[i] = -1;
		  }
	}
	premise.m_counter = ((Integer)hashtableForPremise.get(premise)).intValue();
	consequenceUnconditionedCounter =
	  ((Integer)hashtableForConsequence.get(consequence)).intValue();

	if (metricType == 0) {
	  contingencyTable[0][0] = consequence.m_counter;
	  contingencyTable[0][1] = premise.m_counter - consequence.m_counter;
	  contingencyTable[1][0] = consequenceUnconditionedCounter -
					    consequence.m_counter;
	  contingencyTable[1][1] = numTransactions - premise.m_counter -
					    consequenceUnconditionedCounter +
					    consequence.m_counter;
	  chiSquared = ContingencyTables.chiSquared(contingencyTable, false);

	  metric = confidenceForRule(premise, consequence);

	  if (!(metric < minMetric) &&
	      !(chiSquared > significanceLevel)) {
	    premises.addElement(premise);
	    consequences.addElement(consequence);
	    conf.addElement(new Double(metric));
	    lift.addElement(new Double(this.liftForRule(premise, consequence,
				       consequenceUnconditionedCounter)));
	    lev.addElement(new Double(this.leverageForRule(premise, consequence,
				     premise.m_counter,
				     consequenceUnconditionedCounter)));
	    conv.addElement(new Double(this.convictionForRule(premise, consequence,
				       premise.m_counter,
				       consequenceUnconditionedCounter)));
	  }
	} else {
	  final double tempConf = confidenceForRule(premise, consequence);
	  final double tempLift = this.liftForRule(premise, consequence,
					consequenceUnconditionedCounter);
	  final double tempLev = this.leverageForRule(premise, consequence,
					   premise.m_counter,
					   consequenceUnconditionedCounter);
	  final double tempConv = this.convictionForRule(premise, consequence,
					      premise.m_counter,
					      consequenceUnconditionedCounter);
	  switch(metricType) {
	  case 1:
	    metric = tempLift;
	    break;
	  case 2:
	    metric = tempLev;
	    break;
	  case 3:
	    metric = tempConv;
	    break;
	  default:
	    throw new Exception("ItemSet: Unknown metric type!");
	  }
	  if (!(metric < minMetric)) {
	    premises.addElement(premise);
	    consequences.addElement(consequence);
	    conf.addElement(new Double(tempConf));
	    lift.addElement(new Double(tempLift));
	    lev.addElement(new Double(tempLev));
	    conv.addElement(new Double(tempConv));
	  }
	}
      }
    }
    rules[0] = premises;
    rules[1] = consequences;
    rules[2] = conf;
    rules[3] = lift;
    rules[4] = lev;
    rules[5] = conv;
    return rules;
  }

  /**
   * Subtracts an item set from another one.
   *
   * @param toSubtract the item set to be subtracted from this one.
   * @return an item set that only contains items form this item sets that
   * are not contained by toSubtract
   */
  public final AprioriItemSet subtract(final AprioriItemSet toSubtract) {

    final AprioriItemSet result = new AprioriItemSet(this.m_totalTransactions);

    result.m_items = new int[this.m_items.length];

    for (int i = 0; i < this.m_items.length; i++) {
		if (toSubtract.m_items[i] == -1) {
			result.m_items[i] = this.m_items[i];
		} else {
			result.m_items[i] = -1;
		}
	}
    result.m_counter = 0;
    return result;
  }


  /**
   * Generates rules with more than one item in the consequence.
   *
   * @param rules all the rules having (k-1)-item sets as consequences
   * @param numItemsInSet the size of the item set for which the rules
   * are to be generated
   * @param numItemsInConsequence the value of (k-1)
   * @param minConfidence the minimum confidence a rule has to have
   * @param hashtables the hashtables containing all(!) previously generated
   * item sets
   * @return all the rules having (k)-item sets as consequences
   */
  private final FastVector[] moreComplexRules(final FastVector[] rules,
					      final int numItemsInSet,
					      final int numItemsInConsequence,
					      final double minConfidence,
					      final FastVector hashtables) {

    AprioriItemSet newPremise;
    FastVector[] result, moreResults;
    FastVector newConsequences;
	final FastVector newPremises = new FastVector(), newConf = new FastVector();
    Hashtable hashtable;

    if (numItemsInSet > numItemsInConsequence + 1) {
      hashtable =
	(Hashtable)hashtables.elementAt(numItemsInSet - numItemsInConsequence - 2);
      newConsequences = mergeAllItemSets(rules[1],
					 numItemsInConsequence - 1,
					 this.m_totalTransactions);
      final Enumeration enu = newConsequences.elements();
      while (enu.hasMoreElements()) {
	final AprioriItemSet current = (AprioriItemSet)enu.nextElement();
	current.m_counter = this.m_counter;
	newPremise = this.subtract(current);
	newPremise.m_counter = ((Integer)hashtable.get(newPremise)).intValue();
	newPremises.addElement(newPremise);
	newConf.addElement(new Double(confidenceForRule(newPremise, current)));
      }
      result = new FastVector[3];
      result[0] = newPremises;
      result[1] = newConsequences;
      result[2] = newConf;
      pruneRules(result, minConfidence);
      moreResults = this.moreComplexRules(result,numItemsInSet,numItemsInConsequence+1,
				     minConfidence, hashtables);
      if (moreResults != null) {
		for (int i = 0; i < moreResults[0].size(); i++) {
		  result[0].addElement(moreResults[0].elementAt(i));
		  result[1].addElement(moreResults[1].elementAt(i));
		  result[2].addElement(moreResults[2].elementAt(i));
		}
	}
      return result;
    } else {
		return null;
	}
  }


   /**
   * Returns the contents of an item set as a string.
   *
   * @param instances contains the relevant header information
   * @return string describing the item set
   */
  @Override
public final String toString(final Instances instances) {

      return super.toString(instances);
  }

  /**
   * Converts the header info of the given set of instances into a set
   * of item sets (singletons). The ordering of values in the header file
   * determines the lexicographic order.
   *
   * @param instances the set of instances whose header info is to be used
   * @return a set of item sets, each containing a single item
   * @exception Exception if singletons can't be generated successfully
   */
  public static FastVector singletons(final Instances instances) throws Exception {

    final FastVector setOfItemSets = new FastVector();
    ItemSet current;

    for (int i = 0; i < instances.numAttributes(); i++) {
      if (instances.attribute(i).isNumeric()) {
		throw new Exception("Can't handle numeric attributes!");
	}
      for (int j = 0; j < instances.attribute(i).numValues(); j++) {
	current = new AprioriItemSet(instances.numInstances());
	current.m_items = new int[instances.numAttributes()];
	for (int k = 0; k < instances.numAttributes(); k++) {
		current.m_items[k] = -1;
	}
	current.m_items[i] = j;
	setOfItemSets.addElement(current);
      }
    }
    return setOfItemSets;
  }

  /**
   * Merges all item sets in the set of (k-1)-item sets
   * to create the (k)-item sets and updates the counters.
   *
   * @param itemSets the set of (k-1)-item sets
   * @param size the value of (k-1)
   * @param totalTrans the total number of transactions in the data
   * @return the generated (k)-item sets
   */
  public static FastVector mergeAllItemSets(final FastVector itemSets, final int size,
					    final int totalTrans) {

    final FastVector newVector = new FastVector();
    ItemSet result;
    int numFound, k;

    for (int i = 0; i < itemSets.size(); i++) {
      final ItemSet first = (ItemSet)itemSets.elementAt(i);
    out:
      for (int j = i+1; j < itemSets.size(); j++) {
	final ItemSet second = (ItemSet)itemSets.elementAt(j);
	result = new AprioriItemSet(totalTrans);
	result.m_items = new int[first.m_items.length];

	// Find and copy common prefix of size 'size'
	numFound = 0;
	k = 0;
	while (numFound < size) {
	  if (first.m_items[k] == second.m_items[k]) {
	    if (first.m_items[k] != -1) {
			numFound++;
		}
	    result.m_items[k] = first.m_items[k];
	  } else {
		break out;
	}
	  k++;
	}

	// Check difference
	while (k < first.m_items.length) {
	  if (first.m_items[k] != -1 && second.m_items[k] != -1) {
		break;
	} else {
	    if (first.m_items[k] != -1) {
			result.m_items[k] = first.m_items[k];
		} else {
			result.m_items[k] = second.m_items[k];
		}
	  }
	  k++;
	}
	if (k == first.m_items.length) {
	  result.m_counter = 0;
	  newVector.addElement(result);
	}
      }
    }
    return newVector;
  }

  /**
   * Returns the revision string.
   *
   * @return		the revision
   */
  @Override
public String getRevision() {
    return RevisionUtils.extract("$Revision: 1.6 $");
  }
}
