package bg.exam.animal4me.config;
import bg.exam.animal4me.service.OfferService;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.security.access.expression.SecurityExpressionRoot;
import org.springframework.security.access.expression.method.MethodSecurityExpressionHandler;
import org.springframework.security.access.expression.method.MethodSecurityExpressionOperations;
import org.springframework.security.core.Authentication;

import java.util.UUID;

public class OwnerSecurityRoot extends SecurityExpressionRoot implements MethodSecurityExpressionOperations  {

    private Authentication authentication;
    private OfferService offerService;
    private Object filterObject;
    private Object returnObject;
    public OwnerSecurityRoot(Authentication authentication, OfferService offerService) {
        super(authentication);
        this.authentication = authentication;
        this.offerService = offerService;
    }

    public boolean isOwner(UUID id){
        if(authentication.getPrincipal()==null){
            return false;
        }
        var userName = authentication.getName();

        return offerService.isOwner(userName,id);
    }

    @Override
    public void setFilterObject(Object filterObject) {
        this.filterObject=filterObject;
    }

    @Override
    public Object getFilterObject() {
        return filterObject;
    }

    @Override
    public void setReturnObject(Object returnObject) {
        this.returnObject=returnObject;
    }

    @Override
    public Object getReturnObject() {
        return this.returnObject;
    }

    @Override
    public Object getThis() {
        return this;
    }
}
