package api.utilities;

import java.io.IOException;

public class DataProvider {
	/*
	 * This method reads all the data from an Excel sheet named "Sheet1" using the
	 * XLUtility class and returns it as a 2D String array, which is commonly used
	 * to provide test data in TestNG's @DataProvider.
	 */
	@org.testng.annotations.DataProvider(name = "Data")

	public String[][] getAllData() throws IOException {
		String path = System.getProperty("user.dir") + "//userdata//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet1");
		int colcount = xl.getCellCount("Sheet1", 1);

		String apidata[][] = new String[rownum][colcount];

		for (int i = 1; i <= rownum; i++) {
			for (int j = 0; j < colcount; j++) {
				apidata[i - 1][j] = xl.getCellData("Sheet1", i, j);
			}
		}

		return apidata;
	}

	/*
	 * This method reads all values from the second column of "Sheet1" in an Excel
	 * file and returns them as a 1D String array for use with the
	 * TestNG @DataProvider named "UserNames".
	 */
	@org.testng.annotations.DataProvider(name = "UserNames")
	public String[] getUserNames() throws IOException {
		String path = System.getProperty("user.dir") + "//userdata//Userdata.xlsx";
		XLUtility xl = new XLUtility(path);

		int rownum = xl.getRowCount("Sheet1");

		String apidata[] = new String[rownum];

		for (int i = 1; i <= rownum; i++) {
			apidata[i - 1] = xl.getCellData("Sheet1", i, 1);
		}

		return apidata;
	}

}
