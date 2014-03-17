package com.bosch.rts.session;

import java.util.Arrays;

import org.jboss.seam.annotations.Name;
import org.jboss.seam.framework.EntityQuery;
import com.bosch.rts.entity.Configuration;

/**
 * The Class ConfigurationList.
 */
@Name("configurationList")
public class ConfigurationList extends EntityQuery<Configuration> {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 7122841239942453073L;
	
	/** The Constant EJBQL. */
	private static final String EJBQL = "select configuration from Configuration configuration";
	
	/** The Constant RESTRICTIONS. */
	private static final String[] RESTRICTIONS = { "lower(configuration.name) = lower(#{configurationList.configuration.name})" };

	/** The configuration. */
	private Configuration configuration;

	/**
	 * Gets the configuration.
	 *
	 * @return the configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}

	/**
	 * Sets the configuration.
	 *
	 * @param configuration the new configuration
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}

	/**
	 * Instantiates a new configuration list.
	 */
	public ConfigurationList() {
		setEjbql(EJBQL);
		setRestrictionLogicOperator("and");
		setRestrictionExpressionStrings(Arrays.asList(RESTRICTIONS));
	}
}
