package wb;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductPage {

    private final WebDriver chromeDriver;
    private final WebDriverWait wait;

    private static final String baseURL = "https://www.wildberries.by/catalog/%s/detail.aspx";

    private static final String productPageId = "//*[contains(@class, 'product-page productPage')]";

    private static final String addToBasketButton = "//button[@aria-label='Добавить в корзину']";

    private static final String inBasketButton = "//*[contains(@class, 'orderButtonsWrapper')]//button[@aria-label='В корзине']";

    public ProductPage(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(20));
    }

    public void openAndVerify(String productId) {
        chromeDriver.get(String.format(baseURL, productId));
        wait.until(ExpectedConditions.urlMatches(String.format(baseURL, productId)));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(productPageId)));
    }

    public void addToBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(addToBasketButton)))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(inBasketButton)));
    }

    public void goToBasket() {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(inBasketButton)))
                .click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(BasketPage.basketPageId)));
    }
}
