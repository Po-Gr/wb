package wb;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.time.Duration;

public class OnlineShoppingTest {

    protected WebDriver chromeDriver;

    @BeforeMethod
    public void before(){
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--start-maximized");
        chromeOptions.addArguments("--disable-notifications");
        chromeDriver = new ChromeDriver(chromeOptions);
    }

    @AfterMethod
    public void after(){
        chromeDriver.quit();
    }

    @Test
    public void addingItemInBasketTest() {

        String productId = "264220770";
        ProductPage productPage = new ProductPage(chromeDriver);
        BasketPage basketPage = new BasketPage(chromeDriver);

        productPage.openAndVerify(productId);
        productPage.addToBasket();
        productPage.goToBasket();

        Assert.assertTrue(basketPage.isItemInBasket(productId), "Товар не добавлен в корзину");
        Assert.assertEquals(basketPage.countOfUniqueItemsInBasket(), 1, "Количество различных товаров добавленных в корзину не совпало");
        Assert.assertEquals(basketPage.countOfExactItemInBasket(productId), 1, "Количество единиц товара добавленного в корзину не совпало");
    }
}
