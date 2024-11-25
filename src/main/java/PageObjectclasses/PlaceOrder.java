package PageObjectclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponenetsCollection.AbstractComponents;

public class PlaceOrder extends AbstractComponents {

WebDriver driver;

@FindBy(css="[placeholder='Select Country']") 
WebElement countryDynamicdropdown; 

@FindBy(css=".ta-results button span")
List<WebElement> dropdownitems;

@FindBy(css=".action__submit")
WebElement orderSubmitbutton;

	public PlaceOrder(WebDriver driver) {
		
		super(driver);
		
		PageFactory.initElements(driver, this);
		
		this.driver = driver;
		
	}
	
	public void fillShippingInformation() {
		
		Actions actions = new Actions(driver);
		
		actions.sendKeys(countryDynamicdropdown, "india").build().perform();
		
		WebElement dropdownresult = dropdownitems.stream().filter(r->r.getText().equalsIgnoreCase("india")).findFirst().orElse(null);
		
		dropdownresult.click();
		
	}
	
	public OrderConfimrationPage placeOrder() {
		orderSubmitbutton.click();
		return new OrderConfimrationPage(driver);
	}
	
}
