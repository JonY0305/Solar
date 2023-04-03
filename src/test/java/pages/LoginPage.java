package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class LoginPage {
    WebDriver driver;
    public LoginPage(WebDriver driver){
        this.driver = driver;
    }

    private By usernameInputField = By.cssSelector("#user-name");
    private By passwordInputField = By.cssSelector("#password");
    private By loginButton = By.cssSelector("#login-button");
    private By navBarBtn = By.xpath("/html/body/div/div/div/div[1]/div[1]/div[1]/div/div[1]/div/button");

    public void loginCredentials(String username, String password){
        driver.findElement(usernameInputField).sendKeys(username);
        driver.findElement(passwordInputField).sendKeys(password);
    }
    public void clickLoginBtn(){
        driver.findElement(loginButton).click();
    }
    public void navBar() throws InterruptedException {
        driver.findElement(navBarBtn).click();
        Thread.sleep(5000);
        driver.findElement(By.cssSelector("#logout_sidebar_link")).click();
    }

}
