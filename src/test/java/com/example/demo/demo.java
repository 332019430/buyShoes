package com.example.demo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class demo {

    public static void main(String[] args) {
        WebDriver webDriver;
        /*System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\WebDriver\\geckodriver.exe");
        webDriver = new FirefoxDriver();*/
        System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Google\\Google\\Chrome\\Application\\chromedriver.exe");
        webDriver = new ChromeDriver();
        webDriver.get("https://www.nike.com/cn/launch?s=upcoming");
        /*sleep5();*/
        wait(webDriver);

        login(webDriver);
        /* sleep();*/
        List<WebElement> elements = webDriver.findElements(By.className("ncss-col-lg-4"));
        for (int i = 0; i < elements.size(); i++) {
            sleep5();
            //因为elements在点击返回按钮后悔果实，所以需要destElements
            List<WebElement> destElements = webDriver.findElements(By.className("ncss-col-lg-4"));
            sleep3();
            wait(webDriver);
            destElements.get(i).click();
            sleep3();
            webDriver.navigate().back();
            /*sleep();*/
        }


    }

    //服务器无响应处理
    public static void erroLogin(WebDriver webDriver, List<WebElement> erros) {
        while (erros.size() > 0) {
            erros.get(0).click();
            loginJustPassword(webDriver);
            erros = webDriver.findElements(By.className("nike-unite-error-close"));
            erroLogin(webDriver, erros);
            break;
        }
    }

    //点击登录并输入账号
    public static void login(WebDriver webDriver) {
        sleep2();
        closeX(webDriver);
        sleep2();
        webDriver.findElement(By.className("join-log-in")).click();
        /*wait(webDriver);*/
        webDriver.findElement(By.name("verifyMobileNumber")).sendKeys("18902570346");
        loginJustPassword(webDriver);
    }

    //输入登入密码并点击登录
    public static void loginJustPassword(WebDriver webDriver) {
        webDriver.findElement(By.name("password")).sendKeys("Longsao007");
        webDriver.findElement(By.className("nike-unite-submit-button")).click();
        sleep3();
        //服务器无响应处理
        List<WebElement> erros = webDriver.findElements(By.className("nike-unite-error-close"));
        erroLogin(webDriver, erros);
        /* sleep();*/
        //登录完成后可能会出现空白窗口，点击X关闭
        /*closeX(webDriver);*/
    }

    public static void sleep2() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep3() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void sleep5() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void closeX(WebDriver webDriver) {
        List<WebElement> close = webDriver.findElements(By.className("close"));
        while (close.size() > 0) {
            close.get(0).click();
        }
    }

    //递归点击X
    public static void wait(WebDriver webDriver) {
        List<WebElement> close = webDriver.findElements(By.className("close"));
        while (close.size() > 0) {
            /*  sleep();*/
            try {
                close.get(0).click();
            } catch (Exception e) {
                e.printStackTrace();
            }
            wait(webDriver);
            break;
        }
    }

}
