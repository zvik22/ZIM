package DataProviders;

import org.testng.annotations.DataProvider;

import Framework.ExcelUtils;
import Framework.GeneralUtility;

public class ExcelDataProvider {
	

	
	// All Xls

    @DataProvider(name = "getDataForTableTest")
    public Object[][] getDataForTableTest( ) throws Exception {

           // Excel excel = new Excel();                      
           String excelName = "TableTestInputs.xlsx";
           String sExcelPath = GeneralUtility.getUserDir(System.getProperty("user.dir")) + "\\InputExcelFiles\\" + excelName;
           String sExcelSheet = "sheet1";

           return ExcelUtils.getTableArray(sExcelPath, sExcelSheet);

    }
}
