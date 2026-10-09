package rahulshettyachademy.stepDefinationImpl;

import java.io.IOException;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyachademy.TestComponents.BaseTest;
import rahulshettyachademy.pageobjects.CartPage;
import rahulshettyachademy.pageobjects.CheckOutPage;
import rahulshettyachademy.pageobjects.ConfirmationPage;
import rahulshettyachademy.pageobjects.LandingPage;
import rahulshettyachademy.pageobjects.ProductCatalogue;

public class StepDefinationImpl extends BaseTest
{
	  public LandingPage landingPage;
	  public ProductCatalogue productCatalogue;
	  public ConfirmationPage confermationPage;
	  @Given("I Landed On Ecommerce Page")
	  public void I_Landed_On_Ecommerce_Page() throws IOException
	  {
		  System.out.println("Login done");
	      landingPage = launchApplication();
	  }

	  @Given("^Loggin with username (.+) and password (.+)$")
	  public void Loggin_with_username_and_password(String userName, String password)
	  {
	      productCatalogue = landingPage.loginApplication(userName, password);
	  }

	  @When("^I add product (.+) to Cart$")
	  public void I_add_product_to_Cart(String productName) throws InterruptedException
	  {
	      productCatalogue.getProductList();
	      productCatalogue.addProductToCart(productName);
	  }

	  @When("^CheckOut (.+) and submit the order$")
	  public void CheckOut_and_submit_the_order(String productName)
	  {
	      CartPage cartPage = productCatalogue.goToCartPage();
	      Boolean match = cartPage.VerifyPoductDisplay(productName);
	      Assert.assertTrue(match);
	      CheckOutPage checkOutPage = cartPage.goToCheckOut();
	      checkOutPage.SelectCountry("india");
	      confermationPage = checkOutPage.SubmitOrder();
	  }

	  @Then("{string} message is displayed on the ConfirmationPage")
	  public void message_is_displayed_on_the_ConfirmationPage(String string)
	  {
	      String cnfMsg = confermationPage.getConfirmationMessage();
	      Assert.assertEquals(cnfMsg, string);
	      driver.close();
	  }
	  @Then("{string} message is diaplayed")
	  public void message_is_diaplayed(String string)
	  {
		 Assert.assertEquals( string , landingPage.getErrorMessage());    
			driver.close();
	  }
	  
	  
	  
}