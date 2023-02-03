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

public class LoginTest extends SeleniumUtility  {

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

	@Test
	public void TC_01_loginVtiger() throws Exception {
		vtigerLoginPage.login();
		vtigerDashboardPage.visbileTABCheck();
	}
	
	@Test
	public void TC_02_loginVtigerFromPropertiesFile() throws Exception {
		vtigerLoginPage.loginFromPropertiesFile();
		vtigerDashboardPage.visbileTABCheck();
	}
	
	@Test
	public void TC_03_loginVtigerFromExcelSheet() throws Exception {
		vtigerLoginPage.loginFromExcelSheet();
		vtigerDashboardPage.visbileTABCheck();
}

	@Test(dataProvider="readdatafromexcel")
	public void TC_04_loginVtigerwithDataProvider(String username,String password) throws Exception {
		vtigerLoginPage.loginwithDataProvider(username, password );
		vtigerDashboardPage.visbileTABCheck();
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
