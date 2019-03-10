import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class Test_Tinkoff {
      private static WebDriver driver;
      private static WebElement menu_two; //контейнер второго типа.
      private static List<WebElement> menuArray;

    @Before
    public void setUp() throws Exception {

        System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        System.out.println("Start");
    }

    @Test
    public void testTinkoff() throws InterruptedException {
        driver.get("https://www.tinkoff.ru/");
        toUpdateLink();
        //1. На странице https://www.tinkoff.ru/ выбрать массив всех элементов второго меню (Кред карты, ***, платежи)
        //Вынес в отдельный метод данные функции
//        menu_two = driver.findElement(By.xpath("//div[@class='header__9V1so header__3rtwn']"));
//        menuArray = menu_two.findElements(By.xpath("//div[@class = \"header__1AlOP\"]"));
        //2. Обратиться к последнему из них
        menuArray.get(menuArray.size() - 1).click();
        //Thread.sleep(1000);
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.findElement(By.xpath("//*[@xmlns='http://www.w3.org/2000/svg']")).click();
        driver.findElement(By.className("Icon__icon_1kNRh")).click(); // возврат на главную страницу, т.к. на данной страницы нет второго меню.
        System.out.println("Возврат в гнлавное меню");

        //Thread.sleep(5000);
        waitPageToLoad();
        toUpdateLink();
        //3. Обратиться ко второму
        menuArray.get(1).click();
        waitPageToLoad();
        toUpdateLink();
        //4. Найти отцовский элемент второго
        WebElement fatherElementOfTheSecond = menuArray.get(1).findElement(By.xpath(".."));
        System.out.println(fatherElementOfTheSecond);
        //5. Найти все ссылки в родителях второго элемента
        List<WebElement> links = fatherElementOfTheSecond.findElements(By.xpath(".//a"));
        System.out.println(links.size());

    }

    public void toUpdateLink() {
        menu_two = driver.findElement(By.xpath("//div[@class='header__9V1so header__3rtwn']"));
        menuArray = menu_two.findElements(By.xpath("//div[@class = \"header__1AlOP\"]"));
    }

    // убирает ошибку устаревшей ссылки, решение из интернета
    private static void waitPageToLoad() {
        ExpectedCondition<Boolean> expectation = driver -> ((JavascriptExecutor) driver).executeScript("return document.readyState").toString().equals("complete");
        try {
            Thread.sleep(1000);
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(expectation);
        } catch (Throwable e) {
            e.printStackTrace();
            System.out.println("Timeout waiting for Page Load Request to complete.");
        }
    }




//    public static void main(String[] args) throws InterruptedException {
//        System.setProperty("webdriver.chrome.driver", "C:\\Work\\chromedriver.exe"); //исправить
//        driver = new ChromeDriver();
//        driver.manage().window().maximize();
//        System.out.println("Start");
//        driver.get("https://www.tinkoff.ru/");
//        //1. На странице https://www.tinkoff.ru/ выбрать массив всех элементов второго меню (Кред карты, ***, платежи)
//        menu_two = driver.findElement(By.xpath("//div[@class='header__9V1so header__3rtwn']"));
//        menuArray = menu_two.findElements(By.xpath("//div[@class = \"header__1AlOP\"]"));
//        //2. Обратиться к последнему из них
//        menuArray.get(menuArray.size() - 1).click();
//        //Thread.sleep(1000);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        //driver.findElement(By.xpath("//*[@xmlns='http://www.w3.org/2000/svg']")).click();
//        driver.findElement(By.className("Icon__icon_1kNRh")).click(); // возврат на главную страницу, т.к. на данной страницы нет второго меню.
//        System.out.println("Возврат в гнлавное меню");
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        menuArray.get(menuArray.size() - 1).click();
//
//
//
//
//    }

//    private static WebElement menu_two; .
//    private static List<WebElement> menuArray;





}
