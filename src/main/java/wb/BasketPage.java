package wb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasketPage {

    private final WebDriver chromeDriver;
    private final WebDriverWait wait;

    static final String basketPageId = "//*[@data-testid='basket-page']";

    private static final String itemTitle = "//*[@class='good-info__title']";

    private static final String itemsInBasket = "//*[contains(@class, 'list-item') and contains(@class, 'basket-item')]";

    private static final String productLink = ".//*[contains(@class, 'good-info__title')]";

    private static final String quantity = ".//input[contains(@name, 'quantity')]";

    public BasketPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(15));
    }

    public Boolean isItemInBasket(String productId) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BasketPage.itemTitle)));
        for (WebElement item: chromeDriver.findElements(By.xpath(BasketPage.itemTitle))){
            String href = item.getAttribute("href");
            if (href.contains(productId)) {
                return true;
            }
        }
        return false;
    }

    public int countOfUniqueItemsInBasket() {
        return chromeDriver.findElements(By.xpath(BasketPage.itemsInBasket)).size();
    }

    public int countOfExactItemInBasket(String productId) {
        for (WebElement item : chromeDriver.findElements(By.xpath(itemsInBasket))) {
            String href = item.findElement(By.xpath(productLink)).getAttribute("href");
            if (href.contains(productId)) {
                return Integer.parseInt(item.findElement(By.xpath(quantity)).getAttribute("value"));
            }
        }
        return 0;
    }
}