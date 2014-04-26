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
 *    Apriori.java
 *    Copyright (C) 1999 University of Waikato, Hamilton, New Zealand
 *
 */

package apriori.console;

import java.util.Enumeration;
import java.util.Hashtable;

import weka.core.AttributeStats;
import weka.core.Capabilities;
import weka.core.Capabilities.Capability;
import weka.core.FastVector;
import weka.core.Instances;
import weka.core.Option;
import weka.core.OptionHandler;
import weka.core.RevisionUtils;
import weka.core.SelectedTag;
import weka.core.Tag;
import weka.core.TechnicalInformation;
import weka.core.TechnicalInformation.Field;
import weka.core.TechnicalInformation.Type;
import weka.core.TechnicalInformationHandler;
import weka.core.Utils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

/**
 * <!-- globalinfo-start --> Class implementing an Apriori-type algorithm.
 * Iteratively reduces the minimum support until it finds the required number of
 * rules with the given minimum confidence.<br/>
 * The algorithm has an option to mine class association rules. It is adapted as
 * explained in the second reference.<br/>
 * <br/>
 * For more information see:<br/>
 * <br/>
 * R. Agrawal, R. Srikant: Fast Algorithms for Mining Association Rules in Large
 * Databases. In: 20th International Conference on Very Large Data Bases,
 * 478-499, 1994.<br/>
 * <br/>
 * Bing Liu, Wynne Hsu, Yiming Ma: Integrating Classification and Association
 * Rule Mining. In: Fourth International Conference on Knowledge Discovery and
 * Data Mining, 80-86, 1998.
 * <p/>
 * <!-- globalinfo-end -->
 *
 * <!-- technical-bibtex-start --> BibTeX:
 *
 * <pre>
 * &#64;inproceedings{Agrawal1994,
 *    author = {R. Agrawal and R. Srikant},
 *    booktitle = {20th International Conference on Very Large Data Bases},
 *    pages = {478-499},
 *    publisher = {Morgan Kaufmann, Los Altos, CA},
 *    title = {Fast Algorithms for Mining Association Rules in Large Databases},
 *    year = {1994}
 * }
 *
 * &#64;inproceedings{Liu1998,
 *    author = {Bing Liu and Wynne Hsu and Yiming Ma},
 *    booktitle = {Fourth International Conference on Knowledge Discovery and Data Mining},
 *    pages = {80-86},
 *    publisher = {AAAI Press},
 *    title = {Integrating Classification and Association Rule Mining},
 *    year = {1998}
 * }
 * </pre>
 * <p/>
 * <!-- technical-bibtex-end -->
 *
 * <!-- options-start --> Valid options are:
 * <p/>
 *
 * <pre>
 * -N &lt;required number of rules output&gt;
 *  The required number of rules. (default = 10)
 * </pre>
 *
 * <pre>
 * -T &lt;0=confidence | 1=lift | 2=leverage | 3=Conviction&gt;
 *  The metric type by which to rank rules. (default = confidence)
 * </pre>
 *
 * <pre>
 * -C &lt;minimum metric score of a rule&gt;
 *  The minimum confidence of a rule. (default = 0.9)
 * </pre>
 *
 * <pre>
 * -D &lt;delta for minimum support&gt;
 *  The delta by which the minimum support is decreased in
 *  each iteration. (default = 0.05)
 * </pre>
 *
 * <pre>
 * -U &lt;upper bound for minimum support&gt;
 *  Upper bound for minimum support. (default = 1.0)
 * </pre>
 *
 * <pre>
 * -M &lt;lower bound for minimum support&gt;
 *  The lower bound for the minimum support. (default = 0.1)
 * </pre>
 *
 * <pre>
 * -S &lt;significance level&gt;
 *  If used, rules are tested for significance at
 *  the given level. Slower. (default = no significance testing)
 * </pre>
 *
 * <pre>
 * -I
 *  If set the itemsets found are also output. (default = no)
 * </pre>
 *
 * <pre>
 * -R
 *  Remove columns that contain all missing values (default = no)
 * </pre>
 *
 * <pre>
 * -V
 *  Report progress iteratively. (default = no)
 * </pre>
 *
 * <pre>
 * -A
 *  If set class association rules are mined. (default = no)
 * </pre>
 *
 * <pre>
 * -c &lt;the class index&gt;
 *  The class index. (default = last)
 * </pre>
 *
 * <!-- options-end -->
 *
 * @author Eibe Frank (eibe@cs.waikato.ac.nz)
 * @author Mark Hall (mhall@cs.waikato.ac.nz)
 * @author Stefan Mutter (mutter@cs.waikato.ac.nz)
 * @version $Revision: 9096 $
 */
