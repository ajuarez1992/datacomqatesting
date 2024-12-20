package alvinjuarez;

import java.time.Duration;
import java.util.List;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.github.bonigarcia.wdm.WebDriverManager;

/**
 * automation test for simple App.
 * Alvin Juarez - QA Test Assessment using Selenium and Java
 */
public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
        public static void main(String[] args) {
        // Set up the Chrome WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            // Open the Datacom website
            driver.get("https://datacom.com/nz/en/contact-us");

            //Verify Correct Webpage
            String webPageTitle = driver.getTitle();
            if ("contact-us".equals(webPageTitle)) {
                System.out.println("Verification successful: The location title is correct.");
            } else {
                System.out.println("Verification failed: Expected 'Our locations', but found '" + webPageTitle + "'.");
            }

            // Set up WebDriverWait
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

            // Wait for the location title element to be visible
            WebElement locationCountry = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cmp-title__text")));

            // Get the text of the location title
            String locationText = locationCountry.getText();
            System.out.println("Location Title: " + locationText);

            // Verify Our Location Tab Title
            if ("Our locations".equals(locationText)) {
                System.out.println("Verification successful: The location title is correct.");
            } else {
                System.out.println("Verification failed: Expected 'Our locations', but found '" + locationText + "'.");
            }

             // Display all Locations
            List<WebElement> locationTabs = driver.findElements(By.cssSelector("li.cmp-location__nav_item"));
            for (WebElement tab1 : locationTabs) {
                // Click the tab
                tab1.click();

                // Wait for the action to complete (if necessary)
                WebElement locationAddress = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("cmp-location__location__address")));

                String locationTextAddress = locationAddress.getText();

                if(null == locationTextAddress){
                    System.out.println("Verification failed");
                }

                else switch (locationTextAddress) {
                    case "58 Gaunt Street, Auckland CBD, Auckland 1010":System.out.println("Verification successful: The location address for New Zealand is correct.");
                        break;
                    case "118 Franklin Street, Adelaide, South Australia 5000":System.out.println("Verification successful: The location address for Australia is correct.");
                        break;
                    case "Level 3A, 1 Sentral, Jalan Rakyat, Kuala Lumpur Sentral, Kuala Lumpur 50470":System.out.println("Verification successful: The location address for Australia is correct.");
                        break;
                    default:
                        System.out.println("Verification failed");
                        break;
                }

                // Print the name of the clicked tab
                System.out.println("Clicked tab: " + tab1.getText());
            }

            //Verify Main Contact Us Tabs are working properly
            List<WebElement> contactUsTabs = driver.findElements(By.cssSelector("li.cmp-tabs__tab--anchors"));
            for (WebElement tab2 : contactUsTabs) {
                // Click the tab
                tab2.click();

                // Wait for the action to complete (if necessary)
                WebElement tabh2Titles = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h2.cmp-title__text")));

                String visibleTabTitle = tabh2Titles.getText();

                if(null == visibleTabTitle){
                    System.out.println("Verification failed");
                }

                else switch (visibleTabTitle) {
                    case "Our locations":System.out.println("Verification successful: Our locations Title is visible.");
                        break;
                    case "Get in touch":System.out.println("Verification successful: Get in touch Title is visible.");
                        break;
                    case "Our media pack":System.out.println("Verification successful: Media Pack Title is visible.");
                        break;
                    default:
                        System.out.println("Verification failed");
                        break;
                }

                // Print the name of the clicked tab
                System.out.println("Clicked tab: " + tab2.getText());
            }



        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            // Close the browser
            driver.quit();
        }
    }
}

