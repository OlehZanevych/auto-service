package org.lnu.software.testing.auto.service.service.auth;

import org.lnu.software.testing.auto.service.annotation.Auth;
import org.lnu.software.testing.auto.service.dto.user.credentials.UserCredentials;

public interface AuthService {
    void login(UserCredentials userCredentials);
    void verifyAuthority(Auth auth);
}
