package rahulshettyachademy.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent
{
	WebDriver driver;

	public ProductCatalogue(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

  //  List<WebElement> products=   driver.findElements(By.cssSelector(".mb-3"));

	@FindBy(css=".mb-3")
	List<WebElement> products;

	//driver.findElement(By.cssSelector(".nanimatingg-animating");
	@FindBy(css=".ng-animating")
	WebElement spinner;

    By ProductsBy= By.cssSelector(".mb-3");
	By addToCart=	By.cssSelector(".card-body button:last-of-type");
	By tosteMessage= By.cssSelector("#toast-container");

	public List<WebElement> getProductList()
	{
		waitForElementToAppear(ProductsBy);
		return products;
	}

	public WebElement getproductByName(String productName)
	{
		 WebElement prod=getProductList().stream().filter(product->
	     product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
		 return prod;
	}

	public void addProductToCart(String productName) throws InterruptedException
	{
	WebElement prod= getproductByName(productName);
	     prod.findElement(addToCart).click();
	     waitForElementToAppear(tosteMessage);
	     waitForElementToDissapear(spinner);

	}



}


