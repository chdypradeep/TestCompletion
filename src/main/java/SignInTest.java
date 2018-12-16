import com.sun.javafx.PlatformUtil;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SignInTest {

    WebDriver driver;

    @Test
    public void shouldThrowAnErrorIfSignInDetailsAreMissing() {
    	
        try{
    	setDriverPath();
        driver = new ChromeDriver();
        driver.get("https://www.cleartrip.com/");
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);

        clickWebElement(findElementBy(By.linkText("Your trips")));
        clickWebElement(findElementBy(By.id("SignIn")));
        driver.switchTo().frame("modal_window");

        clickWebElement(findElementBy(By.xpath("//button[@id='signInButton']")));
        
        String errors1 = findElementBy(By.id("errors1")).getText();
        Assert.assertTrue(errors1.contains("There were errors in your submission"));
        driver.quit();
        }
        catch(Exception e){
        	System.out.println("there is a exception ");
        	e.printStackTrace();
        }
    }

    private Boolean waitForElementVisible(WebElement element,int waitInSeconds) throws Exception {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            throw e;
        }
        return true;
    }
    
    private void clickWebElement(WebElement element) throws Exception{
    	if(waitForElementVisible(element, 10)){
    		element.click();
    	}else{
    		throw new Exception("Element is not visible so cannot be clicked");
    	}
    }
    
    private WebElement findElementBy(By by){
    	return  driver.findElement(by);
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
