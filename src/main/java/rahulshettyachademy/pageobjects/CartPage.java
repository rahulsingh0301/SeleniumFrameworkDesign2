package rahulshettyachademy.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent
{
	WebDriver driver;

	public CartPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	  //List<WebElement> cartProducts=  driver.findElements(By.cssSelector(".cartSection  h3"));
	@FindBy(css=".cartSection  h3")
	List<WebElement> cartProducts;
	// driver.findElement(By.cssSelector(".totalRow button")).click();
	@FindBy(css = ".totalRow button")
	WebElement checkOut;
	public Boolean VerifyPoductDisplay(String productName)
	{
	   Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
	   return match;
	}

	public CheckOutPage goToCheckOut()
	{
		checkOut.click();
	CheckOutPage checkOutPage=	new CheckOutPage(driver);
	return checkOutPage;
	}
}


