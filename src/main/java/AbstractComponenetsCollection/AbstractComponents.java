package AbstractComponenetsCollection;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import PageObjectclasses.YourOrdersPage;

public class AbstractComponents {

WebDriverWait wait;
WebDriver driver;
@FindBy(css = "[routerlink='/dashboard/myorders']")
WebElement ordersButton;
	
	public AbstractComponents(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));	
	}

	public void waitUntilInvisiblityOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.invisibilityOfElementLocated(element));
	}
	
	public void waitUntilVisibilityOfElementLocatedBy(By element) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(element));
	}
	
	public YourOrdersPage goToOdersPage() {
		ordersButton.click();
		return new YourOrdersPage(driver);
	}
	
	
	
}
