package PageObjectclasses;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponenetsCollection.AbstractComponents;

public class YourOrdersPage extends AbstractComponents {

WebDriver driver;

	public YourOrdersPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

@FindBy(css=".table tbody th")
List<WebElement> allOrders;


	
	
	public Boolean CheckOrderinOrderHistoryPage() {
		
		Boolean orderconfirmation = allOrders.stream().anyMatch(o->OrderConfimrationPage.orderId.contains(o.getText()));
	
		return orderconfirmation;
	}
	
}
