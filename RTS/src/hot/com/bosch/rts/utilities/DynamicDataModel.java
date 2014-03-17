/**
 * 
 */
package com.bosch.rts.utilities;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import org.ajax4jsf.model.DataVisitor;
import org.ajax4jsf.model.Range;
import org.ajax4jsf.model.SequenceRange;
import org.ajax4jsf.model.SerializableDataModel;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * The Class DynamicDataModel.
 *
 * @param <T> the generic type
 * @author bit71hc
 */
public class DynamicDataModel<T> extends SerializableDataModel {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 4783847579349308613L;
	
	/** The row id. */
	private Integer rowId;
	
	/** The wrapped data. */
	private Map<Integer, T> wrappedData = new HashMap<Integer, T>();
	
	/** The wrapped keys. */
	private List<Integer> wrappedKeys = null;
	
	/** The first row. */
	private Integer firstRow = 0;
	
	/** The number of rows. */
	private Integer numberOfRows = 1;
	
	/** The row count. */
	private int rowCount = 0;
	
	/** The data provider. */
	private IDataModelDataProvider<T> dataProvider;
	
	/** The log. */
	@Logger
	private transient Log log;


	/**
	 * Sets the row count.
	 *
	 * @param rowCount the new row count
	 */
	public void setRowCount(int rowCount) {
		this.rowCount = rowCount;
	}

	/**
	 * Gets the feed back list.
	 *
	 * @return the feed back list
	 */
	public IDataModelDataProvider<T> getFeedBackList() {
		return dataProvider;
	}

	/**
	 * Sets the data provider.
	 *
	 * @param dataProvider the new data provider
	 */
	public void setDataProvider(IDataModelDataProvider<T> dataProvider) {
		this.dataProvider = dataProvider;
	}

	/**
	 * Gets the row id.
	 *
	 * @return the row id
	 */
	public Integer getRowId() {
		return rowId;
	}

	/**
	 * Sets the row id.
	 *
	 * @param rowId the new row id
	 */
	public void setRowId(Integer rowId) {
		this.rowId = rowId;
	}

	/**
	 * Gets the wrapped keys.
	 *
	 * @return the wrapped keys
	 */
	public List<Integer> getWrappedKeys() {
		return wrappedKeys;
	}

	/**
	 * Sets the wrapped keys.
	 *
	 * @param wrappedKeys the new wrapped keys
	 */
	public void setWrappedKeys(List<Integer> wrappedKeys) {
		this.wrappedKeys = wrappedKeys;
	}

	/**
	 * Gets the first row.
	 *
	 * @return the first row
	 */
	public Integer getFirstRow() {
		return firstRow;
	}

	/**
	 * Sets the first row.
	 *
	 * @param firstRow the new first row
	 */
	public void setFirstRow(Integer firstRow) {
		this.firstRow = firstRow;
	}

	/**
	 * Gets the number of rows.
	 *
	 * @return the number of rows
	 */
	public Integer getNumberOfRows() {
		return numberOfRows;
	}

	/**
	 * Sets the number of rows.
	 *
	 * @param numberOfRows the new number of rows
	 */
	public void setNumberOfRows(Integer numberOfRows) {
		this.numberOfRows = numberOfRows;
	}

	/**
	 * Sets the wrapped data.
	 *
	 * @param wrappedData the wrapped data
	 */
	public void setWrappedData(Map<Integer, T> wrappedData) {
		this.wrappedData = wrappedData;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#getRowKey()
	 */
	@Override
	public Object getRowKey() {
		return rowId;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#setRowKey(java.lang.Object)
	 */
	@Override
	public void setRowKey(Object key) {
		this.rowId = (Integer) key;
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#walk(javax.faces.context.FacesContext, org.ajax4jsf.model.DataVisitor, org.ajax4jsf.model.Range, java.lang.Object)
	 */
	@Override
	public void walk(FacesContext context, DataVisitor visitor, Range range,
			Object argument) throws IOException {
		this.firstRow = ((SequenceRange) range).getFirstRow();
		this.numberOfRows = ((SequenceRange) range).getRows();
		List<T> dataList = dataProvider.getDataList();
		wrappedKeys = new ArrayList<Integer>();
		if (dataList != null && dataList.size() > 0) {
			try {
				Method getIdMethod = dataList.get(0).getClass()
						.getMethod("getId");
				wrappedKeys = new ArrayList<Integer>();
				if (!dataList.isEmpty()) {
					for (int i = 1; i <= dataList.size(); i++) {
						Integer id = (Integer) getIdMethod.invoke(dataList
								.get(i - 1));
						if (id != null) {
							wrappedKeys.add(id);
							wrappedData.put(id, dataList.get(i - 1));
							visitor.process(context, id, argument);
						}
					}
				}
			} catch (Exception e) {
				log.error("Execute saveCV method getting error------------------");
			}

		}
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowCount()
	 */
	@Override
	public int getRowCount() {
		return rowCount;

	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowData()
	 */
	@Override
	public T getRowData() {
		if (null == rowId) {
			return null;
		}
		return wrappedData.get(rowId);
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getRowIndex()
	 */
	@Override
	public int getRowIndex() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#getWrappedData()
	 */
	@Override
	public Object getWrappedData() {
		return null;
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#isRowAvailable()
	 */
	@Override
	public boolean isRowAvailable() {
		if (rowId == null) {
			return false;
		} else {
			return true;
		}
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setRowIndex(int)
	 */
	@Override
	public void setRowIndex(int arg0) {
	}

	/* (non-Javadoc)
	 * @see javax.faces.model.DataModel#setWrappedData(java.lang.Object)
	 */
	@Override
	public void setWrappedData(Object arg0) {
	}

	/* (non-Javadoc)
	 * @see org.ajax4jsf.model.ExtendedDataModel#getSerializableModel(org.ajax4jsf.model.Range)
	 */
	@Override
	/*
	 * * This method suppose to produce SerializableDataModel that will be
	 * serialized into View State and used on a post-back. In current
	 * implementation we just mark current model as serialized. In more
	 * complicated cases we may need to transform data to actually serialized
	 * form.
	 */
	public SerializableDataModel getSerializableModel(Range range) {
		if (wrappedKeys != null) {
			return this;
		} else {
			return null;
		}
	}

	/**
	 * This is helper method that is called by framework after model update. In
	 * must delegate actual database update to Data Provider.
	 */
	@Override
	public void update() {

	}
}
