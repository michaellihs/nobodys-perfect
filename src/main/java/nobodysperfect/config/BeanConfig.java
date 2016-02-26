package nobodysperfect.config;

import nobodysperfect.beans.FirstBean;
import nobodysperfect.beans.SecondBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public FirstBean firstBean(SecondBean secondBean) {
        return new FirstBean(secondBean);
    }

}
