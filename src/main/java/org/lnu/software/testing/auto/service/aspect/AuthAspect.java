package org.lnu.software.testing.auto.service.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.lnu.software.testing.auto.service.annotation.Auth;
import org.springframework.context.annotation.Configuration;

@Aspect
@Configuration
public class AuthAspect {
    @Before("@annotation(auth) && within(@org.springframework.web.bind.annotation.RestController *)")
    public void verifyAuthoritySpecifiedForMethod(Auth auth) {
        verifyAuthority(auth);
    }

    @Before("@within(auth) && !@annotation(org.lnu.software.testing.auto.service.annotation.Auth)" +
            " && within(@org.springframework.web.bind.annotation.RestController *)")
    public void verifyAuthoritySpecifiedForControllerClass(Auth auth) {
        verifyAuthority(auth);
    }


    private void verifyAuthority(Auth auth) {
        // TODO Add corresponding logic for processing JWT token
        System.out.println("Work !!!!");
        System.out.println(auth.isAdmin());
    }
}
