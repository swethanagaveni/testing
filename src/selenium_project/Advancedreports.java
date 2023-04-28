package selenium_project;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Advancedreports {
   WebDriver driver;
   ExtentReports reports;
   ExtentTest test;
   @BeforeTest
      public void generatereports()
      {
        reports=new ExtentReports("./extentreports/demo.html");
      }
   @BeforeMethod
      public void setup()
      {
	   driver=new ChromeDriver();
	   driver.manage().window().maximize();
	   driver.get("https://www.google.com/");
      }
   @Test
     public void testcase1()
     {
	   test=reports.startTest("passtest");
	   test.assignAuthor("swetha");
	   test.assignCategory("functional testing");
	   String expected_title="google";
	   String actual_title=driver.getTitle();
	   if(expected_title.equalsIgnoreCase(actual_title))
	   {
		   test.log(LogStatus.PASS,"title is matching"+expected_title+" "+ actual_title);
	   }else
	   {
		   test.log(LogStatus.FAIL,"title is matching"+expected_title+" "+ actual_title);
	   }
     }
   @Test
      public void testcase2()
      {
	   test=reports.startTest("failtest");
	   test.assignAuthor("swetha");
	   test.assignCategory("functional testing");
	   String expected_title="gmail";
	   String actual_title=driver.getTitle();
	   if(expected_title.equalsIgnoreCase(actual_title))
	   {
		   test.log(LogStatus.PASS,"title is matching"+expected_title+" "+ actual_title);
	   }else
	   {
		   test.log(LogStatus.FAIL,"title is matching"+expected_title+" "+ actual_title);
	   }
   }
   @AfterTest
   public void teardown()
   {
	   reports.endTest(test);
	   reports.flush();
	   driver.quit();
   }
     
}
