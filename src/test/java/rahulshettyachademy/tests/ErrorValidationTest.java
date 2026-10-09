package rahulshettyachademy.tests;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import rahulshettyachademy.TestComponents.BaseTest;
import rahulshettyachademy.pageobjects.CartPage;
import rahulshettyachademy.pageobjects.ProductCatalogue;
import rahulshettyachademy.TestComponents.Retry;


public class ErrorValidationTest extends BaseTest 
{
	@Test(groups={"ErrorHandling"}, retryAnalyzer=Retry.class)
	public void logInErrorValidation() throws IOException, InterruptedException
	{
		
	    landingPage.loginApplication("Sangamrathor31@gmail.com","Sangamrathore");
	Assert.assertEquals( "Incorrect email or password." , landingPage.getErrorMessage());    ;
      
  }

	
		@Test
		public void productErrorValidation() throws IOException, InterruptedException
		{
			String productName="ADIDAS ORIGINAL";
		    ProductCatalogue productCatalogue=   landingPage.loginApplication("Sangamrathor31@gmail.com","Sangamrathore31#");
	        productCatalogue.getProductList();
	        productCatalogue.addProductToCart(productName);
	        CartPage cartPage=   productCatalogue.goToCartPage();
	        Boolean match= cartPage.VerifyPoductDisplay("ADIDAS");
	        Assert.assertFalse(match);
	  
	  }

	}



