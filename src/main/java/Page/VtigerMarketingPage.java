package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.SeleniumUtility;

public class VtigerMarketingPage extends SeleniumUtility {
	WebDriver driver;

	public VtigerMarketingPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "div#appnavigator>div>span")
	private WebElement HAMBURGER_BTN;

	@FindBy(css = "div#MARKETING_modules_dropdownMenu")
	private WebElement MARKETING_LABEL;

	@FindBy(css = "a[href='index.php?module=Leads&view=List&app=MARKETING']")
	private WebElement LEADS_LABEL;

	@FindBy(css = "input[name='firstname']")
	private WebElement FIRST_NAME;

	@FindBy(css = "input[name='lastname']")
	private WebElement LAST_NAME;

	@FindBy(className = "s2-btn-text")
	private WebElement SEARCH_BTN;
	
	@FindBy(className = "emptyRecordsContent")
	private WebElement SEARCH_RECORD;
	
	

	public void marketingLeadsLabel() {
		clickOnElement(HAMBURGER_BTN);
		clickOnElement(MARKETING_LABEL);
		clickOnElement(LEADS_LABEL);
		clickOnElement(FIRST_NAME);
		typeInput(FIRST_NAME, "MIKE");
		clickOnElement(LAST_NAME);
		typeInput(LAST_NAME, "JOSE");
		clickOnElement(SEARCH_BTN);
		
		String result_record=webElementText(SEARCH_RECORD);
		System.out.println("Result:"+result_record);
		if(result_record.contains("No Leads found")) {
			System.out.println("Test Case Pass");
		}else { 
			System.out.println("Test Case Fail");
		}

	}
}