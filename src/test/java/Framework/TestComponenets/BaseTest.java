package Framework.TestComponenets;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import PageObjectclasses.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	
static	ExtentSparkReporter reporter;
static	ExtentReports extent;
static	ExtentTest test;	
String name;
	
public WebDriver driver;
public LandingPage landingpage;
	public void initializeDriver() throws IOException {
	
		
		Properties prop = new Properties();
		
		FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//resources//GlobalData.properties");
		
		prop.load(fis);
		
		String browsername = System.getProperty("browser")!=null?System.getProperty("browser"):prop.getProperty("browser");
		
		String modes = System.getProperty("modes")!=null?System.getProperty("modes"):"Headed";
		
		//String browsername = prop.getProperty("browser");
		
		System.out.println(browsername);
		
		if(browsername.contains("chrome")) {
			
		ChromeOptions options = new ChromeOptions();
		//WebDriverManager.chromedriver().setup();
		options.addArguments("headless");
			
		if(modes.contains("Headed")) {
		driver = new ChromeDriver();
		System.out.println("In Headed");
		}
		else {
			driver = new ChromeDriver(options);
		}
				
		}
		else if(browsername.contains("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			driver=new FirefoxDriver();
			
		}
		else if(browsername.contains("edge")) {
			
			WebDriverManager.edgedriver();
			driver = new EdgeDriver();
			
		}
			
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
	public String getScreenshot(String testcaseName, WebDriver driver) throws IOException {
			
		driver.manage().window().setSize(null);
		
		
		TakesScreenshot ts = (TakesScreenshot) driver;
		
		//System.out.println(driver.getTitle());
		
		File source = 	ts.getScreenshotAs(OutputType.FILE);
		
		FileUtils.copyFile(source, new File(System.getProperty("user.dir")+"//Reports//"+ testcaseName+".png"));
		
		return System.getProperty("user.dir")+"//Reports//"+ testcaseName+".png";
		
	}
	
	@BeforeMethod(alwaysRun=true)
	public void launchApplication() throws IOException {
		
		initializeDriver();
		driver.get("https://rahulshettyacademy.com/client");	
		landingpage = new LandingPage(driver);
		System.out.println("In Before Method");

	}
	
	@AfterMethod(alwaysRun=true)
	public void closeDriver() {
		driver.close();
	}
	
	@BeforeSuite(alwaysRun = true)
	public void createreports() {
		 reporter = new ExtentSparkReporter(new File(System.getProperty("user.dir")+"//Reports//index.html"));
		reporter.config().setDocumentTitle("Test Results");
		reporter.config().setReportName("Regression Suite Results");
		extent = new ExtentReports();
		extent.attachReporter(reporter);
		System.out.println("In Before Suite");
		
		if(extent!=null) System.out.println("Extent is not null in BeforeSuite method");
		name ="Satish";
		
	}



		
}
