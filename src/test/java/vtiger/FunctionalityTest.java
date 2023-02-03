package vtiger;

import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import Page.BaseTest;
import Page.VtigerDashboardPage;
import Page.VtigerLoginPage;
import Page.VtigerMarketingPage;
import Page.VtigerSearchBoxPage;
import utility.SeleniumUtility;

public class FunctionalityTest extends SeleniumUtility  {

	WebDriver driver;
	VtigerLoginPage vtigerLoginPage;
	VtigerDashboardPage vtigerDashboardPage;
	VtigerSearchBoxPage vtigerSearchBoxPage;
	VtigerMarketingPage vtigerMarketingPage;
	

	
	@DataProvider(name="readdatafromexcel")
	public Object[][] getData() {
		Object[][] data = new Object[1][2]; 
		data[0][0] = "admin";
		data[0][1] = "Test@123";
		return data;
	}
	@BeforeTest
	public void initializeBrowser() throws IOException {
//		driver = setUp(getPropertyFileData("browser"), getPropertyFileData("appUrl"));
//		driver = setUp("chrome", "https://demo.vtiger.com/vtigercrm/index.php");
	}

	@BeforeMethod
	public void initializeObjects() throws IOException {
		driver = setUp(getPropertyFileData("browser"), getPropertyFileData("appUrl"));
		vtigerLoginPage = new VtigerLoginPage(driver);
		vtigerDashboardPage = new VtigerDashboardPage(driver);
		vtigerSearchBoxPage = new VtigerSearchBoxPage(driver);
		vtigerMarketingPage = new VtigerMarketingPage(driver);
	}

	
	@Parameters({"username","password"})
	@Test
	public void TC_05_parameterization(String username,String password) throws Exception {
		vtigerLoginPage.loginwithParameter(username, password);
	}
	
	@Test
	public void TC_06_validateSearchBox() throws Exception {
		vtigerLoginPage.login();
		vtigerSearchBoxPage.visibleTextBoxCheck();
	}

	@Test
	public void TC_07_marketingLeadsCheck() throws Exception {
		vtigerLoginPage.login();
		vtigerMarketingPage.marketingLeadsLabel();
	}

	@Test
	public void TC_08_dataDrivenTestCase() throws IOException {
		String data = getCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1, 2);
		System.out.println("Print data:" + data);

		int row = getRowCount("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData");
		System.out.println("Print row count:" + row);

		int cell = getCellCount("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1);
		System.out.println("Print cell count:" + cell);

		setCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 0, 4, "Status");
		setCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1, 4, "PASS");

		String data1 = getCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 0, 4);
		System.out.println("Print data:" + data1);
		
		String data2=getCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1, 3);
		System.out.println("Print data:"+data2);
		
		
	}

	@AfterMethod
	public void logoutVtiger() {
		//vtigerLoginPage.logout();
		driver.close();
	}

	@AfterTest
	public void closeBrowser() {
		//tearDown();
	}
}
