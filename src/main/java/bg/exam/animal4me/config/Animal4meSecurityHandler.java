package bg.exam.animal4me.config;
import bg.exam.animal4me.service.OfferService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.security.access.expression.method.DefaultMethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.core.Authentication;

public class Animal4meSecurityHandler extends DefaultMethodSecurityExpressionHandler {

    private final OfferService offerService;

    public Animal4meSecurityHandler(OfferService offerService) {
        this.offerService = offerService;
    }

    @Override
    protected MethodSecurityExpressionOperations createSecurityExpressionRoot(Authentication authentication, MethodInvocation invocation) {

        OwnerSecurityRoot securityRoot = new OwnerSecurityRoot(authentication,offerService);

        securityRoot.setPermissionEvaluator(getPermissionEvaluator());
        securityRoot.setTrustResolver(new AuthenticationTrustResolverImpl());
        securityRoot.setRoleHierarchy(getRoleHierarchy());
        return securityRoot;
    }
}