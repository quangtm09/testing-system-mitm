package com.bosch.rts.utilities;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Vector;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jboss.seam.annotations.Logger;
import org.jboss.seam.log.Log;

/**
 * The Class ParseExcelUtil.
 */
public class ParseExcelUtil {
	
	/** The log. */
	@Logger
	private transient static Log log;

	/**
	 * Read excel2003 file.
	 *
	 * @param file the file
	 * @return the vector
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Vector<Vector<HSSFCell>> readExcel2003File(UploadFileInfo file) throws IOException {

		Vector<Vector<HSSFCell>> cellVectorHolder = new Vector<Vector<HSSFCell>>();
		InputStream inputStream = null;
		try {
			inputStream = new ByteArrayInputStream(file.getData());
			POIFSFileSystem myFileSystem = new POIFSFileSystem(inputStream);

			HSSFWorkbook myWorkBook = new HSSFWorkbook(myFileSystem);
			int countSheets = myWorkBook.getNumberOfSheets();

			HSSFSheet mySheet = null;

			for (int i = 0; i < countSheets; i++) {
				mySheet = myWorkBook.getSheetAt(i);
				Iterator<Row> rowIter = mySheet.rowIterator();

				/* bypass the header row */
				rowIter.next();
				while (rowIter.hasNext()) {
					HSSFRow myRow = (HSSFRow) rowIter.next();
					Iterator<Cell> cellIter = myRow.cellIterator();
					Vector<HSSFCell> cellStoreVector = new Vector<HSSFCell>();
					while (cellIter.hasNext()) {
						HSSFCell myCell = (HSSFCell) cellIter.next();
						if (myCell.getColumnIndex() > RTSConstants.MAX_COLUMNS) {
							break;
						}

						if ((myCell.getColumnIndex() == 0 && myCell.toString().equals(""))) {
							inputStream.close();
							return cellVectorHolder;
						}
						cellStoreVector.addElement(myCell);
					}

					cellVectorHolder.addElement(cellStoreVector);
				}
			}
		} catch (Exception e) {
			log.error("Execute readExcel2003File method getting error------------------");
		} finally {
			if(inputStream != null){
				inputStream.close();
			}
		}
		return cellVectorHolder;
	}

	/**
	 * Read excel2007 file.
	 *
	 * @param file the file
	 * @return the vector
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public static Vector<Vector<XSSFCell>> readExcel2007File(UploadFileInfo file) throws IOException {
		final Vector<Vector<XSSFCell>> cellVectorHolder = new Vector<Vector<XSSFCell>>();
		InputStream inputStream = null;
		try {			
			inputStream = new ByteArrayInputStream(file.getData());			
			XSSFWorkbook  myWorkBook = new XSSFWorkbook(inputStream);
			int countSheets = myWorkBook.getNumberOfSheets();
			XSSFSheet mySheet = null;

			for (int i = 0; i < countSheets; i++) {
				mySheet = myWorkBook.getSheetAt(i);
				Iterator<Row> rowIter = mySheet.rowIterator();
				rowIter.next();
				while (rowIter.hasNext()) {
					XSSFRow myRow = (XSSFRow) rowIter.next();
					Iterator<Cell> cellIter = myRow.cellIterator();
					Vector<XSSFCell> cellStoreVector = new Vector<XSSFCell>();
					while (cellIter.hasNext()) {
						XSSFCell myCell = (XSSFCell) cellIter.next();
						if (myCell.getColumnIndex() > RTSConstants.MAX_COLUMNS) {
							break;
						}

						if ((myCell.getColumnIndex() == 0 && myCell.toString()
								.equals(""))) {
							inputStream.close();
							return cellVectorHolder;
						}
						cellStoreVector.addElement(myCell);
					}

					cellVectorHolder.addElement(cellStoreVector);
				}
			}

		} catch (Exception e) {
			log.error("Execute readExcel2007File method getting error------------------");
		}  finally {
			if(inputStream != null){
				inputStream.close();
			}
		}

		return cellVectorHolder;
	}
		
	/**
	 * Gets the cell value2003.
	 *
	 * @param currCell the curr cell
	 * @return the cell value2003
	 */
	public static Object getCellValue2003(HSSFCell currCell) {
		Object result = null;
		switch (currCell.getCellType()) {
		case Cell.CELL_TYPE_STRING:
			result = currCell.getStringCellValue().trim();
			break;

		case Cell.CELL_TYPE_FORMULA:
			break;

		case Cell.CELL_TYPE_NUMERIC:
			if (DateUtil.isCellDateFormatted(currCell)) {
				result = currCell.getDateCellValue();
			} else {
				result = currCell.getNumericCellValue();
			}
			break;

		case Cell.CELL_TYPE_BLANK:
			result = "";
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			result = currCell.getBooleanCellValue();
			break;
		}
		return result;

	}
	
	/**
	 * Gets the cell value2007.
	 *
	 * @param currCell the curr cell
	 * @return the cell value2007
	 */
	public static Object getCellValue2007(XSSFCell currCell) {
		Object result = null;
		switch (currCell.getCellType()) {
		case Cell.CELL_TYPE_STRING:			
			result = currCell.getStringCellValue().trim();
			break;

		case Cell.CELL_TYPE_FORMULA:
			break;

		case Cell.CELL_TYPE_NUMERIC:			
			 if (DateUtil.isCellDateFormatted(currCell)) {
				 result = currCell.getDateCellValue();
             } else {
            	 result = currCell.getNumericCellValue();
             }
             break;

		case Cell.CELL_TYPE_BLANK:
			result = "";
			break;

		case Cell.CELL_TYPE_BOOLEAN:
			result = currCell.getBooleanCellValue();
			break;
		}
		return result;

	}

}
