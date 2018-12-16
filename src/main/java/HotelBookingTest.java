import com.sun.javafx.PlatformUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class HotelBookingTest {

    WebDriver driver;
    public HotelBookingTest(){}
    public HotelBookingTest(WebDriver driver){
    	AjaxElementLocatorFactory ajaxElemFactory = new AjaxElementLocatorFactory(driver, 10);
        PageFactory.initElements(ajaxElemFactory, this);
    }
    @FindBy(linkText = "Hotels")
    private WebElement hotelLink;

    @FindBy(id = "Tags")
    private WebElement localityTextBox;

    @FindBy(id = "SearchHotelsButton")
    private WebElement searchButton;

    @FindBy(id = "travellersOnhome")
    private WebElement travellerSelection;

    @Test
    public void shouldBeAbleToSearchForHotels() {
        try{
    	setDriverPath();
        driver = new ChromeDriver();
        
        driver.get("https://www.cleartrip.com/");
        HotelBookingTest Obj = new HotelBookingTest(driver);
        
        Obj=clickWebElement(Obj.hotelLink);
        
        sendWebElementKeys(Obj.localityTextBox,"Indiranagar, Bangalore");
        
        new Select(Obj.travellerSelection).selectByVisibleText("1 room, 2 adults");
        Obj=clickWebElement(Obj.searchButton);

        driver.quit();
        }
        catch(Exception e){
        	e.printStackTrace();
        }
    }
    
    private HotelBookingTest clickWebElement(WebElement element) throws Exception{
    		element.click();
    		return new HotelBookingTest(driver);
    }
    
    private HotelBookingTest sendWebElementKeys(WebElement element, String value) throws Exception{
    		element.sendKeys(value);
    		return new HotelBookingTest(driver);
    }
    

    private void setDriverPath() {
        if (PlatformUtil.isMac()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver");
        }
        if (PlatformUtil.isWindows()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        }
        if (PlatformUtil.isLinux()) {
            System.setProperty("webdriver.chrome.driver", "chromedriver_linux");
        }
    }

}
