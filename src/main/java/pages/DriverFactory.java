package pages;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class DriverFactory {

    public static WebDriver getChromeDriver() throws MalformedURLException {
        return getChromeDriver(false,"grid");
    }

    public static WebDriver getChromeDriver(boolean headless, String type) throws MalformedURLException {
        // Setup WebDriver Manager
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();

        // Explicitly disable headless mode for headed execution
        if (headless) {
            options.addArguments("--headless=new");
        } else {
            // Ensure headless is NOT enabled
            options.addArguments("--no-first-run", "--no-default-browser-check");
            options.setExperimentalOption("excludeSwitches", new String[]{"enable-automation"});
            options.setExperimentalOption("useAutomationExtension", false);

            // Optional: Start maximized for better visibility
            options.addArguments("--start-maximized");

            // Disable notifications
            options.addArguments("--disable-notifications");

            // Remove the info bar messages
            options.addArguments("--disable-infobars");
        }

        // Common arguments for both headed and headless
        options.addArguments("--no-sandbox", "--disable-dev-shm-usage");
        
        if(type.equals("grid")){
        	 return new RemoteWebDriver(new URL("http://localhost:4444") , options);
        }else {
        	 return new ChromeDriver(options);
        }

       
    }
}

