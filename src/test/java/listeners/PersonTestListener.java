package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.log4testng.Logger;

import java.util.logging.LogManager;


public class PersonTestListener implements ITestListener {
    private static final Logger log = Logger.getLogger(PersonTestListener.class);
    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("test: " + result.getInstanceName() + " is started");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        System.out.println("test: " + result.getInstanceName() + " is successful");;
    }

//    @Override
//    public void onTestFailure(ITestResult result) {
//        ITestListener.super.onTestFailure(result);
//    }
//
//    @Override
//    public void onTestSkipped(ITestResult result) {
//        ITestListener.super.onTestSkipped(result);
//    }
//
//    @Override
//    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
//        ITestListener.super.onTestFailedButWithinSuccessPercentage(result);
//    }
//
//    @Override
//    public void onTestFailedWithTimeout(ITestResult result) {
//        ITestListener.super.onTestFailedWithTimeout(result);
//    }
//
//    @Override
//    public void onStart(ITestContext context) {
//        ITestListener.super.onStart(context);
//    }
//
//    @Override
//    public void onFinish(ITestContext context) {
//        ITestListener.super.onFinish(context);
//    }
}
