package io.walker.planes.myehbadminton;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackageClasses = {MeyhBadmintonApplication.class})
public class MeyhBadmintonApplication {

    public static void main(String[] args) {
        SpringApplication.run(MeyhBadmintonApplication.class, args);
    }

}
