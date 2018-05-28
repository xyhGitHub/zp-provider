
package com.four.util;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

/*    @Configuration可理解为用spring的时候xml里面的<beans>标签

    @Bean可理解为用spring的时候xml里面的<bean>标签*/


    @Bean
    public Queue Queue() {
        return new Queue("regMessage");
    }


}

