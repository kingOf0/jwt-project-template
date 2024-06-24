package com.kingof0.jwtprojecttemplate.config;

import com.iyzipay.Options;
import lombok.Getter;
import org.springframework.context.annotation.Configuration;

@Configuration
@Getter
public class PaymentConfiguration {
    private final Options options = new Options() {{
        setApiKey("sandbox-DUpCya3rL0vrHWsJ36cOV5U388jr5Kmr");
        setSecretKey("sandbox-mLmpo6tpWqOVV1O7TkBuKd52xTopIIiY");
        setBaseUrl("https://sandbox-api.iyzipay.com");
    }};

}
