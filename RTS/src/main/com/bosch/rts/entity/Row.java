package com.bosch.rts.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author KHB1HC
 *
 */
public class Row implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -3200843463337290337L;

	private List<Cell> actualDataList;

	private Integer rowIndex = 0;

	public Integer getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(Integer rowIndex) {
		this.rowIndex = rowIndex;
	}

	public List<Cell> getActualDataList() {
		return actualDataList;
	}

	public void setActualDataList(List<Cell> actualDataList) {
		this.actualDataList = actualDataList;
	}

	public void addCell(Cell cell) {
		if (null == actualDataList) {
			actualDataList = new ArrayList<Cell>();
		}
		actualDataList.add(cell);
	}

	public void addCell(int index, Cell cell) {
		if (null == actualDataList) {
			actualDataList = new ArrayList<Cell>();
		}
		if (index < actualDataList.size()) {
			actualDataList.set(index, cell);
		}
	}
}
