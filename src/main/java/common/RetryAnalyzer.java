package common;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
	int counter = 0;
	int retryLimit = 1;

//	Returns true if the test method has to be retried, false otherwise.
	@Override
	public boolean retry(ITestResult result) {
		if (counter < retryLimit) {
			counter++;
			return true;
		}
		return false;
	}
} 