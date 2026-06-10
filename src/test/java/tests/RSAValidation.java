package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;
import pages.DriverFactory;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class RSAValidation {

    @Test
    public void validateRSA() {

        //Datbase connection.
        //Connect the Rest Api
        //Validate the throgh selenium

        //String url = "jdbc:mysql://localhost:3306/mycompany";
        String url = "jdbc:mysql://localhost:3306/mycompany";
        String username = "root";
        String password = "Babusahu123";

        String uesremail = null;
        String userpwd = null;

        try {
            Connection con = DriverManager.getConnection(url, username, password);
            System.out.println("Database connection successful.");

            Statement stamt = con.createStatement();
            ResultSet rs = stamt.executeQuery("select um.email, rd.password from RegistrationDetails rd inner join UserNames um on rd.id_number = um.id_number");


            while (rs.next()) {
                rs.getString("email");
                rs.getString("password");
                if (rs.getString("email").contains("johndoe")) {
                    uesremail = rs.getString("email");
                    userpwd = rs.getString("password");
                    break;
                }
            }
            System.out.println("email: " + uesremail);
            System.out.println("password: " + userpwd);

            con.close();

        } catch (Exception e) {

            System.out.println("Database connection failed: " + e.getMessage());
        }
        //Rest API connection and validation logic would go here.

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Build the request (headers/body) first, then call when().post(...)
        Response res = given().log().all().header("Content-Type","application/json").body(
                "{\n" +
                        "    \"userEmail\": \"" + uesremail + "\",\n" +
                        "    \"userPassword\": \"" + userpwd + "\"\n" +
                        "}"
        ).when().post("/api/ecom/auth/login").then().log().all().extract().response();

        JsonPath path = new JsonPath(res.asString());

        String userId = path.get("userId");
        System.out.println("User ID: " + userId);

        WebDriver driver = null;
        try {
            // Headed mode enabled explicitly
            driver = DriverFactory.getChromeDriver(false,"grid");

            // Only interact with the driver if it was created successfully
            driver.get("https://rahulshettyacademy.com/client/#/auth/login");
            driver.findElement(By.xpath("//*[contains(@id,'userEmail')]")).sendKeys(uesremail);
            driver.findElement(By.xpath("//*[contains(@id,'userPassword')]")).sendKeys(userpwd);
            driver.findElement(By.xpath("//*[contains(@id,'login')]")).click();
            
            Thread.sleep(3000);
            
           // WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(20));
           // wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(),'Automation Practice')]")));
            
            List<WebElement> productsEle = driver.findElements(By.xpath("//*[contains(@class,'card-body')]//b"));
            
            List<String> productsList = new ArrayList<>();
            
            productsEle.stream().forEach(el -> productsList.add(el.getText()));
            
            System.out.println("Product List:-- " +productsList);
            

        } catch (Exception e) {
            System.out.println("Selenium Grid connection failed: " + e.getMessage());
        } finally {
            if (driver != null) {
                try {
                    driver.quit();
                } catch (Exception ignore) {
                    // ignore shutdown errors
                }
            }
        }
    }
}