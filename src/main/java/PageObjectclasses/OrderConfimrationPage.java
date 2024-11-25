package PageObjectclasses;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import AbstractComponenetsCollection.AbstractComponents;

public class OrderConfimrationPage extends AbstractComponents {

WebDriver driver;	
static String orderId;

	public OrderConfimrationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		
	}
	
@FindBy(css="td h1")
WebElement orderConfirmation;

@FindBy(css=".box label")
List<WebElement>fetchorderId;


	
	public String getOrderconfirmationTextAndId() {
	
		String orderconfimrationtext = orderConfirmation.getText();

		return orderconfimrationtext;
		
		
	}
	
	public String fetchorderid() {
		
	 orderId = fetchorderId.get(1).getText();
		
		return orderId; 
	}


}



