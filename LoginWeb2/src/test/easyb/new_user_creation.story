import ohtu.*
import ohtu.authentication.*
import org.openqa.selenium.*
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

description """A new user account can be created 
              if a proper unused username 
              and a proper password are given"""


scenario "creation succesfull with correct username and password", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi1");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana1");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then 'new user is registered to system', {
        driver.getPageSource().contains("Welcome to Ohtu App!").shouldBe true
    }
}

scenario "can login with succesfully generated account", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }
 
    when 'a valid username and password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi2");
        element = driver.findElement(By.name("password"));
        element.sendKeys("salasana2");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("salasana2");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then  'new credentials allow logging in to system', {
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        element = driver.findElement(By.linkText("logout"));
        element.click();
        driver.getPageSource().contains("Ohtu App").shouldBe true
    }
}


scenario "can not login with account that is not succesfully created", {
    given 'command new user is selected', {
        driver = new HtmlUnitDriver();
        driver.get("http://localhost:8090");
        WebElement element = driver.findElement(By.linkText("register new user"));
        element.click();
    }

    when 'a invalid username/password are entered', {
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi3");
        element = driver.findElement(By.name("password"));
        element.sendKeys("s");
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys("s");
        element = driver.findElement(By.name("add"));
        element.submit();
    }

    then  'new credentials do not allow logging in to system', {
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        element = driver.findElement(By.linkText("login"));
        element.click();
        element = driver.findElement(By.name("username"));
        element.sendKeys("testi3");
        element = driver.findElement(By.name("password"));
        element.sendKeys("s");
        element = driver.findElement(By.name("login"));
        element.submit();
        driver.getPageSource().contains("wrong username or password").shouldBe true
    }
}