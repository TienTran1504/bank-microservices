package com.tdtien.message.functions;

import com.tdtien.message.dto.AccountsMsgDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class MessageFunctions {
    private static Logger logger = LoggerFactory.getLogger(MessageFunctions.class);

    // Function<T,R>: T - input type, R - return type
    @Bean
    public Function<AccountsMsgDto,AccountsMsgDto> email() {
        return accountsMsgDto -> {
            logger.info("Sending email with the deailts: {}", accountsMsgDto.toString());
            return accountsMsgDto;
        };
    }

    @Bean
    public Function<AccountsMsgDto,Long> sms() {
        return accountsMsgDto -> {
            logger.info("Sending sms with the deailts: {}", accountsMsgDto.toString());
            return accountsMsgDto.accountNumber();
        };
    }
}
