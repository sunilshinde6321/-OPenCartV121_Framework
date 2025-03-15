package utilities;

import java.io.IOException;
import org.testng.annotations.DataProvider;

public class DataProviders {

    @DataProvider(name = "LoginData")
    public String[][] getData() throws IOException {
        String path = ".\\TestData\\Opencart_LoginData.xlsx"; // Ensure correct file path
        ExcelUtlity xlutil = new ExcelUtlity(path);

        int totalrows = xlutil.getRowCount("Sheet1");
        int totalcols = xlutil.getCellCount("Sheet1", 1);

        String logindata[][] = new String[totalrows][totalcols];

        for (int i = 1; i <= totalrows; i++) 
        {
            for (int j = 0; j < totalcols; j++) 
            {
                logindata[i-1][j] = xlutil.getcellData("Sheet1", i, j); // Trim spaces
            }
        }

        return logindata;
    }
}
