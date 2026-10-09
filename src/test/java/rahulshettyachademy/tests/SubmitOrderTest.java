package rahulshettyachademy.tests;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyachademy.TestComponents.BaseTest;
import rahulshettyachademy.pageobjects.CartPage;
import rahulshettyachademy.pageobjects.CheckOutPage;
import rahulshettyachademy.pageobjects.ConfirmationPage;
import rahulshettyachademy.pageobjects.OrderPage;
import rahulshettyachademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest 
{
	String productName="ADIDAS ORIGINAL";
	@Test (dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
	{
		
	    ProductCatalogue productCatalogue=   landingPage.loginApplication(input.get("email"),input.get("password"));
       // ProductCatalogue productCatalogue= new ProductCatalogue(driver);
   List<WebElement> products= productCatalogue.getProductList();
        productCatalogue.addProductToCart(input.get("productName"));
        CartPage cartPage=   productCatalogue.goToCartPage();
       //CartPage cartPage =new CartPage(driver);
   Boolean match= cartPage.VerifyPoductDisplay(input.get("productName"));
   Assert.assertTrue(match);
   CheckOutPage checkOutPage=   cartPage.goToCheckOut();
   checkOutPage.SelectCountry("india");
   ConfirmationPage confermationPage = checkOutPage.SubmitOrder();
    String cnfMsg=  confermationPage.getConfirmationMessage();
   // Assert.assertTrue(cnfMsg.equalsIgnoreCase("Thankyou for the order."));
   Assert.assertEquals(cnfMsg, "THANKYOU FOR THE ORDER.");
   System.out.println(cnfMsg);
  }
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		 ProductCatalogue productCatalogue=  landingPage.loginApplication("Sangamrathor31@gmail.com","Sangamrathore31#");
		OrderPage orderPage= productCatalogue.goToOrderPage();
	    Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	
	}
	
    
	@DataProvider
	public Object getData() throws IOException
	{
		List<HashMap<String,String>> data=getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\rahulshettyachademy\\data\\PurchaseOrder.json");

		return new Object[] []{{data.get(0)},{data.get(1)}};
	}
	
	
	
	
	
	
	/*@DataProvider
	public Object getData()
	{
    
		return new Object[] []{{"Sangamrathor31@gmail.com","Sangamrathore31#","ADIDAS ORIGINAL"},{"Singha@gmail.com","Realmenarzo#70","ADIDAS ORIGINAL"}};
	}*/
	
/*	HashMap<String,String> map= new HashMap<String,String>();
	map.put("email", "Sangamrathor31@gmail.com");
	map.put("password", "Sangamrathore31#");
	map.put("productName", "ADIDAS ORIGINAL");
	
	HashMap<String,String> map1= new HashMap<String,String>();
	map1.put("email", "Singha@gmail.com");
	map1.put("password", "Realmenarzo#70");
	map1.put("productName", "ADIDAS ORIGINAL");
	
	
	}*/
	
	
	


}
