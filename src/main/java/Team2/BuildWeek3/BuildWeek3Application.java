package Team2.BuildWeek3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class BuildWeek3Application {

    public static void main(String[] args) {
//        SpringApplication.run(BuildWeek3Application.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(BuildWeek3Application.class, args);
        FillDatabaseProvince fillDatabaseProvince = context.getBean(FillDatabaseProvince.class);
        fillDatabaseProvince.fillDatabase();
        fillDatabaseProvince.fillDatabaseComuni();
    }

}
