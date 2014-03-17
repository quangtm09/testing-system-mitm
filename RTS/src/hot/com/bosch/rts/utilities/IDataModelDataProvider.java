/**
 * 
 */
package com.bosch.rts.utilities;

import java.util.List;

/**
 * The Interface IDataModelDataProvider.
 *
 * @param <T> the generic type
 * @author bit71hc
 */
public interface IDataModelDataProvider<T> {

	/**
	 * Gets the data list.
	 *
	 * @return the data list
	 */
	List<T> getDataList();
}
