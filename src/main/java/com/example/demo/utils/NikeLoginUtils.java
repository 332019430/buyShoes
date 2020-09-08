package com.example.demo.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static com.example.demo.utils.commonUtils.*;


/**
 * @author zyl
 */
public class NikeLoginUtils {
    List<WebElement> Buys;

    private static String login = "join-log-in";
    private static String mobile = "verifyMobileNumber";
    private static String password = "password";
    private static String submit = "nike-unite-submit-button";
    private static String close = "close";
    private static String errorClose = "nike-unite-error-close";
    private static String productList = "ncss-col-lg-4";
    private  static String mail="mobileNumberToEmailLoginLink";
    private  static String mailAddress="emailAddress";

    //服务器无响应处理
    public static void erroLogin(WebDriver webDriver, List<WebElement> erros, String password) {
        while (erros.size() > 0) {
            erros.get(0).click();
            loginJustPassword(webDriver, erros, password);
            erros = webDriver.findElements(By.className(errorClose));
            erroLogin(webDriver, erros, password);
            break;
        }
    }

    //点击登录并输入账号
    public static void login(WebDriver webDriver, List<WebElement> closes, List<WebElement> errors, String userName, String password) {
        sleep1();
        waitAndClose(webDriver, closes);
        sleep3();
        webDriver.findElement(By.className(login)).click();
        /*wait(webDriver);*/
        List<WebElement> elements = webDriver.findElements(By.className("action-link"));
        elements.get(2).click();
        webDriver.findElement(By.name(mailAddress)).sendKeys(userName);
        loginJustPassword(webDriver, errors, password);
    }

    //输入登入密码并点击登录
    public static void loginJustPassword(WebDriver webDriver, List<WebElement> errors, String passwords) {
        webDriver.findElement(By.name(password)).sendKeys(passwords);
        webDriver.findElement(By.className(submit)).click();
        //sleep3();
        //服务器无响应处理
        errors = webDriver.findElements(By.className(errorClose));
        erroLogin(webDriver, errors, passwords);
        /* sleep();*/
        //登录完成后可能会出现空白窗口，点击X关闭
        /*closeX(webDriver);*/
    }

    //轮流点击抢购项
    public static void eachBuy(WebDriver webDriver, List<WebElement> closes, List<WebElement> buys) {
        try {
            sleep7();
            List<WebElement> sizes = webDriver.findElements(By.className("size-grid-dropdown"));
            for (int i = 0; i < sizes.size(); i++) {
                try {
                    sizes.get(i).click();
                    System.out.println(i+"======可以点击了=======");
                    break;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            sleep3();
            webDriver.findElement(By.className("cta-btn")).click();
            sleep3();
            buys = webDriver.findElements(By.className("payment-provider-btn"));
            buys.get(buys.size() - 1).click();
            buys = webDriver.findElements(By.className("save-button"));
            buys.get(1).click();
            sleep1();
            buys.get(2).click();
        } catch (Exception e) {
            e.printStackTrace();
        }
        sleep10();
        waitAndClose(webDriver, closes);
        webDriver.close();
    }

    public static void closeX(WebDriver webDriver, List<WebElement> closes) {
        closes = webDriver.findElements(By.className(close));
        while (closes.size() > 0) {
            closes.get(0).click();
        }
    }

    //递归点击X
    public static void waitAndClose(WebDriver webDriver, List<WebElement> closes) {
        closes = webDriver.findElements(By.className(close));
        while (closes.size() > 0) {
            try {
                closes.get(0).click();
            } catch (Exception e) {
                e.printStackTrace();
            }
            waitAndClose(webDriver, closes);
            break;
        }
    }
}
