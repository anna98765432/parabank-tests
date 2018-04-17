import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.UUID;

public class RegistrationTest {
    WebDriver driver = new ChromeDriver();
    String urlRegistration = "http://parabank.parasoft.com/parabank/register.htm";
    String username = UUID.randomUUID().toString();
    String username2 = username.substring(0, 10);

    @Test
    public void shouldRegister() {
        driver.get(urlRegistration);
        driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Barbara");
        driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys("Rabarbar");
        driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("Mikolajska");
        driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Warszawa");
        driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Mazowieckie");
        driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("30-121");
        driver.findElement(By.cssSelector("input[id='customer.ssn']")).sendKeys("12345678");
//        id$='customer.ssn'
        driver.findElement(By.cssSelector("input[id='customer.username']")).sendKeys(username2 + "ania");
        driver.findElement(By.cssSelector("input[id='customer.password']")).sendKeys("barbara123");
        driver.findElement(By.cssSelector("#repeatedPassword")).sendKeys("barbara123");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        Assert.assertTrue(driver.findElement(By.cssSelector("[href$='logout.htm']")).isDisplayed());
    }

    @Test
    public void shouldNotRegisterBecauseOfNotRepatingPassword() {
        driver.get(urlRegistration);
        driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Jan");
        driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys("Banan");
        driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("Aleje");
        driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Kraków");
        driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Małopolskie");
        driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("31-789");
        driver.findElement(By.cssSelector("input[id='customer.ssn']")).sendKeys("98765432");
        driver.findElement(By.cssSelector("input[id='customer.username']")).sendKeys(username2 + "beata");
        driver.findElement(By.cssSelector("input[id='customer.password']")).sendKeys("barbara123");
        driver.findElement(By.cssSelector("input[id='repeatedPassword']")).sendKeys("barbara12");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Passwords did not match."));

    }

    @Test
    public void shouldNotRegisterBecauseOfNotGivingLastName() {
        driver.get(urlRegistration);
        driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Alicja");
        driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("Nadwiślańska");
        driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Wrocław");
        driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Dolnośląskie");
        driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("44-789");
        driver.findElement(By.cssSelector("input[id='customer.ssn']")).sendKeys("34567890");
        driver.findElement(By.cssSelector("input[id='customer.username']")).sendKeys(username2 + "alicja");
        driver.findElement(By.cssSelector("input[id='customer.password']")).sendKeys("alicja123");
        driver.findElement(By.cssSelector("input[id='repeatedPassword']")).sendKeys("alicja123");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String pageSource = driver.getPageSource();
        Assert.assertTrue(pageSource.contains("Last name is required."));

    }

    @Test
    public void shouldNotRegisterBecauseOfRepeatingUser() {
        driver.get(urlRegistration);
        driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Anna");
        driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys("Michalska");
        driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("Piłsudskiego");
        driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Gdańsk");
        driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Pomorskie");
        driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("34-123");
        driver.findElement(By.cssSelector("input[id='customer.ssn']")).sendKeys("34567890");
        driver.findElement(By.cssSelector("input[id='customer.username']")).sendKeys("AnnaEMIN");
        driver.findElement(By.cssSelector("input[id='customer.password']")).sendKeys("anna1234");
        driver.findElement(By.cssSelector("input[id='repeatedPassword']")).sendKeys("anna1234");
        driver.findElement(By.cssSelector("input[value='Register']")).click();
        String pageSource = driver.getPageSource();
        if (pageSource.contains("Your account was created successfully."))
        {
            driver.get(urlRegistration);
            driver.findElement(By.xpath("//input[@id='customer.firstName']")).sendKeys("Anna");
            driver.findElement(By.xpath("//input[@id='customer.lastName']")).sendKeys("Michalska");
            driver.findElement(By.xpath("//input[@id='customer.address.street']")).sendKeys("Piłsudskiego");
            driver.findElement(By.xpath("//input[@id='customer.address.city']")).sendKeys("Gdańsk");
            driver.findElement(By.xpath("//input[@id='customer.address.state']")).sendKeys("Pomorskie");
            driver.findElement(By.xpath("//input[@id='customer.address.zipCode']")).sendKeys("34-123");
            driver.findElement(By.cssSelector("input[id='customer.ssn']")).sendKeys("34567890");
            driver.findElement(By.cssSelector("input[id='customer.username']")).sendKeys("AnnaEMIN");
            driver.findElement(By.cssSelector("input[id='customer.password']")).sendKeys("anna1234");
            driver.findElement(By.cssSelector("input[id='repeatedPassword']")).sendKeys("anna1234");
            driver.findElement(By.cssSelector("input[value='Register']")).click();

        }else {
            String pageSource1 = driver.getPageSource();
            Assert.assertTrue(pageSource1.contains("This username already exists."));
        }


    }

}


