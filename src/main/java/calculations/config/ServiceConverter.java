package calculations.config;

import calculations.model.calculator.AbstractCalculation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding;
import org.springframework.context.ApplicationContext;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
@ConfigurationPropertiesBinding
public class ServiceConverter implements Converter<String, AbstractCalculation> {

    @Autowired
    private ApplicationContext context;

    @Override
    public AbstractCalculation convert(String source) {
        return (AbstractCalculation) context.getBean(source);
    }
}
