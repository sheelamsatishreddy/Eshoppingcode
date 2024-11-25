package Framework.Eshopping;

import static org.testng.Assert.assertEquals;

import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Framework.Data.DataReader;
import Framework.TestComponenets.BaseTest;
import PageObjectclasses.CartPage;
import PageObjectclasses.LandingPage;
import PageObjectclasses.OrderConfimrationPage;
import PageObjectclasses.PlaceOrder;
import PageObjectclasses.ProductsCatalouge;
import PageObjectclasses.YourOrdersPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class Potest extends BaseTest {
	

	
	
	@Test(dataProvider="getData")
	public  void placeOrder(HashMap<String, String>input) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("PO Test 1");
			//String productName = "ZARA COAT 3";
			ProductsCatalouge productscatalouge = landingpage.login(input.get("username"),input.get("password"));
			productscatalouge.addToCart(input.get("productName"));
			CartPage cartpage = productscatalouge.goToCartPage();
			
			cartpage.validateProductInCartPage();
			Assert.assertEquals(cartpage.validateProductInCartPage(), input.get("productName"));
			PlaceOrder placeorder = cartpage.gotoCheckOut();
						
			placeorder.fillShippingInformation();
			OrderConfimrationPage orderconfirmationpage = placeorder.placeOrder();
			
			String orderconfirmationtext = orderconfirmationpage.getOrderconfirmationTextAndId();
			orderconfirmationpage.fetchorderid();
			Assert.assertEquals(orderconfirmationtext.toLowerCase(), "Thankyou for the order.".toLowerCase());
			
			YourOrdersPage yourorders = orderconfirmationpage.goToOdersPage();
			Assert.assertEquals(yourorders.CheckOrderinOrderHistoryPage(), true);
			System.out.println("Execution Successful. Thanks a lot!");	
			
			
			
	}
	
	
	@Test(dependsOnMethods={"placeOrder"})
	public void verifyProductDisplayInOrderPage() {
		System.out.println("PO Test 2");
		ProductsCatalouge productscatalouge = landingpage.login("satish@gmail.com", "Test@1234");
		YourOrdersPage yourorderspage = productscatalouge.goToOdersPage();
		Assert.assertEquals(yourorderspage.CheckOrderinOrderHistoryPage(), true);
		System.out.println("verifyProductdisplayinorderspage");
	}

//	@DataProvider
//	public Object[][] getData(){
//		
//		return new Object[][] {{"satish@gmail.com","Test@1234","ZARA COAT 3"},{"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL"}};
//		
//		
//	}
	
//	@DataProvider
//	public Object[][] getData(){
//		
//		HashMap<String, String> map = new HashMap<String, String>();
//		map.put("username", "satish@gmail.com");
//		map.put("password", "Test@1234");
//		map.put("productName", "ZARA COAT 3");
//		
//		
//		HashMap<String, String> map1 = new HashMap<String, String>();
//		map1 .put("username", "shetty@gmail.com");
//		map1.put("password", "Iamking@000");
//		map1.put("productName", "ADIDAS ORIGINAL");
//		
//		return new Object[][] {{map},{map1}};
//		
//	}
	
	
	@DataProvider
	public Object[][] getData() throws IOException{
		
		List<HashMap<String, String>>data = DataReader.getJsonDatatoMap();
		
		return new Object[][] {{data.get(0)},{data.get(1)}}; 
	}
	
	
	
	

}
