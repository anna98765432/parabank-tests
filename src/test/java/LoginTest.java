import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest {
    WebDriver driver = new ChromeDriver();

    @Test
    public void shouldLogin(){
        driver.get("http://parabank.parasoft.com");
        driver.findElement(By.cssSelector("[name=username]")).sendKeys("John");
        driver.findElement(By.cssSelector("[name=password]")).sendKeys("123123!$L");
        driver.findElement(By.cssSelector(".login .button")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[href$='logout.htm']")).isDisplayed());
    }

}
