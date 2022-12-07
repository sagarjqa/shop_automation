
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

import static io.restassured.RestAssured.given;

public class ShopAutomation {

    @Test
    public void getUser() throws InterruptedException {
        RestAssured.baseURI = "https://randomuser.me";
        Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("/api")
                .then()
                .extract().response();
        String firstname = response.jsonPath().getString("results[0].name.first");
        String lastname = response.jsonPath().getString("results[0].name.last");
        String email = response.jsonPath().getString("results[0].email");
        System.out.println(firstname);

        WebDriver driver ;
        System.setProperty("webdriver.chrome.driver","/Users/sagarjain/Desktop/Sagar/git/test1/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS) ;
        driver.manage().window().maximize() ;
        driver.get("https://magento.softwaretestingboard.com/");

        WebElement acc = driver.findElement(By.xpath("(//a[text()='Create an Account'])[1]"));
        acc.click();
        Thread.sleep(3000);

        WebElement first = driver.findElement(By.name("firstname"));
        first.sendKeys(firstname);
        WebElement last = driver.findElement(By.name("lastname"));
        last.sendKeys(lastname);
        WebElement emailfield = driver.findElement(By.name("email"));
        emailfield.sendKeys(email);
        WebElement password = driver.findElement(By.name("password"));
        password.sendKeys("abc@12345");
        WebElement password_confirmation = driver.findElement(By.name("password_confirmation"));
        password_confirmation.sendKeys("abc@12345");
        WebElement submit = driver.findElement(By.xpath("//button[@class='action submit primary']"));
        submit.click();
        Thread.sleep(3000);
        WebElement logo = driver.findElement(By.xpath("//a[@class='logo']"));
        logo.click();
        WebElement actionmore = driver.findElement(By.xpath("//span[@class='action more button']"));
        actionmore.click();
        WebElement product = driver.findElement(By.xpath("(//img[@class='product-image-photo'])[1]"));
        product.click();
        WebElement size = driver.findElement(By.xpath("//div[@id='option-label-size-143-item-171']"));
        size.click();
        WebElement color = driver.findElement(By.xpath("//div[@id='option-label-color-93-item-49']"));
        color.click();
        WebElement sub = driver.findElement(By.xpath("//button[@class='action primary tocart']"));
        sub.click();
        Thread.sleep(3000);
        WebElement cart = driver.findElement(By.xpath("//a[text()='shopping cart']"));
        cart.click();
        Thread.sleep(4000);
        WebElement check = driver.findElement(By.xpath("(//button[@class='action primary checkout'])[2]"));
        check.click();
        Thread.sleep(3000);
        WebElement street = driver.findElement(By.name("street[0]"));
        street.sendKeys("a");
        WebElement city = driver.findElement(By.name("city"));
        city.sendKeys("a");
        Select s = new Select(driver.findElement(By.className("select")));
        s.selectByVisibleText("Alabama");
        WebElement postcode = driver.findElement(By.name("postcode"));
        postcode.sendKeys("12345");
        WebElement telephone = driver.findElement(By.name("telephone"));
        telephone.sendKeys("12345");
        WebElement method = driver.findElement(By.name("ko_unique_3"));
        method.click();
        WebElement next = driver.findElement(By.xpath("//button[@class='button action continue primary']"));
        next.click();
        Thread.sleep(4000);
        WebElement order = driver.findElement(By.xpath("//span[text()='Place Order']"));
        order.click();
        Thread.sleep(4000);
        WebElement purchase = driver.findElement(By.xpath("//span[text()='Thank you for your purchase!']"));
        Assert.assertTrue(purchase.isDisplayed());
        Thread.sleep(2000);
        driver.quit();
    }
}
