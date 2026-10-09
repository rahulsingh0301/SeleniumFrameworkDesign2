package rahulshettyachademy.tests;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args)
	{
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		 JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/client");
        driver.findElement(By.id("userEmail")).sendKeys("Sangamrathor31@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Sangamrathore31#");
        driver.findElement(By.id("login")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
     List<WebElement> products=   driver.findElements(By.cssSelector(".mb-3"));
     WebElement prod=products.stream().filter(product->
     product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
     js.executeScript("arguments[0].scrollIntoView(true);", prod);
     prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();

     wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
     wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
     driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
   List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection  h3"));
   Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
   Assert.assertTrue(match);
   driver.findElement(By.cssSelector(".totalRow button")).click();
   Actions a= new Actions(driver);
   a.sendKeys(driver.findElement(By.cssSelector("input[placeholder='Select Country']")),"india").build().perform();
   wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
   driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
  
   WebElement submitBtn= driver.findElement(By.cssSelector(".action__submit"));
   js.executeScript("arguments[0].scrollIntoView(true);", submitBtn);
   js.executeScript("arguments[0].click();", submitBtn);

 //  wait.until(ExpectedConditions.visibilityOf( driver.findElement(By.cssSelector(".action__submit"))));
   String cnfMsg=  driver.findElement(By.cssSelector(".hero-primary")).getText();
   // Assert.assertTrue(cnfMsg.equalsIgnoreCase("Thankyou for the order."));
   Assert.assertEquals(cnfMsg, "THANKYOU FOR THE ORDER.");
   System.out.println(cnfMsg);
   driver.quit();





	}

}
