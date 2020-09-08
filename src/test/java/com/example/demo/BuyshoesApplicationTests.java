package com.example.demo;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.utils.NikeLoginUtils.eachBuy;
import static com.example.demo.utils.NikeLoginUtils.login;
import static com.example.demo.utils.commonUtils.sleep1;

@SpringBootTest
class BuyshoesApplicationTests {



    public static void main(String[] args) {
        /**
         * 屏蔽窗口
         */
        List<WebElement> closes = null;
        /**
         * 服务器错误节点
         */
        List<WebElement> errors=null;
        /**
         * 抢购按钮
         */
        List<WebElement> buys=null;
        /**
         * 浏览器驱动
         */
        WebDriver webDriver;

        //登录账号
        HashMap<String, String> count = new HashMap<>();
        count.put("账号", "密码");

        for (String s : count.keySet()) {
            //抢鞋流程开始
            System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\WebDriver\\geckodriver.exe");
            webDriver = new FirefoxDriver();
        /*System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Google\\Google\\Chrome\\Application\\chromedriver.exe");
        webDriver = new ChromeDriver();*/
            webDriver.get("https://www.nike.com/cn/launch/t/dunk-low-champ-colors");
            String userName = s;
            String password = count.get(s);
            login(webDriver, closes, errors, userName, password);
            sleep1();
            eachBuy(webDriver, closes, buys);
        }
    }

}
