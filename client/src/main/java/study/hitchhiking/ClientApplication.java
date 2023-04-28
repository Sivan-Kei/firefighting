package study.hitchhiking;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import study.hitchhiking.config.StartConfig;

@SpringBootApplication
@MapperScan("study.hitchhiking.mapper")
public class ClientApplication {
    public static void main(String[] args) {
        StartConfig.SERVER = "/CLogin.html";
        SpringApplication.run(ClientApplication.class,args);
    }
}
