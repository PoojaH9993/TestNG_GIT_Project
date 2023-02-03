package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SeleniumUtility extends ExcelUtility{
//	It includes all the commonly used methods

	public static WebDriver driver;
	public static Actions actions;
	public static WebDriverWait wait;

	public WebDriver setUp(String browser, String url) {
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
		}
		if (browser.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			driver = new EdgeDriver();
		}
		if (browser.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver = new FirefoxDriver();
		}
		if (browser.equalsIgnoreCase("safari")) {
			WebDriverManager.safaridriver().setup();
			driver = new SafariDriver();
		}

		driver.manage().window().maximize();

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

		driver.get(url);

		return driver;
	}

	public void mouseHover(String hoverOpe, WebElement element, Integer x, Integer y) {
		actions = new Actions(driver);
		if (hoverOpe.equalsIgnoreCase("targetElement"))
			actions.moveToElement(element).perform();
		if (hoverOpe.equalsIgnoreCase("targetElementWithOffsets"))
			actions.moveToElement(element, x, y).perform();
		if (hoverOpe.equalsIgnoreCase("Offsets"))
			actions.moveByOffset(x, y).perform();
	}

	public void mouseDoubleClick(WebElement element) {
		actions = new Actions(driver);
		actions.doubleClick(element).build().perform();
	}

	public void mouseRightClick(WebElement element) {
		actions = new Actions(driver);
		actions.contextClick(element).build().perform();
	}

	public void mouseClickHoldRelease(String releaseOpe, WebElement sourceElement, WebElement targetElement, Integer x,
			Integer y) {
		actions = new Actions(driver);
		if (releaseOpe.equalsIgnoreCase("sourceAndTarget")) {
			actions.clickAndHold(sourceElement).build().perform();
//			actions.moveToElement(targetElement).perform();
			actions.release(targetElement).build().perform();
		}
		if (releaseOpe.equalsIgnoreCase("sourceAndOffset")) {
			actions.clickAndHold(sourceElement).build().perform();
			actions.moveByOffset(x, y).perform();
			actions.release().perform();
		}
	}

	public void dragAndDrop(String dragDropOpe, WebElement sourceElement, WebElement targetElement, Integer x,
			Integer y) {
		actions = new Actions(driver);
		if (dragDropOpe.equalsIgnoreCase("sourceAndTarget"))
			actions.dragAndDrop(sourceElement, targetElement).build().perform();
		if (dragDropOpe.equalsIgnoreCase("sourceAndOffset"))
			actions.dragAndDropBy(sourceElement, x, y).build().perform();
	}

	public void getScreenshot(String path, String name, String format) {
		TakesScreenshot ts = (TakesScreenshot) driver;
		File file = ts.getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(file, new File("./Screenshots/" + name + "." + format + ""));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void typeInput(WebElement element, String input) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.clear();
		element.sendKeys(input);
	}

	public void clickOnElement(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(element));
		element.click();
	}

	public String getPropertyFileData(String key) throws IOException {
		FileInputStream fis = new FileInputStream("./src/main/resources/staticData.properties");
		Properties prop = new Properties();
		prop.load(fis);
		return prop.getProperty(key);
	}

	public boolean verifyUIElementDisplayed(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.isDisplayed();
	}

	public void keyboardActions(WebElement element, String key) {
		switch (key) {
		case "ENTER":
			element.sendKeys(Keys.ENTER);
			break;

		case "TAB":
			element.sendKeys(Keys.TAB);
			break;
		}
	}
	
	public String webElementText(WebElement element) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.getText();
	}

	public void tearDown() {
		driver.close();
	}

}