import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;


@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test Scenario to test login functionality")
public class LoginTests { 
	
	
	//Declaration of the object webdriver
	public static WebDriver driver = null;
	
	
	@BeforeAll
	public static void beforeAll() {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
	
	}
	
	@AfterAll
	public static void afterAll() {
		driver.close();
		//driver.quit();
	}
	
	//@BeforeEach
	//@AfterEach
	
	@Test
	@Order(1)
	@DisplayName("Check results on entering a valid userID and password")
	public void login_happyPath() throws InterruptedException {
		
		//Data to be used in this test
		String userID = "mngr447108";
		String password = "ymYmApA";
		
		//Open the URL demo.guru99.com/v4
		driver.get("https://demo.guru99.com/v4");
		driver.manage().window().maximize();
		Thread.sleep(3000); //wait for 2 seconds to execute the next line of code
		
		driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
		
		Thread.sleep(2000);
		//Enter the UserID
		driver.findElement(By.name("uid")).sendKeys(userID);
		
		//Enter the password
		driver.findElement(By.name("password")).sendKeys(password);
		
		//Click on the button to submit
		driver.findElement(By.name("btnLogin")).click();
		
		//Check if the welcome message is displayed
		String actualResults = driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(2) > td > marquee")).getText();
		String expectedResults = "Welcome To Manager's Page of Guru99 Bank";
		
		assertEquals(expectedResults, actualResults);
		//assertTrue(actualResults.contains(expectedResults));
	
	}
	
	@Test
	@Order(2)
	@DisplayName("Check results on entering invalid userID")
	public void invalidUserID_Login() throws InterruptedException {
	
		driver.get("https://demo.guru99.com/v4");
		driver.manage().window().maximize();
		Thread.sleep(3000); 
		
		if (driver.findElement(By.tagName("iframe")).isDisplayed()) {
			driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			Thread.sleep(2000);
		}
		 
		
		String userID = "aaaa";
		String password = "ymYmApA";
		//enter invalid userID
		driver.findElement(By.name("uid")).sendKeys(userID);
		
		driver.findElement(By.name("password")).sendKeys(password);
		
		//Click on submit
		driver.findElement(By.name("btnLogin")).click();
		
		//check the message on the alert: User or Password is not valid
		String actualResults = driver.switchTo().alert().getText();
		String expectedResults = "User or Password is not valid"; 
		
		assertEquals(expectedResults,actualResults);
		
		
		//Click on OK in the alert
		driver.switchTo().alert().accept();
		
	}
	
	
	@Test
	@Order(3)
	@DisplayName("Check response when a User ID is Empty")
	public void allFieldsBlank_login() throws InterruptedException {
		
	
		String password = "aqAgehe";
		
		//Open the URL
		driver.get("https://demo.guru99.com/v4");
		driver.manage().window().maximize();
		Thread.sleep(3000); 
		
		
		
		if (driver.findElement(By.tagName("iframe")).isDisplayed()) {
			driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();

		}
		
		driver.findElement(By.name("uid")).click();
				
		//Enter the password
		driver.findElement(By.name("password")).sendKeys(password);
		
		String actualResultsBlank = driver.findElement(By.id("message23")).getText();
		String expectedResultsBlank = "User-ID must not be blank";
		assertEquals(expectedResultsBlank,actualResultsBlank);
	
		
		//Click on submit
		driver.findElement(By.name("btnLogin")).click();
		
	
		String actualResultsAlert = driver.switchTo().alert().getText();
		String expectedResultsAlert = "User or Password is not valid"; 
		
		assertEquals(expectedResultsAlert,actualResultsAlert);
				
		//Click on OK in the alert
		driver.switchTo().alert().accept();
		
	}
	
	
}
