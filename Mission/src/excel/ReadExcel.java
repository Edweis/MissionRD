package excel;

import java.awt.Component;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

public class ReadExcel {

	public ArrayList<ObjetExcel> read() {

		String aff = null, mod = null, opt = null, sec = null, nat = null, des = null, art = null, dim = null;
		double num = 0, vol = 0, poids = 0;
		ArrayList<ObjetExcel> liste_oe = new ArrayList<ObjetExcel>();

		try {

			String filename = SelectFile();
			FileInputStream file = new FileInputStream(new File(filename));

			// Create a workbook
			HSSFWorkbook classeur = new HSSFWorkbook(file);

			// Read the first page of the workbook
			HSSFSheet feuille = classeur.getSheetAt(0);

			// Create an iterator over the sheet
			Iterator<Row> rowIterator = feuille.iterator();
			while (rowIterator.hasNext()) {
				Row row = rowIterator.next();

				// Read every column of every row.
				Iterator<Cell> cellIterator = row.cellIterator();
				if (row.getRowNum() != 0) {
					while (cellIterator.hasNext()) {

						Cell cell = cellIterator.next();
						switch (cell.getColumnIndex()) {
						case 0:
							aff = cell.getStringCellValue();
							break;
						case 1:
							mod = cell.getStringCellValue();
							break;
						case 2:
							opt = cell.getStringCellValue();
							break;
						case 3:
							sec = cell.getStringCellValue();
							break;
						case 4:
							nat = cell.getStringCellValue();
							break;
						case 5:
							num = cell.getNumericCellValue();
							break;
						case 6:
							des = cell.getStringCellValue();
							break;
						case 7:
							art = cell.getStringCellValue();
							break;
						case 8:
							dim = cell.getStringCellValue();
							break;
						case 9:
							vol = cell.getNumericCellValue();
							break;
						case 10:
							poids = cell.getNumericCellValue();
							break;
						}
					}

					ObjetExcel oe = new ObjetExcel(aff, mod, opt, sec, nat, num, des, art, dim, vol, poids);
					liste_oe.add(oe);
				}
			}
			classeur.close();
			file.close();

			/*
			 * for (int k=0;k<liste_oe.size();k++){ liste_oe.get(k).affichage();
			 * }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
		return liste_oe;
	}

	/**
	 * Selects an excel file.
	 * 
	 * @return the full pathname of the file
	 */
	public String SelectFile() {

		String filename = null;
		Component frame = null;
		JFileChooser jfc = new JFileChooser();
		FileFilter ff = new FileFilter() {
			public boolean accept(File f) {
				if (f.isDirectory())
					return true;
				else if (f.getName().endsWith(".xls"))
					return true;
				else
					return false;
			}

			public String getDescription() {
				return "xls files";
			}
		};
		jfc.setFileFilter(ff);
		if (jfc.showDialog(frame, "Select") == JFileChooser.APPROVE_OPTION) {
			filename = jfc.getSelectedFile().getPath();
		}
		return filename;
	}
}