public class Apriori extends AbstractAssociator implements OptionHandler,
		CARuleMiner, TechnicalInformationHandler {

	/** for serialization */
	static final long serialVersionUID = 3277498842319212687L;

	/** The minimum support. */
	protected double m_minSupport;

	/** The upper bound on the support */
	protected double m_upperBoundMinSupport;

	/** The lower bound for the minimum support. */
	protected double m_lowerBoundMinSupport;

	/** Metric type: Confidence */
	protected static final int CONFIDENCE = 0;
	/** Metric type: Lift */
	protected static final int LIFT = 1;
	/** Metric type: Leverage */
	protected static final int LEVERAGE = 2;
	/** Metric type: Conviction */
	protected static final int CONVICTION = 3;
	/** Metric types. */
	public static final Tag[] TAGS_SELECTION = {
			new Tag(CONFIDENCE, "Confidence"), new Tag(LIFT, "Lift"),
			new Tag(LEVERAGE, "Leverage"), new Tag(CONVICTION, "Conviction") };

	/** The selected metric type. */
	protected int m_metricType = CONFIDENCE;

	/** The minimum metric score. */
	protected double m_minMetric;

	/** The maximum number of rules that are output. */
	protected int m_numRules;

	/** Delta by which m_minSupport is decreased in each iteration. */
	protected double m_delta;

	/** Significance level for optional significance test. */
	protected double m_significanceLevel;

	/** Number of cycles used before required number of rules was one. */
	protected int m_cycles;

	/** The set of all sets of itemsets L. */
	protected FastVector m_Ls;

	/** The same information stored in hash tables. */
	protected FastVector m_hashtables;

	/** The list of all generated rules. */
	protected FastVector[] m_allTheRules;

	/**
	 * The instances (transactions) to be used for generating the association
	 * rules.
	 */
	protected Instances m_instances;

	/** Output itemsets found? */
	protected boolean m_outputItemSets;

	/** Remove columns with all missing values */
	protected boolean m_removeMissingCols;

	/** Report progress iteratively */
	protected boolean m_verbose;

	/** Only the class attribute of all Instances. */
	protected Instances m_onlyClass;

	/** The class index. */
	protected int m_classIndex;

	/** Flag indicating whether class association rules are mined. */
	protected boolean m_car;

	/**
	 * Returns a string describing this associator
	 *
	 * @return a description of the evaluator suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String globalInfo() {
		return "Class implementing an Apriori-type algorithm. Iteratively reduces "
				+ "the minimum support until it finds the required number of rules with "
				+ "the given minimum confidence.\n"
				+ "The algorithm has an option to mine class association rules. It is "
				+ "adapted as explained in the second reference.\n\n"
				+ "For more information see:\n\n"
				+ this.getTechnicalInformation().toString();
	}

	/**
	 * Returns an instance of a TechnicalInformation object, containing detailed
	 * information about the technical background of this class, e.g., paper
	 * reference or book this class is based on.
	 *
	 * @return the technical information about this class
	 */
	@Override
	public TechnicalInformation getTechnicalInformation() {
		TechnicalInformation result;
		TechnicalInformation additional;

		result = new TechnicalInformation(Type.INPROCEEDINGS);
		result.setValue(Field.AUTHOR,
				Messages.getInstance().getString("APRIORI_AUTHOR"));
		result.setValue(Field.TITLE,
				"Fast Algorithms for Mining Association Rules in Large Databases");
		result.setValue(Field.BOOKTITLE,
				"20th International Conference on Very Large Data Bases");
		result.setValue(Field.YEAR, "1994");
		result.setValue(Field.PAGES, "478-499");
		result.setValue(Field.PUBLISHER, "Morgan Kaufmann, Los Altos, CA");

		additional = result.add(Type.INPROCEEDINGS);
		additional.setValue(Field.AUTHOR,
				"Bing Liu and Wynne Hsu and Yiming Ma");
		additional.setValue(Field.TITLE,
				"Integrating Classification and Association Rule Mining");
		additional
				.setValue(Field.BOOKTITLE,
						"Fourth International Conference on Knowledge Discovery and Data Mining");
		additional.setValue(Field.YEAR, "1998");
		additional.setValue(Field.PAGES, "80-86");
		additional.setValue(Field.PUBLISHER, "AAAI Press");

		return result;
	}

	/**
	 * Constructor that allows to sets default values for the minimum confidence
	 * and the maximum number of rules the minimum confidence.
	 */
	public Apriori() {

		this.resetOptions();
	}

	/**
	 * Resets the options to the default values.
	 */
	public void resetOptions() {

		this.m_removeMissingCols = false;
		this.m_verbose = false;
		this.m_delta = 0.05;
		this.m_minMetric = 0.90;
		this.m_numRules = 10;
		this.m_lowerBoundMinSupport = 0.1;
		this.m_upperBoundMinSupport = 1.0;
		this.m_significanceLevel = -1;
		this.m_outputItemSets = false;
		this.m_car = false;
		this.m_classIndex = -1;
	}

	/**
	 * Removes columns that are all missing from the data
	 *
	 * @param instances
	 *            the instances
	 * @return a new set of instances with all missing columns removed
	 * @throws Exception
	 *             if something goes wrong
	 */
	protected Instances removeMissingColumns(final Instances instances)
			throws Exception {

		final int numInstances = instances.numInstances();
		final StringBuffer deleteString = new StringBuffer();
		int removeCount = 0;
		boolean first = true;
		int maxCount = 0;

		for (int i = 0; i < instances.numAttributes(); i++) {
			final AttributeStats as = instances.attributeStats(i);
			if (this.m_upperBoundMinSupport == 1.0 && maxCount != numInstances) {
				// see if we can decrease this by looking for the most frequent
				// value
				final int[] counts = as.nominalCounts;
				if (counts[Utils.maxIndex(counts)] > maxCount) {
					maxCount = counts[Utils.maxIndex(counts)];
				}
			}
			if (as.missingCount == numInstances) {
				if (first) {
					deleteString.append(i + 1);
					first = false;
				} else {
					deleteString.append("," + (i + 1));
				}
				removeCount++;
			}
		}
		if (this.m_verbose) {
			System.err.println("Removed : " + removeCount
					+ " columns with all missing " + "values.");
		}
		if (this.m_upperBoundMinSupport == 1.0 && maxCount != numInstances) {
			this.m_upperBoundMinSupport = (double) maxCount / (double) numInstances;
			if (this.m_verbose) {
				System.err.println("Setting upper bound min support to : "
						+ this.m_upperBoundMinSupport);
			}
		}

		if (deleteString.toString().length() > 0) {
			final Remove af = new Remove();
			af.setAttributeIndices(deleteString.toString());
			af.setInvertSelection(false);
			af.setInputFormat(instances);
			final Instances newInst = Filter.useFilter(instances, af);

			return newInst;
		}
		return instances;
	}

	/**
	 * Returns default capabilities of the classifier.
	 *
	 * @return the capabilities of this classifier
	 */
	@Override
	public Capabilities getCapabilities() {
		final Capabilities result = super.getCapabilities();
		result.disableAll();

		// attributes
		result.enable(Capability.NOMINAL_ATTRIBUTES);
		result.enable(Capability.MISSING_VALUES);

		// class (can handle a nominal class if CAR rules are selected). This
		result.enable(Capability.NO_CLASS);
		result.enable(Capability.NOMINAL_CLASS);
		result.enable(Capability.MISSING_CLASS_VALUES);

		return result;
	}

	/**
	 * Method that generates all large itemsets with a minimum support, and from
	 * these all association rules with a minimum confidence.
	 *
	 * @param instances
	 *            the instances to be used for generating the associations
	 * @throws Exception
	 *             if rules can't be built successfully
	 */
	@Override
	public void buildAssociations(Instances instances) throws Exception {

		double[] confidences, supports;
		int[] indices;
		FastVector[] sortedRuleSet;
		int necSupport = 0;

		instances = new Instances(instances);

		if (this.m_removeMissingCols) {
			instances = this.removeMissingColumns(instances);
		}
		if (this.m_car && this.m_metricType != CONFIDENCE) {
			throw new Exception(
					"For CAR-Mining metric type has to be confidence!");
		}

		// only set class index if CAR is requested
		if (this.m_car) {
			if (this.m_classIndex == -1) {
				instances.setClassIndex(instances.numAttributes() - 1);
			} else if (this.m_classIndex <= instances.numAttributes()
					&& this.m_classIndex > 0) {
				instances.setClassIndex(this.m_classIndex - 1);
			} else {
				throw new Exception("Invalid class index.");
			}
		}

		// can associator handle the data?
		this.getCapabilities().testWithFail(instances);

		this.m_cycles = 0;

		// make sure that the lower bound is equal to at least one instance
		final double lowerBoundMinSupportToUse = this.m_lowerBoundMinSupport
				* instances.numInstances() < 1.0 ? 1.0 / instances
				.numInstances() : this.m_lowerBoundMinSupport;

		if (this.m_car) {
			// m_instances does not contain the class attribute
			this.m_instances = LabeledItemSet.divide(instances, false);

			// m_onlyClass contains only the class attribute
			this.m_onlyClass = LabeledItemSet.divide(instances, true);
		} else {
			this.m_instances = instances;
		}

		if (this.m_car && this.m_numRules == Integer.MAX_VALUE) {
			// Set desired minimum support
			this.m_minSupport = lowerBoundMinSupportToUse;
		} else {
			// Decrease minimum support until desired number of rules found.
			this.m_minSupport = this.m_upperBoundMinSupport - this.m_delta;
			this.m_minSupport = this.m_minSupport < lowerBoundMinSupportToUse ? lowerBoundMinSupportToUse
					: this.m_minSupport;
		}

		do {

			// Reserve space for variables
			this.m_Ls = new FastVector();
			this.m_hashtables = new FastVector();
			this.m_allTheRules = new FastVector[6];
			this.m_allTheRules[0] = new FastVector();
			this.m_allTheRules[1] = new FastVector();
			this.m_allTheRules[2] = new FastVector();
			if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
				this.m_allTheRules[3] = new FastVector();
				this.m_allTheRules[4] = new FastVector();
				this.m_allTheRules[5] = new FastVector();
			}
			sortedRuleSet = new FastVector[6];
			sortedRuleSet[0] = new FastVector();
			sortedRuleSet[1] = new FastVector();
			sortedRuleSet[2] = new FastVector();
			if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
				sortedRuleSet[3] = new FastVector();
				sortedRuleSet[4] = new FastVector();
				sortedRuleSet[5] = new FastVector();
			}
			if (!this.m_car) {
				// Find large itemsets and rules
				this.findLargeItemSets();
				if (this.m_significanceLevel != -1 || this.m_metricType != CONFIDENCE) {
					this.findRulesBruteForce();
				} else {
					this.findRulesQuickly();
				}
			} else {
				this.findLargeCarItemSets();
				this.findCarRulesQuickly();
			}

			// prune rules for upper bound min support
			if (this.m_upperBoundMinSupport < 1.0) {
				this.pruneRulesForUpperBoundSupport();
			}

			// Sort rules according to their support
			/*
			 * supports = new double[m_allTheRules[2].size()]; for (int i = 0; i
			 * < m_allTheRules[2].size(); i++) supports[i] =
			 * (double)((AprioriItemSet
			 * )m_allTheRules[1].elementAt(i)).support(); indices =
			 * Utils.stableSort(supports); for (int i = 0; i <
			 * m_allTheRules[2].size(); i++) {
			 * sortedRuleSet[0].addElement(m_allTheRules
			 * [0].elementAt(indices[i]));
			 * sortedRuleSet[1].addElement(m_allTheRules
			 * [1].elementAt(indices[i]));
			 * sortedRuleSet[2].addElement(m_allTheRules
			 * [2].elementAt(indices[i])); if (m_metricType != CONFIDENCE ||
			 * m_significanceLevel != -1) {
			 * sortedRuleSet[3].addElement(m_allTheRules
			 * [3].elementAt(indices[i]));
			 * sortedRuleSet[4].addElement(m_allTheRules
			 * [4].elementAt(indices[i]));
			 * sortedRuleSet[5].addElement(m_allTheRules
			 * [5].elementAt(indices[i])); } }
			 */
			final int j = this.m_allTheRules[2].size() - 1;
			supports = new double[this.m_allTheRules[2].size()];
			for (int i = 0; i < j + 1; i++) {
				supports[j - i] = (double) ((ItemSet) this.m_allTheRules[1]
						.elementAt(j - i)).support() * -1;
			}
			indices = Utils.stableSort(supports);
			for (int i = 0; i < j + 1; i++) {
				sortedRuleSet[0].addElement(this.m_allTheRules[0]
						.elementAt(indices[j - i]));
				sortedRuleSet[1].addElement(this.m_allTheRules[1]
						.elementAt(indices[j - i]));
				sortedRuleSet[2].addElement(this.m_allTheRules[2]
						.elementAt(indices[j - i]));
				if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
					sortedRuleSet[3].addElement(this.m_allTheRules[3]
							.elementAt(indices[j - i]));
					sortedRuleSet[4].addElement(this.m_allTheRules[4]
							.elementAt(indices[j - i]));
					sortedRuleSet[5].addElement(this.m_allTheRules[5]
							.elementAt(indices[j - i]));
				}
			}

			// Sort rules according to their confidence
			this.m_allTheRules[0].removeAllElements();
			this.m_allTheRules[1].removeAllElements();
			this.m_allTheRules[2].removeAllElements();
			if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
				this.m_allTheRules[3].removeAllElements();
				this.m_allTheRules[4].removeAllElements();
				this.m_allTheRules[5].removeAllElements();
			}
			confidences = new double[sortedRuleSet[2].size()];
			final int sortType = 2 + this.m_metricType;

			for (int i = 0; i < sortedRuleSet[2].size(); i++) {
				confidences[i] = ((Double) sortedRuleSet[sortType].elementAt(i))
						.doubleValue();
			}
			indices = Utils.stableSort(confidences);
			for (int i = sortedRuleSet[0].size() - 1; i >= sortedRuleSet[0]
					.size() - this.m_numRules && i >= 0; i--) {
				this.m_allTheRules[0].addElement(sortedRuleSet[0]
						.elementAt(indices[i]));
				this.m_allTheRules[1].addElement(sortedRuleSet[1]
						.elementAt(indices[i]));
				this.m_allTheRules[2].addElement(sortedRuleSet[2]
						.elementAt(indices[i]));
				if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
					this.m_allTheRules[3].addElement(sortedRuleSet[3]
							.elementAt(indices[i]));
					this.m_allTheRules[4].addElement(sortedRuleSet[4]
							.elementAt(indices[i]));
					this.m_allTheRules[5].addElement(sortedRuleSet[5]
							.elementAt(indices[i]));
				}
			}

			if (this.m_verbose) {
				if (this.m_Ls.size() > 1) {
					System.out.println(this.toString());
				}
			}

			if (this.m_minSupport == lowerBoundMinSupportToUse
					|| this.m_minSupport - this.m_delta > lowerBoundMinSupportToUse) {
				this.m_minSupport -= this.m_delta;
			} else {
				this.m_minSupport = lowerBoundMinSupportToUse;
			}

			necSupport = Math.round((float) (this.m_minSupport * this.m_instances
					.numInstances() + 0.5));

			this.m_cycles++;
		} while (this.m_allTheRules[0].size() < this.m_numRules
				&& Utils.grOrEq(this.m_minSupport, lowerBoundMinSupportToUse)
				/* (necSupport >= lowerBoundNumInstancesSupport) */
				/* (Utils.grOrEq(m_minSupport, m_lowerBoundMinSupport)) */&& necSupport >= 1);
		this.m_minSupport += this.m_delta;
	}

	private void pruneRulesForUpperBoundSupport() {
		final int necMaxSupport = (int) (this.m_upperBoundMinSupport
				* this.m_instances.numInstances() + 0.5);

		final FastVector[] prunedRules = new FastVector[6];
		for (int i = 0; i < 6; i++) {
			prunedRules[i] = new FastVector();
		}

		for (int i = 0; i < this.m_allTheRules[0].size(); i++) {
			if (((ItemSet) this.m_allTheRules[1].elementAt(i)).support() <= necMaxSupport) {
				prunedRules[0].addElement(this.m_allTheRules[0].elementAt(i));
				prunedRules[1].addElement(this.m_allTheRules[1].elementAt(i));
				prunedRules[2].addElement(this.m_allTheRules[2].elementAt(i));

				if (!this.m_car) {
					prunedRules[3].addElement(this.m_allTheRules[3].elementAt(i));
					prunedRules[4].addElement(this.m_allTheRules[4].elementAt(i));
					prunedRules[5].addElement(this.m_allTheRules[5].elementAt(i));
				}
			}
		}

		this.m_allTheRules[0] = prunedRules[0];
		this.m_allTheRules[1] = prunedRules[1];
		this.m_allTheRules[2] = prunedRules[2];
		this.m_allTheRules[3] = prunedRules[3];
		this.m_allTheRules[4] = prunedRules[4];
		this.m_allTheRules[5] = prunedRules[5];
	}

	/**
	 * Method that mines all class association rules with minimum support and
	 * with a minimum confidence.
	 *
	 * @return an sorted array of FastVector (confidence depended) containing
	 *         the rules and metric information
	 * @param data
	 *            the instances for which class association rules should be
	 *            mined
	 * @throws Exception
	 *             if rules can't be built successfully
	 */
	@Override
	public FastVector[] mineCARs(final Instances data) throws Exception {

		this.m_car = true;
		this.buildAssociations(data);
		return this.m_allTheRules;
	}

	/**
	 * Gets the instances without the class atrribute.
	 *
	 * @return the instances without the class attribute.
	 */
	@Override
	public Instances getInstancesNoClass() {

		return this.m_instances;
	}

	/**
	 * Gets only the class attribute of the instances.
	 *
	 * @return the class attribute of all instances.
	 */
	@Override
	public Instances getInstancesOnlyClass() {

		return this.m_onlyClass;
	}

	/**
	 * Returns an enumeration describing the available options.
	 *
	 * @return an enumeration of all the available options.
	 */
	@Override
	public Enumeration listOptions() {

		final String string1 = "\tThe required number of rules. (default = "
				+ this.m_numRules + ")", string2 = "\tThe minimum confidence of a rule. (default = "
				+ this.m_minMetric + ")", string3 = "\tThe delta by which the minimum support is decreased in\n", string4 = "\teach iteration. (default = "
				+ this.m_delta + ")", string5 = "\tThe lower bound for the minimum support. (default = "
				+ this.m_lowerBoundMinSupport + ")", string6 = "\tIf used, rules are tested for significance at\n", string7 = "\tthe given level. Slower. (default = no significance testing)", string8 = "\tIf set the itemsets found are also output. (default = no)", string9 = "\tIf set class association rules are mined. (default = no)", string10 = "\tThe class index. (default = last)", stringType = "\tThe metric type by which to rank rules. (default = "
				+ "confidence)";

		final FastVector newVector = new FastVector(11);

		newVector.addElement(new Option(string1, "N", 1,
				"-N <required number of rules output>"));
		newVector.addElement(new Option(stringType, "T", 1,
				"-T <0=confidence | 1=lift | " + "2=leverage | 3=Conviction>"));
		newVector.addElement(new Option(string2, "C", 1,
				"-C <minimum metric score of a rule>"));
		newVector.addElement(new Option(string3 + string4, "D", 1,
				"-D <delta for minimum support>"));
		newVector.addElement(new Option("\tUpper bound for minimum support. "
				+ "(default = 1.0)", "U", 1,
				"-U <upper bound for minimum support>"));
		newVector.addElement(new Option(string5, "M", 1,
				"-M <lower bound for minimum support>"));
		newVector.addElement(new Option(string6 + string7, "S", 1,
				"-S <significance level>"));
		newVector.addElement(new Option(string8, "I", 0, "-I"));
		newVector.addElement(new Option("\tRemove columns that contain "
				+ "all missing values (default = no)", "R", 0, "-R"));
		newVector.addElement(new Option(
				"\tReport progress iteratively. (default " + "= no)", "V", 0,
				"-V"));
		newVector.addElement(new Option(string9, "A", 0, "-A"));
		newVector.addElement(new Option(string10, "c", 1,
				"-c <the class index>"));

		return newVector.elements();
	}

	/**
	 * Parses a given list of options.
	 * <p/>
	 *
	 * <!-- options-start --> Valid options are:
	 * <p/>
	 *
	 * <pre>
	 * -N &lt;required number of rules output&gt;
	 *  The required number of rules. (default = 10)
	 * </pre>
	 *
	 * <pre>
	 * -T &lt;0=confidence | 1=lift | 2=leverage | 3=Conviction&gt;
	 *  The metric type by which to rank rules. (default = confidence)
	 * </pre>
	 *
	 * <pre>
	 * -C &lt;minimum metric score of a rule&gt;
	 *  The minimum confidence of a rule. (default = 0.9)
	 * </pre>
	 *
	 * <pre>
	 * -D &lt;delta for minimum support&gt;
	 *  The delta by which the minimum support is decreased in
	 *  each iteration. (default = 0.05)
	 * </pre>
	 *
	 * <pre>
	 * -U &lt;upper bound for minimum support&gt;
	 *  Upper bound for minimum support. (default = 1.0)
	 * </pre>
	 *
	 * <pre>
	 * -M &lt;lower bound for minimum support&gt;
	 *  The lower bound for the minimum support. (default = 0.1)
	 * </pre>
	 *
	 * <pre>
	 * -S &lt;significance level&gt;
	 *  If used, rules are tested for significance at
	 *  the given level. Slower. (default = no significance testing)
	 * </pre>
	 *
	 * <pre>
	 * -I
	 *  If set the itemsets found are also output. (default = no)
	 * </pre>
	 *
	 * <pre>
	 * -R
	 *  Remove columns that contain all missing values (default = no)
	 * </pre>
	 *
	 * <pre>
	 * -V
	 *  Report progress iteratively. (default = no)
	 * </pre>
	 *
	 * <pre>
	 * -A
	 *  If set class association rules are mined. (default = no)
	 * </pre>
	 *
	 * <pre>
	 * -c &lt;the class index&gt;
	 *  The class index. (default = last)
	 * </pre>
	 *
	 * <!-- options-end -->
	 *
	 * @param options
	 *            the list of options as an array of strings
	 * @throws Exception
	 *             if an option is not supported
	 */
	@Override
	public void setOptions(final String[] options) throws Exception {

		this.resetOptions();
		final String numRulesString = Utils.getOption('N', options), minConfidenceString = Utils
				.getOption('C', options), deltaString = Utils.getOption('D',
				options), maxSupportString = Utils.getOption('U', options), minSupportString = Utils
				.getOption('M', options), significanceLevelString = Utils
				.getOption('S', options), classIndexString = Utils.getOption(
				'c', options);
		final String metricTypeString = Utils.getOption('T', options);
		if (metricTypeString.length() != 0) {
			this.setMetricType(new SelectedTag(Integer.parseInt(metricTypeString),
					TAGS_SELECTION));
		}

		if (numRulesString.length() != 0) {
			this.m_numRules = Integer.parseInt(numRulesString);
		}
		if (classIndexString.length() != 0) {
			if (classIndexString.equalsIgnoreCase("last")) {
				this.m_classIndex = -1;
			} else if (classIndexString.equalsIgnoreCase("first")) {
				this.m_classIndex = 0;
			} else {
				this.m_classIndex = Integer.parseInt(classIndexString);
			}
		}
		if (minConfidenceString.length() != 0) {
			this.m_minMetric = new Double(minConfidenceString).doubleValue();
		}
		if (deltaString.length() != 0) {
			this.m_delta = new Double(deltaString).doubleValue();
		}
		if (maxSupportString.length() != 0) {
			this.setUpperBoundMinSupport(new Double(maxSupportString)
					.doubleValue());
		}
		if (minSupportString.length() != 0) {
			this.m_lowerBoundMinSupport = new Double(minSupportString)
					.doubleValue();
		}
		if (significanceLevelString.length() != 0) {
			this.m_significanceLevel = new Double(significanceLevelString)
					.doubleValue();
		}
		this.m_outputItemSets = Utils.getFlag('I', options);
		this.m_car = Utils.getFlag('A', options);
		this.m_verbose = Utils.getFlag('V', options);
		this.setRemoveAllMissingCols(Utils.getFlag('R', options));
	}

	/**
	 * Gets the current settings of the Apriori object.
	 *
	 * @return an array of strings suitable for passing to setOptions
	 */
	@Override
	public String[] getOptions() {

		final String[] options = new String[20];
		int current = 0;

		if (this.m_outputItemSets) {
			options[current++] = "-I";
		}

		if (this.getRemoveAllMissingCols()) {
			options[current++] = "-R";
		}

		options[current++] = "-N";
		options[current++] = "" + this.m_numRules;
		options[current++] = "-T";
		options[current++] = "" + this.m_metricType;
		options[current++] = "-C";
		options[current++] = "" + this.m_minMetric;
		options[current++] = "-D";
		options[current++] = "" + this.m_delta;
		options[current++] = "-U";
		options[current++] = "" + this.m_upperBoundMinSupport;
		options[current++] = "-M";
		options[current++] = "" + this.m_lowerBoundMinSupport;
		options[current++] = "-S";
		options[current++] = "" + this.m_significanceLevel;
		if (this.m_car) {
			options[current++] = "-A";
		}
		if (this.m_verbose) {
			options[current++] = "-V";
		}
		options[current++] = "-c";
		options[current++] = "" + this.m_classIndex;

		while (current < options.length) {
			options[current++] = "";
		}
		return options;
	}

	/**
	 * Outputs the size of all the generated sets of itemsets and the rules.
	 *
	 * @return a string representation of the model
	 */
	@Override
	public String toString() {

		final StringBuffer text = new StringBuffer();

		if (this.m_Ls.size() <= 1) {
			return "\nNo large itemsets and rules found!\n";
		}
		text.append("\nApriori\n=======\n\n");
		text.append("Minimum support: " + Utils.doubleToString(this.m_minSupport, 2)
				+ " ("
				+ (int) (this.m_minSupport * this.m_instances.numInstances() + 0.5)
				+ " instances)" + '\n');
		text.append("Minimum metric <");
		switch (this.m_metricType) {
		case CONFIDENCE:
			text.append("confidence>: ");
			break;
		case LIFT:
			text.append("lift>: ");
			break;
		case LEVERAGE:
			text.append("leverage>: ");
			break;
		case CONVICTION:
			text.append("conviction>: ");
			break;
		}
		text.append(Utils.doubleToString(this.m_minMetric, 2) + '\n');

		if (this.m_significanceLevel != -1) {
			text.append("Significance level: "
					+ Utils.doubleToString(this.m_significanceLevel, 2) + '\n');
		}
		text.append("Number of cycles performed: " + this.m_cycles + '\n');
		text.append("\nGenerated sets of large itemsets:\n");
		if (!this.m_car) {
			for (int i = 0; i < this.m_Ls.size(); i++) {
				text.append("\nSize of set of large itemsets L(" + (i + 1)
						+ "): " + ((FastVector) this.m_Ls.elementAt(i)).size()
						+ '\n');
				if (this.m_outputItemSets) {
					text.append("\nLarge Itemsets L(" + (i + 1) + "):\n");
					for (int j = 0; j < ((FastVector) this.m_Ls.elementAt(i)).size(); j++) {
						text.append(((AprioriItemSet) ((FastVector) this.m_Ls
								.elementAt(i)).elementAt(j))
								.toString(this.m_instances)
								+ "\n");
					}
				}
			}
			text.append("\nBest rules found:\n\n");
			for (int i = 0; i < this.m_allTheRules[0].size(); i++) {
				text.append(Utils.doubleToString((double) i + 1,
						(int) (Math.log(this.m_numRules) / Math.log(10) + 1), 0)
						+ ". "
						+ ((AprioriItemSet) this.m_allTheRules[0].elementAt(i))
								.toString(this.m_instances)
						+ " ==> "
						+ ((AprioriItemSet) this.m_allTheRules[1].elementAt(i))
								.toString(this.m_instances)
						+ "    conf:("
						+ Utils.doubleToString(((Double) this.m_allTheRules[2]
								.elementAt(i)).doubleValue(), 2) + ")");
				if (this.m_metricType != CONFIDENCE || this.m_significanceLevel != -1) {
					text.append((this.m_metricType == LIFT ? " <" : "")
							+ " lift:("
							+ Utils.doubleToString(((Double) this.m_allTheRules[3]
									.elementAt(i)).doubleValue(), 2) + ")"
							+ (this.m_metricType == LIFT ? ">" : ""));
					text.append((this.m_metricType == LEVERAGE ? " <" : "")
							+ " lev:("
							+ Utils.doubleToString(((Double) this.m_allTheRules[4]
									.elementAt(i)).doubleValue(), 2) + ")");
					text.append(" ["
							+ (int) (((Double) this.m_allTheRules[4].elementAt(i))
									.doubleValue() * this.m_instances.numInstances())
							+ "]" + (this.m_metricType == LEVERAGE ? ">" : ""));
					text.append((this.m_metricType == CONVICTION ? " <" : "")
							+ " conv:("
							+ Utils.doubleToString(((Double) this.m_allTheRules[5]
									.elementAt(i)).doubleValue(), 2) + ")"
							+ (this.m_metricType == CONVICTION ? ">" : ""));
				}
				text.append('\n');
			}
		} else {
			for (int i = 0; i < this.m_Ls.size(); i++) {
				text.append("\nSize of set of large itemsets L(" + (i + 1)
						+ "): " + ((FastVector) this.m_Ls.elementAt(i)).size()
						+ '\n');
				if (this.m_outputItemSets) {
					text.append("\nLarge Itemsets L(" + (i + 1) + "):\n");
					for (int j = 0; j < ((FastVector) this.m_Ls.elementAt(i)).size(); j++) {
						text.append(((ItemSet) ((FastVector) this.m_Ls.elementAt(i))
								.elementAt(j)).toString(this.m_instances) + "\n");
						text.append(((LabeledItemSet) ((FastVector) this.m_Ls
								.elementAt(i)).elementAt(j)).m_classLabel
								+ "  ");
						text.append(((LabeledItemSet) ((FastVector) this.m_Ls
								.elementAt(i)).elementAt(j)).support() + "\n");
					}
				}
			}
			text.append("\nBest rules found:\n\n");
			for (int i = 0; i < this.m_allTheRules[0].size(); i++) {
				text.append(Utils.doubleToString((double) i + 1,
						(int) (Math.log(this.m_numRules) / Math.log(10) + 1), 0)
						+ ". "
						+ ((ItemSet) this.m_allTheRules[0].elementAt(i))
								.toString(this.m_instances)
						+ " ==> "
						+ ((ItemSet) this.m_allTheRules[1].elementAt(i))
								.toString(this.m_onlyClass)
						+ "    conf:("
						+ Utils.doubleToString(((Double) this.m_allTheRules[2]
								.elementAt(i)).doubleValue(), 2) + ")");

				text.append('\n');
			}
		}
		return text.toString();
	}

	/**
	 * Returns the metric string for the chosen metric type
	 *
	 * @return a string describing the used metric for the interestingness of a
	 *         class association rule
	 */
	@Override
	public String metricString() {

		switch (this.m_metricType) {
		case LIFT:
			return "lif";
		case LEVERAGE:
			return "leverage";
		case CONVICTION:
			return "conviction";
		default:
			return "conf";
		}
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String removeAllMissingColsTipText() {
		return "Remove columns with all missing values.";
	}

	/**
	 * Remove columns containing all missing values.
	 *
	 * @param r
	 *            true if cols are to be removed.
	 */
	public void setRemoveAllMissingCols(final boolean r) {
		this.m_removeMissingCols = r;
	}

	/**
	 * Returns whether columns containing all missing values are to be removed
	 *
	 * @return true if columns are to be removed.
	 */
	public boolean getRemoveAllMissingCols() {
		return this.m_removeMissingCols;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String upperBoundMinSupportTipText() {
		return "Upper bound for minimum support. Start iteratively decreasing "
				+ "minimum support from this value.";
	}

	/**
	 * Get the value of upperBoundMinSupport.
	 *
	 * @return Value of upperBoundMinSupport.
	 */
	public double getUpperBoundMinSupport() {

		return this.m_upperBoundMinSupport;
	}

	/**
	 * Set the value of upperBoundMinSupport.
	 *
	 * @param v
	 *            Value to assign to upperBoundMinSupport.
	 */
	public void setUpperBoundMinSupport(final double v) {

		this.m_upperBoundMinSupport = v;
	}

	/**
	 * Sets the class index
	 *
	 * @param index
	 *            the class index
	 */
	@Override
	public void setClassIndex(final int index) {

		this.m_classIndex = index;
	}

	/**
	 * Gets the class index
	 *
	 * @return the index of the class attribute
	 */
	public int getClassIndex() {

		return this.m_classIndex;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String classIndexTipText() {
		return "Index of the class attribute. If set to -1, the last attribute is taken as class attribute.";

	}

	/**
	 * Sets class association rule mining
	 *
	 * @param flag
	 *            if class association rules are mined, false otherwise
	 */
	public void setCar(final boolean flag) {
		this.m_car = flag;
	}

	/**
	 * Gets whether class association ruels are mined
	 *
	 * @return true if class association rules are mined, false otherwise
	 */
	public boolean getCar() {
		return this.m_car;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String carTipText() {
		return "If enabled class association rules are mined instead of (general) association rules.";
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String lowerBoundMinSupportTipText() {
		return "Lower bound for minimum support.";
	}

	/**
	 * Get the value of lowerBoundMinSupport.
	 *
	 * @return Value of lowerBoundMinSupport.
	 */
	public double getLowerBoundMinSupport() {

		return this.m_lowerBoundMinSupport;
	}

	/**
	 * Set the value of lowerBoundMinSupport.
	 *
	 * @param v
	 *            Value to assign to lowerBoundMinSupport.
	 */
	public void setLowerBoundMinSupport(final double v) {

		this.m_lowerBoundMinSupport = v;
	}

	/**
	 * Get the metric type
	 *
	 * @return the type of metric to use for ranking rules
	 */
	public SelectedTag getMetricType() {
		return new SelectedTag(this.m_metricType, TAGS_SELECTION);
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String metricTypeTipText() {
		return "Set the type of metric by which to rank rules. Confidence is "
				+ "the proportion of the examples covered by the premise that are also "
				+ "covered by the consequence(Class association rules can only be mined using confidence). Lift is confidence divided by the "
				+ "proportion of all examples that are covered by the consequence. This "
				+ "is a measure of the importance of the association that is independent "
				+ "of support. Leverage is the proportion of additional examples covered "
				+ "by both the premise and consequence above those expected if the "
				+ "premise and consequence were independent of each other. The total "
				+ "number of examples that this represents is presented in brackets "
				+ "following the leverage. Conviction is "
				+ "another measure of departure from independence. Conviction is given "
				+ "by P(premise)P(!consequence) / P(premise, !consequence).";
	}

	/**
	 * Set the metric type for ranking rules
	 *
	 * @param d
	 *            the type of metric
	 */
	public void setMetricType(final SelectedTag d) {

		if (d.getTags() == TAGS_SELECTION) {
			this.m_metricType = d.getSelectedTag().getID();
		}

		if (this.m_metricType == CONFIDENCE) {
			this.setMinMetric(0.9);
		}

		if (this.m_metricType == LIFT || this.m_metricType == CONVICTION) {
			this.setMinMetric(1.1);
		}

		if (this.m_metricType == LEVERAGE) {
			this.setMinMetric(0.1);
		}
	}

	public void setMetricType(final int metricType) {
        this.m_metricType = metricType;
    }

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String minMetricTipText() {
		return "Minimum metric score. Consider only rules with scores higher than "
				+ "this value.";
	}

	/**
	 * Get the value of minConfidence.
	 *
	 * @return Value of minConfidence.
	 */
	public double getMinMetric() {

		return this.m_minMetric;
	}

	/**
	 * Set the value of minConfidence.
	 *
	 * @param v
	 *            Value to assign to minConfidence.
	 */
	public void setMinMetric(final double v) {

		this.m_minMetric = v;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String numRulesTipText() {
		return "Number of rules to find.";
	}

	/**
	 * Get the value of numRules.
	 *
	 * @return Value of numRules.
	 */
	public int getNumRules() {

		return this.m_numRules;
	}

	/**
	 * Set the value of numRules.
	 *
	 * @param v
	 *            Value to assign to numRules.
	 */
	public void setNumRules(final int v) {

		this.m_numRules = v;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String deltaTipText() {
		return "Iteratively decrease support by this factor. Reduces support "
				+ "until min support is reached or required number of rules has been "
				+ "generated.";
	}

	/**
	 * Get the value of delta.
	 *
	 * @return Value of delta.
	 */
	public double getDelta() {

		return this.m_delta;
	}

	/**
	 * Set the value of delta.
	 *
	 * @param v
	 *            Value to assign to delta.
	 */
	public void setDelta(final double v) {

		this.m_delta = v;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String significanceLevelTipText() {
		return "Significance level. Significance test (confidence metric only).";
	}

	/**
	 * Get the value of significanceLevel.
	 *
	 * @return Value of significanceLevel.
	 */
	public double getSignificanceLevel() {

		return this.m_significanceLevel;
	}

	/**
	 * Set the value of significanceLevel.
	 *
	 * @param v
	 *            Value to assign to significanceLevel.
	 */
	public void setSignificanceLevel(final double v) {

		this.m_significanceLevel = v;
	}

	/**
	 * Sets whether itemsets are output as well
	 *
	 * @param flag
	 *            true if itemsets are to be output as well
	 */
	public void setOutputItemSets(final boolean flag) {
		this.m_outputItemSets = flag;
	}

	/**
	 * Gets whether itemsets are output as well
	 *
	 * @return true if itemsets are output as well
	 */
	public boolean getOutputItemSets() {
		return this.m_outputItemSets;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String outputItemSetsTipText() {
		return "If enabled the itemsets are output as well.";
	}

	/**
	 * Sets verbose mode
	 *
	 * @param flag
	 *            true if algorithm should be run in verbose mode
	 */
	public void setVerbose(final boolean flag) {
		this.m_verbose = flag;
	}

	/**
	 * Gets whether algorithm is run in verbose mode
	 *
	 * @return true if algorithm is run in verbose mode
	 */
	public boolean getVerbose() {
		return this.m_verbose;
	}

	/**
	 * Returns the tip text for this property
	 *
	 * @return tip text for this property suitable for displaying in the
	 *         explorer/experimenter gui
	 */
	public String verboseTipText() {
		return "If enabled the algorithm will be run in verbose mode.";
	}

	/**
	 * Method that finds all large itemsets for the given set of instances.
	 *
	 * @throws Exception
	 *             if an attribute is numeric
	 */
	private void findLargeItemSets() throws Exception {

		FastVector kMinusOneSets, kSets;
		Hashtable hashtable;
		int necSupport, necMaxSupport, i = 0;

		// Find large itemsets

		// minimum support
		necSupport = (int) (this.m_minSupport * this.m_instances.numInstances() + 0.5);
		necMaxSupport = (int) (this.m_upperBoundMinSupport
				* this.m_instances.numInstances() + 0.5);

		kSets = AprioriItemSet.singletons(this.m_instances);
		AprioriItemSet.upDateCounters(kSets, this.m_instances);
		kSets = AprioriItemSet.deleteItemSets(kSets, necSupport,
				this.m_instances.numInstances());
		if (kSets.size() == 0) {
			return;
		}
		do {
			this.m_Ls.addElement(kSets);
			kMinusOneSets = kSets;
			kSets = AprioriItemSet.mergeAllItemSets(kMinusOneSets, i,
					this.m_instances.numInstances());
			hashtable = AprioriItemSet.getHashtable(kMinusOneSets,
					kMinusOneSets.size());
			this.m_hashtables.addElement(hashtable);
			kSets = AprioriItemSet.pruneItemSets(kSets, hashtable);
			AprioriItemSet.upDateCounters(kSets, this.m_instances);
			kSets = AprioriItemSet.deleteItemSets(kSets, necSupport,
					this.m_instances.numInstances());
			i++;
		} while (kSets.size() > 0);
	}

	/**
	 * Method that finds all association rules and performs significance test.
	 *
	 * @throws Exception
	 *             if an attribute is numeric
	 */
	private void findRulesBruteForce() throws Exception {

		FastVector[] rules;

		// Build rules
		for (int j = 1; j < this.m_Ls.size(); j++) {
			final FastVector currentItemSets = (FastVector) this.m_Ls.elementAt(j);
			final Enumeration enumItemSets = currentItemSets.elements();
			while (enumItemSets.hasMoreElements()) {
				final AprioriItemSet currentItemSet = (AprioriItemSet) enumItemSets
						.nextElement();
				// AprioriItemSet currentItemSet = new
				// AprioriItemSet((ItemSet)enumItemSets.nextElement());
				rules = currentItemSet.generateRulesBruteForce(this.m_minMetric,
						this.m_metricType, this.m_hashtables, j + 1,
						this.m_instances.numInstances(), this.m_significanceLevel);
				for (int k = 0; k < rules[0].size(); k++) {
					this.m_allTheRules[0].addElement(rules[0].elementAt(k));
					this.m_allTheRules[1].addElement(rules[1].elementAt(k));
					this.m_allTheRules[2].addElement(rules[2].elementAt(k));

					this.m_allTheRules[3].addElement(rules[3].elementAt(k));
					this.m_allTheRules[4].addElement(rules[4].elementAt(k));
					this.m_allTheRules[5].addElement(rules[5].elementAt(k));
				}
			}
		}
	}

	/**
	 * Method that finds all association rules.
	 *
	 * @throws Exception
	 *             if an attribute is numeric
	 */
	private void findRulesQuickly() throws Exception {

		FastVector[] rules;

		// Build rules
		for (int j = 1; j < this.m_Ls.size(); j++) {
			final FastVector currentItemSets = (FastVector) this.m_Ls.elementAt(j);
			final Enumeration enumItemSets = currentItemSets.elements();
			while (enumItemSets.hasMoreElements()) {
				final AprioriItemSet currentItemSet = (AprioriItemSet) enumItemSets
						.nextElement();
				// AprioriItemSet currentItemSet = new
				// AprioriItemSet((ItemSet)enumItemSets.nextElement());
				rules = currentItemSet.generateRules(this.m_minMetric, this.m_hashtables,
						j + 1);
				for (int k = 0; k < rules[0].size(); k++) {
					this.m_allTheRules[0].addElement(rules[0].elementAt(k));
					this.m_allTheRules[1].addElement(rules[1].elementAt(k));
					this.m_allTheRules[2].addElement(rules[2].elementAt(k));
				}
			}
		}
	}

	/**
	 *
	 * Method that finds all large itemsets for class association rules for the
	 * given set of instances.
	 *
	 * @throws Exception
	 *             if an attribute is numeric
	 */
	private void findLargeCarItemSets() throws Exception {

		FastVector kMinusOneSets, kSets;
		Hashtable hashtable;
		int necSupport, necMaxSupport, i = 0;

		// Find large itemsets

		// minimum support
		final double nextMinSupport = this.m_minSupport * this.m_instances.numInstances();
		final double nextMaxSupport = this.m_upperBoundMinSupport
				* this.m_instances.numInstances();
		if (Math.rint(nextMinSupport) == nextMinSupport) {
			necSupport = (int) nextMinSupport;
		} else {
			necSupport = Math.round((float) (nextMinSupport + 0.5));
		}
		if (Math.rint(nextMaxSupport) == nextMaxSupport) {
			necMaxSupport = (int) nextMaxSupport;
		} else {
			necMaxSupport = Math.round((float) (nextMaxSupport + 0.5));
		}

		// find item sets of length one
		kSets = LabeledItemSet.singletons(this.m_instances, this.m_onlyClass);
		LabeledItemSet.upDateCounters(kSets, this.m_instances, this.m_onlyClass);

		// check if a item set of lentgh one is frequent, if not delete it
		kSets = LabeledItemSet.deleteItemSets(kSets, necSupport,
				this.m_instances.numInstances());
		if (kSets.size() == 0) {
			return;
		}
		do {
			this.m_Ls.addElement(kSets);
			kMinusOneSets = kSets;
			kSets = LabeledItemSet.mergeAllItemSets(kMinusOneSets, i,
					this.m_instances.numInstances());
			hashtable = LabeledItemSet.getHashtable(kMinusOneSets,
					kMinusOneSets.size());
			kSets = LabeledItemSet.pruneItemSets(kSets, hashtable);
			LabeledItemSet.upDateCounters(kSets, this.m_instances, this.m_onlyClass);
			kSets = LabeledItemSet.deleteItemSets(kSets, necSupport,
					this.m_instances.numInstances());
			i++;
		} while (kSets.size() > 0);
	}

	/**
	 * Method that finds all class association rules.
	 *
	 * @throws Exception
	 *             if an attribute is numeric
	 */
	private void findCarRulesQuickly() throws Exception {

		FastVector[] rules;

		// Build rules
		for (int j = 0; j < this.m_Ls.size(); j++) {
			final FastVector currentLabeledItemSets = (FastVector) this.m_Ls.elementAt(j);
			final Enumeration enumLabeledItemSets = currentLabeledItemSets.elements();
			while (enumLabeledItemSets.hasMoreElements()) {
				final LabeledItemSet currentLabeledItemSet = (LabeledItemSet) enumLabeledItemSets
						.nextElement();
				rules = currentLabeledItemSet.generateRules(this.m_minMetric, false);
				for (int k = 0; k < rules[0].size(); k++) {
					this.m_allTheRules[0].addElement(rules[0].elementAt(k));
					this.m_allTheRules[1].addElement(rules[1].elementAt(k));
					this.m_allTheRules[2].addElement(rules[2].elementAt(k));
				}
			}
		}
	}

	/**
	 * returns all the rules
	 *
	 * @return all the rules
	 * @see #m_allTheRules
	 */
	public FastVector[] getAllTheRules() {
		return this.m_allTheRules;
	}

	/**
	 * Returns the revision string.
	 *
	 * @return the revision
	 */
	@Override
	public String getRevision() {
		return RevisionUtils.extract("$Revision: 9096 $");
	}

	/**
	 * Main method.
	 *
	 * @param args
	 *            the commandline options
	 */
	public static void main(final String[] args) {
		runAssociator(new Apriori(), args);
	}
}
