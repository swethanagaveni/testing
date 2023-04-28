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

public class primusbank {
WebDriver driver;
Properties con;
@BeforeTest
public void setup()throws Throwable
   {
	con=new Properties();
	con.load(new FileInputStream("primus.properties"));
	driver=new ChromeDriver();
	driver.manage().window().maximize();
	driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	driver.get(con.getProperty("url"));
	
	}
@Test
public void verifylogin()
{
	driver.findElement(By.xpath(con.getProperty("objuser"))).sendKeys("Admin");
	driver.findElement(By.xpath(con.getProperty("objpw"))).sendKeys("Admin");
	driver.findElement(By.xpath(con.getProperty("loginbtn"))).click();
	String expected_title="Primus BANK";
	String actul_title=driver.getTitle();
	if(expected_title.equalsIgnoreCase(actul_title))
	{
		Reporter.log("login succesful:::"+expected_title+"  "+actul_title);
		
	}else
	{
		Reporter.log("login fail:::"+expected_title+"  "+actul_title);
	}
	}
@AfterTest
public void teardown()
{
	driver.quit();
	}
}
