package org.lnu.software.testing.auto.service.dto.user.credentials;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UserCredentials {

    @ApiModelProperty(example = "tomboy47")
    private String username;

    @ApiModelProperty(example = "pa$$word65!")
    private String password;
}
