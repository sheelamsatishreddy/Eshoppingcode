package PageObjectclasses;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AbstractComponenetsCollection.AbstractComponents;


public class CartPage extends AbstractComponents {

WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;
	}
	
@FindBy(css=".cartSection h3")
WebElement productinCartPage;

@FindBy(css=".totalRow .btn-primary")
WebElement checkOutButton;
	
	public String validateProductInCartPage() {
		
		String productinCartPageText = productinCartPage.getText();
		
		return productinCartPageText;
	}

	public PlaceOrder gotoCheckOut() {
		checkOutButton.click();
		return new PlaceOrder(driver);
	}
}
