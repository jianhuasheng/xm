package com.xm.xmscapi;

import com.xm.xmsccommon.DefaultProfileUtil;
import com.xm.xmsccommon.utils.LocalIpUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;

//@MapperScan(basePackages = "com.xm.mapper")
//@ComponentScan(basePackages = {"com.xm"})
@SpringBootApplication
@Slf4j
public class XmscApiApplication {

    private static ApplicationContext context;

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(XmscApiApplication.class);
        DefaultProfileUtil.addDefaultProfile(app);
        context = app.run(args);
        Environment env = context.getEnvironment();
        log.info("\n----------------------------------------------------------\n\t" +
                        "Application '{}' is running! Access URLs:\n\t" +
                        "Local: \t\thttp://localhost:{}\n\t" +
                        "External: \thttp://{}:{}\n\t" +
                        "Active Profile(s): \t{}\n" +
                        "----------------------------------------------------------",
                env.getProperty("spring.application.name"),
                env.getProperty("server.port"),
                LocalIpUtil.getLocalHost(),
                env.getProperty("server.port"),
                env.getActiveProfiles());
    }

    public static ApplicationContext getContext() {
        return context;
    }
}
