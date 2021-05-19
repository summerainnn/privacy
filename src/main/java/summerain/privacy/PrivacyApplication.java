package summerain.privacy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("summerain.privacy.mapper")

@SpringBootApplication
public class PrivacyApplication {

    public static void main(String[] args) {
        SpringApplication.run(PrivacyApplication.class, args);
    }

}
