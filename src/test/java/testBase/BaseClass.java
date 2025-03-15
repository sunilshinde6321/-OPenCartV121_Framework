package testBase;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;

import org.apache.logging.log4j.LogManager;  // log4j
import org.apache.logging.log4j.Logger;     // log4j
 
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;


public class BaseClass 
{
	public static WebDriver driver;
	public Logger logger;
	//private static final Logger logger = LogManager.getLogger(BaseClass.class);

	public Properties p;

	@BeforeClass(groups = {"sanity","Regression","Master","Datadriven"})
	@Parameters({"os","browser"})
	public void setUp(String os , String br) throws IOException
	{  
		FileReader file = new FileReader("./src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		

	 logger=LogManager.getLogger(this.getClass());


		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
			DesiredCapabilities capabilities = new DesiredCapabilities();
			capabilities.setBrowserName(br.toLowerCase());

			// os 
			if(os.equalsIgnoreCase("Windows"))
			{
				capabilities.setPlatform(Platform.WIN11);
			}
			else if(os.equalsIgnoreCase("mac"))
			{
				capabilities.setPlatform(Platform.MAC);
			}
			else
			{
				System.out.println("NOmatching OS");
				return;
			}

			// brower 
			switch (br.toLowerCase())
			{
			case "chrome": capabilities.setBrowserName("chrome"); break;
			case "edge": capabilities.setBrowserName("MicrosoftEdge"); break;
			default:System.out.println("Invalid browser"); return;	

			}
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
		}


		if (p.getProperty("execution_env").equalsIgnoreCase("local")) {
		    switch (br.toLowerCase()) {
		        case "chrome": driver = new ChromeDriver(); break;
		        case "edge": driver = new EdgeDriver(); break;
		        case "firefox": driver = new FirefoxDriver(); break;
		        default: System.out.println("Invalid browser"); return;
		    }
		}


		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(100));

		//		driver.get("https://tutorialsninja.com/demo/ ");
		driver.get(p.getProperty("appURL"));
		driver.manage().window().maximize();

	}


	@AfterClass(groups = {"sanity","Regression","Master","Datadriven"})
	public void tearDown()
	{
		driver.quit();
	}

	public String randomstring() 
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(5);

		return generatedstring;
	} 

	public String randomNumber() 
	{
		String generatednumber = RandomStringUtils.randomNumeric(10);

		return generatednumber;
	} 

	public String randomAlphnumaric() 
	{
		String generatedstring = RandomStringUtils.randomAlphabetic(3);
		String generatednumber = RandomStringUtils.randomNumeric(3);

		return(generatedstring + generatednumber);
	} 

	public String captureScreen(String tname) 
	{

		String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());

		TakesScreenshot takescScreenshot = (TakesScreenshot)driver;
		java.io.File sourcefile = takescScreenshot.getScreenshotAs(OutputType.FILE);

		String targetfilePath = System.getProperty("user.dir")+ "\\screenShot\\"+tname +"_"+timestamp + ".png";
		java.io.File targetfile = new java.io.File(targetfilePath);

		sourcefile.renameTo(targetfile);

		return targetfilePath;

	}

}
