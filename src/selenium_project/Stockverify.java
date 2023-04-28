package selenium_project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.seleniumhq.jetty9.security.authentication.LoginAuthenticator;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Stockverify {
WebDriver driver;
Properties con;
@BeforeTest
public void setup() throws Throwable
{
	con=new Properties();
    con.load(new FileInputStream("stock.properties"));
    driver=new ChromeDriver();
    driver.manage().window().maximize();
    driver.get(con.getProperty("url"));
    driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    
	}
@Test
public void verifylogin()
{
	driver.findElement(By.xpath(con.getProperty("objrest"))).click();
	driver.findElement(By.xpath(con.getProperty("objuser"))).sendKeys("admin");
	driver.findElement(By.xpath(con.getProperty("objpass"))).sendKeys("master");
	driver.findElement(By.xpath(con.getProperty("loginbtn"))).click();
	String expected_title= "Dashboard « Stock Accounting";
	String actual_title=driver.getTitle();
	if(expected_title.equalsIgnoreCase(actual_title))
	{
		Reporter.log("Login sucess:: "+expected_title+"  "+actual_title);
		
	}else {
		Reporter.log("login fail:::"+expected_title+"  "+actual_title);
		
	}
	}
@AfterTest
public void teardown()
{
	driver.quit();
	}
}
