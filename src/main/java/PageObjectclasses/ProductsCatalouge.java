package PageObjectclasses;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import AbstractComponenetsCollection.AbstractComponents;

public class ProductsCatalouge extends AbstractComponents {

	WebDriver driver;

	public ProductsCatalouge(WebDriver driver) {
		super(driver);
		PageFactory.initElements(driver, this);
		this.driver = driver;

	}

	@FindBy(css = ".card-body")
	List<WebElement> products;

	@FindBy(css = "[routerlink='/dashboard/cart']")
	WebElement cartButton;

	By addToCart = By.cssSelector(".card-body button:nth-of-type(2)");

	By toastMessage = By.cssSelector("#toast-container");
	
	By allproducts = By.cssSelector(".card-body");

	public List<WebElement> getProductsList() {
		waitUntilVisibilityOfElementLocatedBy(allproducts);
		return products;
	}

	public WebElement getProductByName(String ProductName) {

		WebElement prod = getProductsList().stream()
				.filter(p -> p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(ProductName)).findFirst()
				.orElse(null);
		return prod;

	}

	public void addToCart(String ProductName) {
		WebElement prod = getProductByName(ProductName);
		prod.findElement(addToCart).click();
		waitUntilInvisiblityOfElementLocatedBy(toastMessage);
	}

	public CartPage goToCartPage() {
		cartButton.click();
		return new CartPage(driver);
	}


}
