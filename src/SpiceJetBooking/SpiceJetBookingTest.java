package SpiceJetBooking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class SpiceJetBookingTest {
	
	 WebDriver driver;

	    @BeforeMethod
	    public void setUp() {
	        // Set up WebDriver for Chrome
	        WebDriverManager.chromedriver().setup();
	        ChromeOptions options = new ChromeOptions();
	       
	        driver = new ChromeDriver(options);
	        
	        // Open the SpiceJet website
	        driver.get("https://www.spicejet.com/");
	    }

	    @Test(priority = 1)
	    public void testSignUpPage() {
	        // Navigate to the Sign-Up Page
	        WebElement signUpButton = driver.findElement(By.xpath("//a[contains(text(),'Sign up')]"));
	        signUpButton.click();

	        // Fill out mandatory fields
	        WebElement nameField = driver.findElement(By.id("txtFullName"));
	        WebElement emailField = driver.findElement(By.id("txtEmail"));
	        WebElement phoneField = driver.findElement(By.id("txtPhone"));
	        nameField.sendKeys("Divya Vishal");
	        emailField.sendKeys("dbharathi11997@gmail.com");
	        phoneField.sendKeys("8124394266");
  
	        // Fill out non-mandatory fields (if applicable)
	        WebElement dobField = driver.findElement(By.id("txtDOB"));
	        dobField.sendKeys("25/06/1997");
	        
	        // Submit the form (assumed to be a SignUp button with id 'btnSubmit')
	        WebElement submitButton = driver.findElement(By.id("btnSubmit"));
	        submitButton.click();

	        // Validate successful sign-up (could be a confirmation message or redirection)
	        Assert.assertTrue(driver.getPageSource().contains("Welcome"), "Sign-up failed!");
	    }

	    @Test(priority = 2)
	    public void testLogin() {
	        // Click Login button on top of the page
	        WebElement loginButton = driver.findElement(By.xpath("//a[contains(text(),'Login')]"));
	        loginButton.click();

	        // Fill out login fields with dummy credentials
	        WebElement emailField = driver.findElement(By.id("txtEmail"));
	        WebElement passwordField = driver.findElement(By.id("txtPassword"));
	        emailField.sendKeys("dbharathi11997@gmail.com");
	        passwordField.sendKeys("Ziya@1000");

	        // Submit the login form
	        WebElement loginSubmitButton = driver.findElement(By.id("btnLogin"));
	        loginSubmitButton.click();

	        // Validate successful login (this could be checking for a user-specific page)
	        Assert.assertTrue(driver.getPageSource().contains("Welcome"), "Login failed!");
	    }

	    @Test(priority = 3)
	    public void testFlightSearchOneWay() {
	        // Select One-Way flight
	        WebElement oneWayRadioButton = driver.findElement(By.id("OneWay"));
	        oneWayRadioButton.click();

	        // Enter Origin and Destination
	        WebElement originField = driver.findElement(By.id("FromSector"));
	        WebElement destinationField = driver.findElement(By.id("ToSector"));
	        originField.sendKeys("Delhi");
	        destinationField.sendKeys("Bengaluru");

	        // Select date
	        WebElement dateField = driver.findElement(By.id("txtJourneyDate"));
	        dateField.sendKeys("11/14/2024");

	        // Search flights
	        WebElement searchButton = driver.findElement(By.id("search_btn"));
	        searchButton.click();
	        
	        // Validate that results appear
	        Assert.assertTrue(driver.getPageSource().contains("Flight Results"), "No flights found!");
	    }

	    @Test(priority = 4)
	    public void testFlightSearchRoundTrip() {
	        // Select Round Trip flight
	        WebElement roundTripRadioButton = driver.findElement(By.id("RoundTrip"));
	        roundTripRadioButton.click();

	        // Enter Origin and Destination
	        WebElement originField = driver.findElement(By.id("FromSector"));
	        WebElement destinationField = driver.findElement(By.id("ToSector"));
	        originField.sendKeys("Bengaluru");
	        destinationField.sendKeys("Delhi");

	        // Select dates
	        WebElement onwardDateField = driver.findElement(By.id("txtJourneyDate"));
	        WebElement returnDateField = driver.findElement(By.id("txtReturnDate"));
	        onwardDateField.sendKeys("11/25/2024");
	        returnDateField.sendKeys("11/28/2024");

	        // Search flights
	        WebElement searchButton = driver.findElement(By.id("search_btn"));
	        searchButton.click();

	        // Validate that results appear
	        Assert.assertTrue(driver.getPageSource().contains("Flight Results"), "No flights found!");
	    }

	    @Test(priority = 5)
	    public void testSelectFlightAndProceedToBooking() {
	        // Select the first available flight (click on the Select button)
	        WebElement selectButton = driver.findElement(By.xpath("//button[contains(text(),'Select')]"));
	        selectButton.click();

	        // Proceed to the booking page
	        WebElement proceedButton = driver.findElement(By.id("btnProceed"));
	        proceedButton.click();

	        // Validate successful navigation to booking page
	        Assert.assertTrue(driver.getCurrentUrl().contains("booking"), "Booking page not reached!");
	    }

	    @Test(priority = 6)
	    public void testBookingForm() {
	        // Fill out passenger details
	        WebElement passengerNameField = driver.findElement(By.id("txtPassengerName"));
	        passengerNameField.sendKeys("DivyaBharathi");

	        // Fill out payment information (dummy)
	        WebElement cardNumberField = driver.findElement(By.id("txtCardNumber"));
	        cardNumberField.sendKeys("5111111111111111");

	        WebElement cardExpiryField = driver.findElement(By.id("txtExpiryDate"));
	        cardExpiryField.sendKeys("12/25");

	        WebElement cardCVVField = driver.findElement(By.id("txtCVV"));
	        cardCVVField.sendKeys("123");

	        // Submit the booking form
	        WebElement bookNowButton = driver.findElement(By.id("btnBookNow"));
	        bookNowButton.click();

	        // Validate booking success
	        Assert.assertTrue(driver.getPageSource().contains("Booking Confirmation"), "Booking failed!");
	    }

	    @Test(priority = 7)
	    public void validateBookingPageFields() {
	        // Check availability of key fields on booking page
	        WebElement checkInLink = driver.findElement(By.linkText("Check-in"));
	        WebElement flightStatusLink = driver.findElement(By.linkText("Flight Status"));
	        WebElement manageBookingLink = driver.findElement(By.linkText("Manage Booking"));

	        Assert.assertTrue(checkInLink.isDisplayed(), "Check-in link not found!");
	        Assert.assertTrue(flightStatusLink.isDisplayed(), "Flight Status link not found!");
	        Assert.assertTrue(manageBookingLink.isDisplayed(), "Manage Booking link not found!");
	    }

	    @AfterMethod
	    public void tearDown() {
	        // Close the browser after the test is complete
	        driver.quit();
	    }
	}	


