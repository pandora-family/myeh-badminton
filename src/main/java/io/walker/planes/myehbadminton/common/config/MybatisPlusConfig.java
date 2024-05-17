package io.walker.planes.myehbadminton.common.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import io.walker.planes.myehbadminton.repo.QueryInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Planeswalker23
 */
@Configuration
public class MybatisPlusConfig {

    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new QueryInterceptor());
        return interceptor;
    }
}
