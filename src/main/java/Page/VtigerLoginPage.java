package Page;

import java.io.IOException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utility.SeleniumUtility;

public class VtigerLoginPage extends SeleniumUtility {

	WebDriver driver;

	public VtigerLoginPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	private WebElement userNameField;

	@FindBy(id = "password")
	private WebElement pwdField;

	@FindBy(xpath = "//button[text()='Sign in']")
	private WebElement loginBtn;

	@FindBy(css = "span[class='fa fa-user']")
	private WebElement MEMBER_PROFILE;

	@FindBy(id = "menubar_item_right_LBL_SIGN_OUT")
	private WebElement SIGN_OUT;

	public void login() throws IOException, InterruptedException {

		typeInput(userNameField, "admin");
		typeInput(pwdField, "Test@123");
		clickOnElement(loginBtn);
		Thread.sleep(2000);
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Dashboard")) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
	}

	public void loginFromPropertiesFile() throws IOException, InterruptedException {
		typeInput(userNameField, getPropertyFileData("username"));
		typeInput(pwdField, getPropertyFileData("password"));
		clickOnElement(loginBtn);
		Thread.sleep(2000);
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Dashboard")) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
	}

	public void loginFromExcelSheet() throws IOException, InterruptedException {
		String username = getCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1, 2);
		System.out.println(username);
		String password = getCellData("src\\test\\resources\\DataSheets\\VtigerTestData.xlsx", "TestData", 1, 3);
		System.out.println(password);
		typeInput(userNameField, username);
		typeInput(pwdField, password);
		clickOnElement(loginBtn);
		Thread.sleep(2000);
		driver.navigate().refresh();
		Thread.sleep(2000);
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Dashboard")) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
	}
	
	
	public void logout() {
		clickOnElement(MEMBER_PROFILE);
		clickOnElement(SIGN_OUT);
	}

	public void loginwithDataProvider(String username, String password) throws InterruptedException {
		typeInput(userNameField, username);
		typeInput(pwdField, password);
		clickOnElement(loginBtn);
		Thread.sleep(2000);
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Dashboard")) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
	}
	public void loginwithParameter(String username, String password) throws InterruptedException {
		typeInput(userNameField, username);
		typeInput(pwdField, password);
		clickOnElement(loginBtn);
		Thread.sleep(2000);
		String title = driver.getTitle();
		if (title.equalsIgnoreCase("Dashboard")) {
			System.out.println("login successful");
		} else {
			System.out.println("login failed");
		}
	}
	
	
}
