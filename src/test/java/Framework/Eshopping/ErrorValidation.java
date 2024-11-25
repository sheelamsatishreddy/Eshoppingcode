package Framework.Eshopping;
import org.testng.Assert;
import org.testng.annotations.Test;
import Framework.TestComponenets.BaseTest;
import Framework.TestComponenets.Retry;
import PageObjectclasses.CartPage;
import PageObjectclasses.ProductsCatalouge;

public class ErrorValidation extends BaseTest {
	
	@Test(groups= {"ErrorValidation"})
	public void VerifyLoginErrorMessage() {
		landingpage.login("Satish@gmail.com", "123");
		Assert.assertEquals(landingpage.getLoginErrorMessage(), "Incorrect email or password.");
		System.out.println("incorrectloginerror");
	
	}
	@Test(retryAnalyzer = Retry.class)
	public void verifyProductDisplayInCartPage() {
		System.out.println("verifyProductdisplayincartpage");
		String productName = "ZARA COAT 3";
		ProductsCatalouge productscatalouge = landingpage.login("automation07@gmail.com", "Test@1234");
		
		productscatalouge.addToCart(productName);
		CartPage cartpage = productscatalouge.goToCartPage();
		
		cartpage.validateProductInCartPage();
		Assert.assertEquals(cartpage.validateProductInCartPage(), "ZARA COAT 33");
		
	}
}
