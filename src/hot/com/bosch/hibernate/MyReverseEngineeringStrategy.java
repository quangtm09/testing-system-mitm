/**
 * 
 */
package com.bosch.hibernate;

import org.hibernate.cfg.reveng.DefaultReverseEngineeringStrategy;
import org.hibernate.cfg.reveng.TableIdentifier;
import org.hibernate.util.StringHelper;

/**
 * The Class MyReverseEngineeringStrategy.
 *
 * @author mih1hc
 */
public class MyReverseEngineeringStrategy extends
		DefaultReverseEngineeringStrategy {

	/** The package name. */
	private String packageName = "com.bosch.rts.entity";

	/* (non-Javadoc)
	 * @see org.hibernate.cfg.reveng.DefaultReverseEngineeringStrategy#tableToClassName(org.hibernate.cfg.reveng.TableIdentifier)
	 */
	@Override
	public String tableToClassName(TableIdentifier tableIdentifier) {
		String pkgName = (packageName == null ? "" : packageName);
		String className = toUpperCamelCase(tableIdentifier.getName()
				.substring(4));

		if (pkgName.length() > 0) {
			return StringHelper.qualify(pkgName, className);
		} else {
			return className;
		}

	}

	/**
	 * The main method.
	 *
	 * @param arg the arguments
	 */
	public static void main(String[] arg) {
		double x = 0.315;

		System.out.println(String.valueOf(x));
	}

}
