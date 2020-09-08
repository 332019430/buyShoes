package com.example.demo.quartz;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.HashMap;
import java.util.List;

import static com.example.demo.utils.NikeLoginUtils.*;

/**
 * @author zyl
 */
public class QuartzBuyShoes extends QuartzJobBean {
    /**
     * 屏蔽窗口
     */
    List<WebElement> closes;
    /**
     * 服务器错误节点
     */
    List<WebElement> errors;
    /**
     * 抢购按钮
     */
    List<WebElement> buys;
    /**
     * 浏览器驱动
     */
    WebDriver webDriver;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {


        //登录账号
        HashMap<String, String> count = new HashMap<>();
        count.put("账号", "密码");

        for (String s : count.keySet()) {
            //抢鞋流程开始
           System.setProperty("webdriver.gecko.driver", "D:\\Program Files\\WebDriver\\geckodriver.exe");
            webDriver = new FirefoxDriver();
            /*System.setProperty("webdriver.chrome.driver", "D:\\Program Files\\Google\\Google\\Chrome\\Application\\chromedriver.exe");
            webDriver = new ChromeDriver();*/
            webDriver.get("https://www.nike.com/cn/launch/t/womens-air-max-270-xx-bright-crimson");
            String userName = s;
            String password = count.get(s);
            try {
                login(webDriver, closes, errors, userName, password);
                /* sleep();*/
                eachBuy(webDriver, closes, buys);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }


}
