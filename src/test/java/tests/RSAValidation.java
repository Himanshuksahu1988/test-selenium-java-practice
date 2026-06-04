package tests;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import static io.restassured.RestAssured.given;

public class RSAValidation {

    @Test
    public void validateRSA() {

        //Datbase connection.
        //Connect the Rest Api
        //Validate the throgh selenium

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

        // Build the request (headers/body) first, then call when().post(...)
        given()
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "    \"userEmail\": \"" + uesremail + "\",\n" +
                        "    \"userPassword\": \"" + userpwd + "\"\n" +
                        "}")
                .when()
                .post("https://rahulshettyacademy.com/api/ecom/auth/login")
                .then()
                .log().all();
    }
}