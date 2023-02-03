package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.SeleniumUtility;

public class VtigerDashboardPage extends SeleniumUtility {
WebDriver driver;
	
	public  VtigerDashboardPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//*[@id='page']/nav/div[2]/div/div[2]/div/div[1]/a/h4")
	private static WebElement DASHBOARD_TAB;
	
	@FindBy(xpath="//*[@id='page']/div[4]/div/ul/li[2]/a/div/span[1]/strong")
	private static WebElement DEFAULT_TAB;
	
	public void visbileTABCheck() {
		Boolean x = verifyUIElementDisplayed(DASHBOARD_TAB);
		if (x ) {
			System.out.println("dashboard tab displayed");
		}
		else {
			System.out.println("dashboard tab NOT displayed");
		}
		
		Boolean y = verifyUIElementDisplayed(DEFAULT_TAB);
		if (y) {
			System.out.println("default tab displayed");
		}
		else {
			System.out.println("default tab NOT displayed");
		}
	}

}
