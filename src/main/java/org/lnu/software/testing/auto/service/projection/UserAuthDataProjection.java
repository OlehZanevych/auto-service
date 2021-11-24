package org.lnu.software.testing.auto.service.projection;

public interface UserAuthDataProjection {
    String getUsername();
    boolean getIsAdmin();
    String getPasswordHash();
}
