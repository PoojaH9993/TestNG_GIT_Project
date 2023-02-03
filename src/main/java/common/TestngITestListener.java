package common;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestngITestListener implements ITestListener {

	@Override
	public void onStart(ITestContext context) {
		System.out.println(context.getName() + " starts");
	}

	@Override
	public void onTestStart(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " starts");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " passed");
	}

	@Override
	public void onTestFailure(ITestResult result) {
		System.out.println(result.getMethod().getMethodName() + " failed");
	}

	@Override
	public void onFinish(ITestContext context) {
		System.out.println(context.getName() + " ends");
	}
}