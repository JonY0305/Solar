package tests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.time.Duration;

@Test(priority = 1)
public class TestCases extends SetUp {  // TestCases - sub-class, SetUp - super-class
    Long standardTime;
    String homePage = "https://qa-challenge.codesubmit.io/inventory.html";
    public void standard_user_login() throws InterruptedException {
        loginPage.loginCredentials("standard_user", "secret_sauce");
        Thread.sleep(4000);
        loginPage.clickLoginBtn();
        standardTime = System.currentTimeMillis();
//        Thread.sleep(4000);
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, homePage, "The site should work as expected for this user");
        loginPage.navBar();

    }

    @Test(priority = 2)
    public void locked_out_user_login() throws InterruptedException {
        loginPage.loginCredentials("locked_out_user", "secret_sauce");
        Thread.sleep(4000);
        loginPage.clickLoginBtn();
        Thread.sleep(4000);
        driver.navigate().refresh();
        String currentURL = driver.getCurrentUrl();
        Assert.assertEquals(currentURL, homePage, "User is locked out and should not be able to log in.");

    }

    @Test(priority = 3)
    public void problem_user_login() throws InterruptedException {
        loginPage.loginCredentials("problem_user", "secret_sauce");
        Thread.sleep(4000);
        loginPage.clickLoginBtn();
        Thread.sleep(2000);
        WebElement image4 = driver.findElement(By.tagName("img"));
        String srcValue = image4.getAttribute("src");
        Assert.assertNotNull(srcValue, "Images are not loading for this user."); // images are present and src is available // test passed
        Thread.sleep(3000);
        loginPage.navBar();
    }

    @Test(priority = 4)
    public void glitching_user_login(){
        loginPage.loginCredentials("performance_glitch_user", "secret_sauce");

        loginPage.clickLoginBtn();
        WebDriverWait waitTime = new WebDriverWait(driver, Duration.ofSeconds(6));
        waitTime.until(ExpectedConditions.elementToBeClickable(By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/button")));
        long estimate = System.currentTimeMillis();
        long total = estimate - standardTime;
        System.out.println("Page load time in milliseconds: " + total);
        Assert.assertEquals(standardTime, estimate, "This user has high loading times. Does the site still work as expected?");
    }

}
