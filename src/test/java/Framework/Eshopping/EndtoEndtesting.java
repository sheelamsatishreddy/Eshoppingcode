package Framework.Eshopping;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class EndtoEndtesting {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			String productName = "ZARA COAT 3";
			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
			driver.manage().window().maximize();
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
			
			driver.get("https://rahulshettyacademy.com/client");
			driver.findElement(By.cssSelector("#userEmail")).sendKeys("satish@gmail.com");
			driver.findElement(By.cssSelector("#userPassword")).sendKeys("Test@1234");
			driver.findElement(By.cssSelector("#login")).click();
			
			List<WebElement> products =  driver.findElements(By.cssSelector(".card-body"));
			WebElement product  = products.stream().filter(p->p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase("ZARA COAT 3")).findFirst().orElse(null);
			product.findElement(By.cssSelector(".card-body button:nth-of-type(2)")).click();
			wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector("#toast-container")));
			
			wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[routerlink='/dashboard/cart']")));
			driver.findElement(By.cssSelector("[routerlink='/dashboard/cart']")).click();
			String cartpagetext = driver.findElement(By.cssSelector(".cartSection h3")).getText();
			Assert.assertEquals(cartpagetext, productName);
			driver.findElement(By.cssSelector(".totalRow .btn-primary")).click();
			
			Actions action = new Actions(driver);
			action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")), "india").build().perform();
			List<WebElement> dropdownitems = driver.findElements(By.cssSelector(".ta-results button span"));
			WebElement dropdownresult = dropdownitems.stream().filter(r->r.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
			dropdownresult.click();
			driver.findElement(By.cssSelector(".action__submit")).click();
			
			String orderconfirmationtext = driver.findElement(By.cssSelector("td h1")).getText();
			Assert.assertEquals(orderconfirmationtext.toLowerCase(), "Thankyou for the order.".toLowerCase());
			List<WebElement>confirmpage = driver.findElements(By.cssSelector(".box label"));
			String orderId = confirmpage.get(1).getText();
			
			
			driver.findElement(By.cssSelector("[routerlink='/dashboard/myorders']")).click();
			List<WebElement> allOrders = driver.findElements(By.cssSelector(".table tbody th"));
			Boolean orderconfirmation = allOrders.stream().anyMatch(o->orderId.contains(o.getText()));			
			Assert.assertEquals(orderconfirmation, true);
			
		

	}

}
