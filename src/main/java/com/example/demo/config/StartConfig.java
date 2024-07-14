package com.example.demo.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class StartConfig implements ApplicationRunner {
    public static final Logger Log = LoggerFactory.getLogger(StartConfig.class);

    @Value("${spring.web.loginurl}")
    private String loginUrl;

    @Value("${spring.web.googleexcute}")
    private String googleexcute;

    @Value("${spring.web.isopenurl}")
    private boolean isOpen;

    @Override
    public void run(ApplicationArguments args) {
        Log.info("loginUrl:{}", loginUrl);
        Log.info("googleexcute:{}", googleexcute);
        Log.info("isOpen:{}", isOpen);

        //先判断当前环境
        if (!IsLinuxOs()) {
            //启动swagger-ui
            application();
        }
    }

    private boolean IsLinuxOs() {
        String osName = System.getProperty("os.name");
        if (osName.toLowerCase().contains("linux")) {
            Log.info("runing in linux");
            return true;
        } else {
            Log.info("dot't running in linux");
            return false;
        }
    }

    private void application() {
        if (isOpen) {
            String cmd = googleexcute + " " + loginUrl;
            Log.info("浏览地址：" + cmd);
            Runtime run = Runtime.getRuntime();
            try {
                run.exec(cmd);
                Log.info("启动浏览器打开项目成功");
            } catch (Exception e) {
                Log.error(e.getMessage());
            }
        }
    }
}
