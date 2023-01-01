package com.codehh.board.api.dto.user.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LoginReq {
    String id;
    String password;
    @JsonProperty("auto_login")
    boolean autoLogin;
}
