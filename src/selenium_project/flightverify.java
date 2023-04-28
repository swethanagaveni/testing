package selenium_project;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class flightverify {
	WebDriver driver;
	Properties con;
	@BeforeTest
	public void setup()throws Throwable
	{
		con=new Properties();
		con.load(new FileInputStream("flight.properties"));
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.get(con.getProperty("url"));
		
	}
	@Test
	public void verifylogin()
	{
		driver.findElement(By.xpath(con.getProperty("emailid"))).sendKeys("swethanagaveni04@gmail.com");
		driver.findElement(By.xpath(con.getProperty("pw"))).sendKeys("password");
		driver.findElement(By.xpath(con.getProperty("lgbtn"))).click();
		String expected_title="Flight Reservation | Dashboard";
		String actual_title=driver.getTitle();
		if(expected_title.equalsIgnoreCase(actual_title))
		{
			Reporter.log("login sucessful:::"+expected_title+"  "+actual_title);
			
		}else
		{
			Reporter.log("login fail:::"+expected_title+"  "+actual_title);
		}
		
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
}
