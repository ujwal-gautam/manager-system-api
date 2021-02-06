package in.manager.system.config;


import in.manager.system.util.ResponseMessageUtil;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author ujwal-gautam
 * @date 06/02/21
 * @time 11:31 AM
 */

@Component
@Configuration
public class BeanConfig {

    @Bean
    public ResponseMessageUtil getMessageUtil() {
        return new ResponseMessageUtil();
    }

}
