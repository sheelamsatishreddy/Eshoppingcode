package PageObjectclasses;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
WebDriver driver;
	
public LandingPage(WebDriver driver) {
		
	this.driver=driver;
	PageFactory.initElements(driver, this);
	
	
}

@FindBy(css="#userEmail")
WebElement username;

@FindBy(css="#userPassword")
WebElement password;

@FindBy(css="#login")
WebElement login;

@FindBy(xpath="//div[@id='toast-container']")
WebElement loginErrorMessage;
	
	
//	public void goTo() {
//		driver.get("https://rahulshettyacademy.com/client");	
//	}
	
	public ProductsCatalouge login(String user, String pass) {
		username.sendKeys(user);
		password.sendKeys(pass);
		login.click();
		return new ProductsCatalouge(driver);
	}
	
	public String getLoginErrorMessage() {
		return loginErrorMessage.getText();
	}
}
