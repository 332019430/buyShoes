package com.example.demo.quartz;


import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zyl
 */
@Configuration
public class QuartzConfig {
    @Bean
    public JobDetail printTimeJobDetail(){
                //BuyShoesJob我们的业务类
        return JobBuilder.newJob(QuartzBuyShoes.class)
                //可以给该JobDetail起一个id
                .withIdentity("BuyShoesJob")
                /*//每个JobDetail内都有一个Map，包含了关联到这个Job的数据，在Job类中可以通过context获取
                //关联键值对
                .usingJobData("msg", "Hello Quartz")*/
                .storeDurably()//即使没有Trigger关联时，也不需要删除该JobDetail
                .build();
    }
    @Bean
    public Trigger printTimeJobTrigger() {
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("0 20 12 * * ?");
        return TriggerBuilder.newTrigger()
                //关联上述的JobDetail
                .forJob(printTimeJobDetail())
                //给Trigger起个名字
                .withIdentity("BuyShoesService")
                .withSchedule(cronScheduleBuilder)
                .build();
    }
}
