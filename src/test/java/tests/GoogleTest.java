package tests;

import java.net.MalformedURLException;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.DriverFactory;

public class GoogleTest {

    @Test
    public void launchGoogle() {
        // Headed mode enabled explicitly
        WebDriver driver = null;
		try {
			driver = DriverFactory.getChromeDriver(false,"grid");
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        driver.get("https://www.google.com");

        System.out.println("Page Title: " + driver.getTitle());

        driver.quit();
    }
}