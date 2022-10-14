import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Deposit {

	
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
			}
				public void login () throws InterruptedException {
					
					
					String userID = "mngr447108";
					String password = "ymYmApA";
					driver.get("https://demo.guru99.com/v4");
					driver.manage().window().maximize();
					Thread.sleep(3000);
					if (!driver.findElement(By.id("gdpr-toggle")).getAttribute("aria-label").isEmpty()) {
						driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
						Thread.sleep(2000);
					}
					//driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
					
					driver.findElement(By.name("uid")).sendKeys(userID);
					driver.findElement(By.name("password")).sendKeys(password);
					driver.findElement(By.name("btnLogin")).click();
				}

				
			
		
			@Test
			@DisplayName("Check results on entering a valid information for all fields")
			public void deposit() throws InterruptedException {
			
				login();

			
				driver.findElement(By.linkText("Deposit")).click();
				
				String AccountNo = "112563";
				String Amount = "100";
				String Description = "incomes";
				
				driver.findElement(By.name("accountno")).sendKeys(AccountNo);
				driver.findElement(By.name("ammount")).sendKeys(Amount);
				driver.findElement(By.name("desc")).sendKeys(Description);
				driver.findElement(By.name("AccSubmit")).click();
				
				
				
}
			
			@Test
			@DisplayName("Check response on entering all fields and click on the reset button.")
			public void depositReset() throws InterruptedException {
				login();
				driver.findElement(By.linkText("Deposit")).click();
				String AccountNo = "112563";
				String Amount = "100";
				String Description = "incomes";
				
				driver.findElement(By.name("accountno")).sendKeys(AccountNo);
				driver.findElement(By.name("ammount")).sendKeys(Amount);
				driver.findElement(By.name("desc")).sendKeys(Description);
				driver.findElement(By.name("res")).click();
				assertTrue(driver.findElement(By.name("accountno")).getText().isEmpty());
				assertTrue(driver.findElement(By.name("ammount")).getText().isEmpty());
				assertTrue(driver.findElement(By.name("desc")).getText().isEmpty());
				
				
				
			}
	}
			
			

