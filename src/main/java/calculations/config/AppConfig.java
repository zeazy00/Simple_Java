package calculations.config;

import calculations.model.calculator.AbstractCalculation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.annotation.Validated;

//@Configuration
//@EnableCaching
//@PropertySource("classpath:application.properties")
//@ConfigurationProperties(prefix = "app")
public class AppConfig {

    @Value("service")
    private AbstractCalculation service;

    public AbstractCalculation getService() {
        return service;
    }

    public void setService(AbstractCalculation service) {
        this.service = service;
    }
}
