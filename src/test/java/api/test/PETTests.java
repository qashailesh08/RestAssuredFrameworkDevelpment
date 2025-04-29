package api.test;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class PETTests {

	@Test
	public void petstoreAutomation() {
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.manage().window().maximize();
		driver.get("https://petstore.swagger.io/");
		// User Module
		// Accept Cookies
		WebElement weCookies = driver
				.findElement(By.xpath("//div[@id='ch2-dialog']//button[text()='Allow all cookies']"));
		JavascriptExecutor executor = (JavascriptExecutor)driver;
		executor.executeScript("arguments[0].click();", weCookies);

		// performing click action on post (Single User) request
		WebElement wePostUser = driver.findElement(By.xpath("//button[@fdprocessedid='tisw1a']/span"));
		// Before clicking on post request we need to scroll down to post request
		Actions actObj = new Actions(driver);
		actObj.moveToElement(wePostUser).build().perform();
		wePostUser.click();

		// click on Try it out
		WebElement weTryitout = driver.findElement(By.xpath("//button[@fdprocessedid='j0krrk']"));
		weTryitout.click();
		// Execute
		WebElement weExecute = driver.findElement(By.xpath("//button[text()='Execute']"));
		weExecute.click();

	}
}
