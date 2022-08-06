package bg.exam.animal4me.config;
import bg.exam.animal4me.service.OfferService;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Animal4meMethodSecurityConfig extends GlobalMethodSecurityConfiguration {

    private OfferService offerService;

    public Animal4meMethodSecurityConfig(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionHandler createExpressionHandler() {
        return new Animal4meSecurityHandler(offerService);
    }
}
