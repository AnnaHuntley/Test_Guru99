import static org.junit.Assert.assertEquals;

import java.util.Random;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

@TestMethodOrder(OrderAnnotation.class)
@DisplayName("Test Scenario to test new customer functionality")
public class NewCustomerTests {

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
		String passwordManager = "ymYmApA";
		
		driver.get("https://demo.guru99.com/v4");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		if (!driver.findElement(By.id("gdpr-toggle")).getAttribute("aria-label").isEmpty()) {
			driver.switchTo().frame("gdpr-consent-notice").findElement(By.id("save")).click();
			Thread.sleep(2000);
		}

		//Enter the UserID
		driver.findElement(By.name("uid")).sendKeys(userID);
		//Enter the password
		driver.findElement(By.name("password")).sendKeys(passwordManager);
		//Click on the button to submit
		driver.findElement(By.name("btnLogin")).click();
	}
	
	@Test
	@Order(1)
	@DisplayName("Check results on entering a valid information for all fields")
				public void addCustomer_happyPath() throws InterruptedException {
		
		login();
		//driver.get("https://demo.guru99.com/v4/manager/addcustomerpage.php");
		String customerName = "Adam";
		String gender = "male";
		String dob = "01/01/1991";
		String expectedDOB = "1991-01-01";
		String address = "47 Testing Road";
		String city = "Dublin";
		String state = "Dublin";
		String pin = "123456";
		String mobilNumber = "1234567";
		
		Random random = new Random();
		int randomEmail = random.nextInt(10000);
		System.out.println(randomEmail);  
		
		String email = "adam"+randomEmail+"@guru.ie";
		
		System.out.println(email);
		
		String customerPassword = "1234567";
		
		//Click on New Customer;
		driver.findElement(By.linkText("New Customer")).click();
		//driver.findElement(By.partialLinkText("Customer")).click();
		//driver.get("https://demo.guru99.com/v4/manager/addcustomerpage.php");
		//Enter the Customer name
		driver.findElement(By.name("name")).sendKeys(customerName);
		//Click on the gender (male)
		driver.findElement(By.cssSelector("body > table > tbody > tr > td > table > tbody > tr:nth-child(5) > td:nth-child(2) > input[type=radio]:nth-child(1)")).click();
		//Enter the DoB
		driver.findElement(By.id("dob")).sendKeys(dob);
		//Enter the Address
		driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[7]/td[2]/textarea")).sendKeys(address);
		//Enter the City
		driver.findElement(By.name("city")).sendKeys(city);
		//Enter the State
		driver.findElement(By.name("state")).sendKeys(state);
		//Enter the PIN
		driver.findElement (By.name("pinno")).sendKeys(pin);
		//Enter Mobile Number
		driver.findElement(By.name("telephoneno")).sendKeys(mobilNumber);
		//Enter E-mail
		driver.findElement(By.name("emailid")).sendKeys(email);
		//Enter Password
		driver.findElement (By.name("password")).sendKeys(customerPassword);
		//Click on Submit
		driver.findElement(By.name("sub")).click();
		
		//Check if the customer was created successfully 
		String expectedResults = "Customer Registered Successfully!!!";
		String actualResults = driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(1) > td > p")).getText();
		assertEquals(expectedResults,actualResults);
				
		//Check if all inputs is presented as expected
		//check the customer name
		assertEquals(customerName, driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[5]/td[2]")).getText());
		//Check gender
		assertEquals(gender, driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[6]/td[2]")).getText());
		//Check DOB
		assertEquals(expectedDOB, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(7) > td:nth-child(2)")).getText());
		//check address
		assertEquals(address, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(8) > td:nth-child(2)")).getText());
		//check city
		assertEquals(city, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(9) > td:nth-child(2)")).getText());
		//check state
		assertEquals(state, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(10) > td:nth-child(2)")).getText());
		//check pin
		assertEquals(pin, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(11) > td:nth-child(2)")).getText());
		//check mobile number
		assertEquals(mobilNumber, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(12) > td:nth-child(2)")).getText());
		//check email
		assertEquals(email, driver.findElement(By.cssSelector("#customer > tbody > tr:nth-child(13) > td:nth-child(2)")).getText());
		
		//store CustomerID to use this value for next tests 
		String CusId = driver.findElement(By.xpath("/html/body/table/tbody/tr/td/table/tbody/tr[4]/td[2]")).getText();
		System.out.println(CusId);
		
		
		
		//edit created customer 
		driver.findElement(By.linkText("Edit Customer")).click();
		driver.get("https://demo.guru99.com/v4/manager/EditCustomer.php");
		driver.findElement(By.name("cusid")).sendKeys(CusId);
		driver.findElement(By.name("AccSubmit")).click();
		
		/*assertEquals(customerName, driver.findElement(By.name("name")).getText());
		assertEquals(gender, driver.findElement(By.name("gender")).getText())
		assertEquals(expectedDOB, driver.findElement(By.name("dob")).getText());
		assertEquals(address, driver.findElement(By.name("addr")).getText());
		assertEquals(city, driver.findElement(By.name("city")).getText());
		assertEquals(state, driver.findElement(By.name("state")).getText());
		assertEquals(pin, driver.findElement(By.name("pinno")).getText());
		assertEquals(mobilNumber, driver.findElement(By.name("telephoneno")).getText());
		assertEquals(email, driver.findElement(By.name("emailid")).getText());*/
			
		String newAdress = "111 A Road";
		
		driver.findElement(By.name("addr")).sendKeys(newAdress);
		driver.findElement(By.name("sub")).click();
		
	
	}
	

	@Test
	@Order(2)
	@DisplayName (" Check the error message of all fields if we leave them blank")
	public void addCustomer_fieldsBlank () throws InterruptedException {
		
		login();
		driver.findElement(By.linkText("New Customer")).click();
		//driver.get("//demo.guru99.com/v4/manager/addcustomerpage.php");
		driver.findElement(By.name("sub")).click();
		String actualResultsAlert = driver.switchTo().alert().getText();
		String expectedResultsAlert = "please fill all fields"; 
		assertEquals(expectedResultsAlert,actualResultsAlert);
		driver.switchTo().alert().accept();
		
		
	}
}
