package ohtu;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Tester {
    public static void main(String[] args) {
        WebDriver driver = new FirefoxDriver();

        // Correct login
        login(driver, "pekka", "akkep");

        // Login with wrong password
        login(driver, "pekka", "joo");

        // Login with nonexistent username
        login(driver, "juha", "sala");

        // Register new user
        register(driver, "mikko", "salasana1");

        // Logout after creating new user
        logout(driver);

    }

    private static void login(WebDriver driver, String username, String password) {
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("login"));
        element.click();

        System.out.println("==");

        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        element.submit();

        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }

    private static void register(WebDriver driver, String username, String password) {
        driver.get("http://localhost:8090");
        System.out.println( driver.getPageSource() );
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();

        System.out.println("==");

        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("add"));
        element.submit();

        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }

    private static void logout(WebDriver driver) {
        WebElement element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();

        System.out.println("==");

        System.out.println( driver.getPageSource() );
        element = driver.findElement(By.linkText("logout"));
        element.click();

        System.out.println("==");
        System.out.println( driver.getPageSource() );
    }
}
