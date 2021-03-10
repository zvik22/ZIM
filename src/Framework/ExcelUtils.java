package Framework;

 



import java.io.FileInputStream;

import java.io.FileNotFoundException;



import java.io.IOException;


 

import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;









import org.apache.poi.ss.usermodel.DataFormatter;


import org.apache.poi.ss.usermodel.Row;


import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;



public class ExcelUtils {

 

                private static final Logger log = LogManager.getLogger(ExcelUtils.class.getName());

               


 

                public static Object[][] getTableArray(String filePath, String sheetName) throws Exception {
                                String[][] tabArray = null;
                                try {
                                                XSSFSheet ExcelWSheet;
                                                XSSFWorkbook ExcelWBook;
                                                FileInputStream ExcelFile = new FileInputStream(filePath);
                                                // Access the required test data sheet
                                                ExcelWBook = new XSSFWorkbook(ExcelFile);
                                                ExcelWSheet = ExcelWBook.getSheet(sheetName);
                                                // String val =

                                                // formatter.formatCellValue(ExcelWSheet.getRow(col).getCell(row)); 

                                                int startRow = 1;
                                                int startCol = 1;
                                                int ci, cj;

                                                int totalRows = ExcelWSheet.getLastRowNum();

                                                Row r = ExcelWSheet.getRow(totalRows);
                                                int totalCols = r.getLastCellNum();
                                                totalCols = totalCols - 1;
                                                tabArray = new String[totalRows][totalCols];
                                                DataFormatter formatter = new DataFormatter();
                                                ci = 0;
                                                for (int i = startRow; i <= totalRows; i++, ci++) {
                                                                cj = 0;
                                                                for (int j = startCol; j <= totalCols; j++, cj++) {
                                                                                tabArray[ci][cj] = formatter.formatCellValue(ExcelWSheet.getRow(i).getCell(j));
                                                                                log.info(tabArray[ci][cj]);
                                                                }

                                                }

                                                ExcelWBook.close();

                                }

 

                                catch (FileNotFoundException e) {

                                                log.info("Could not read the Excel sheet");

                                                e.printStackTrace();

                                } catch (IOException e) {

                                                log.info("Could not read the Excel sheet");

                                                e.printStackTrace();

                                }

 

                                return (tabArray);

                }
}
               