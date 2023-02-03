package Page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utility.SeleniumUtility;

public class VtigerSearchBoxPage extends SeleniumUtility {
	WebDriver driver;

	public VtigerSearchBoxPage( WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(className="keyword-input") 
	private WebElement SEARCHTEXT_BOX;
	
	@FindBy(id="adv-search") 
	private WebElement ADVANCE_SEARCH;

	@FindBy(className="emptyRecordsContent")
	private WebElement SEARCH_RESULT;
	
	@FindBy(css="[class='close']")
	private WebElement CLOSE_RESULT;

	public void visibleTextBoxCheck(){
		boolean searchtextbox =verifyUIElementDisplayed(SEARCHTEXT_BOX);
		if(searchtextbox) {
			System.out.println("Search TextBox Is Displayed");
		}else {
			System.out.println("Search TextBox Is Not Displayed");
		}
		typeInput(SEARCHTEXT_BOX,"Task" );
		keyboardActions(SEARCHTEXT_BOX, "ENTER");
		String result=webElementText(SEARCH_RESULT);
		System.out.println("Result:"+result);
		if(result.equalsIgnoreCase("No records found")) {
			System.out.println("Test Case Pass");
		}else { 
			System.out.println("Test Case Fail");
		}
		clickOnElement(CLOSE_RESULT);
		
		}
	}

