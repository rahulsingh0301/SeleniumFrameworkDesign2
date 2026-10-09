package rahulshettyachademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent
{
	WebDriver driver;

	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

    //WebElement userName= driver.findElement(By.id("userEmail"));
	    @FindBy(id="userEmail")
	    WebElement userName;

	//WebElement Password=  driver.findElement(By.id("userPassword"));
		@FindBy(id="userPassword")
		WebElement passwordElement;

   //  WebElement submit=  driver.findElement(By.id("login"));
		@FindBy(id="login")
		WebElement submit;
		
		//For error validation
		@FindBy(css="[class*='flyInOut']")
		WebElement errorMessage;

		public ProductCatalogue loginApplication(String email, String password)
		{
			userName.sendKeys(email);
			passwordElement.sendKeys(password);
			submit.click();
			  ProductCatalogue productCatalogue= new ProductCatalogue(driver);
			  return productCatalogue;
		}
		
		public String getErrorMessage()
		{
			waitForWebElementToAppear(errorMessage);
			return errorMessage.getText();
			
		}

	

		public void goTo()
		{
	        driver.get("https://rahulshettyacademy.com/client");

		}

}


