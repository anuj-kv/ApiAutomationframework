package api.utilities;

import org.testng.annotations.DataProvider;

import java.io.IOException;

public class DataProviders {

    @DataProvider(name="Data")
    public Object[][] getAllData()throws IOException {
        String path = System.getProperty("user.dir")+"//testData//Book1.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        int colcount = xl.getCellCount("Sheet1", rownum);
        String[][] apidata = new String[rownum][colcount];
        for(int i=1; i<=rownum; i++){
            for(int j=0; j<colcount;j++){
                apidata[i-1][j] = xl.getCellData("Sheet1",i,j);
            }
        }
        return apidata;
    }

    @DataProvider(name="UserNames")
    public Object[][] getUserNames() throws IOException{
        String path = System.getProperty("user.dir")+"//testData//Book1.xlsx";
        XLUtility xl = new XLUtility(path);
        int rownum = xl.getRowCount("Sheet1");
        String[][] apidata = new String[rownum][1];
        for(int i=1; i<=rownum; i++){
            apidata[i-1][0] = xl.getCellData("Sheet1",i,1);
        }
        return apidata;
    }


}
