package tests;

import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import pages.LoginPage;

public class SetUp {

    WebDriver driver;
    LoginPage loginPage;
    public String baseURL = "https://qa-challenge.codesubmit.io";

    @BeforeTest // makes method run before the first test
    public void setDriver() {
        System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);

        driver.manage().window().setPosition(new Point(-1000, 0)); // DON'T COPY

        driver.manage().window().maximize();

        driver.get(baseURL);
        loginPage = new LoginPage(driver);

    }

    @AfterTest
    public void closeDriver() {
        //   driver.close(); // will close the current (active) tab
//        driver.quit(); // closes whole browser
    }
}
