package com.codehh.board.api.dto.user.request;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinReq {
    String id;
    String nickname;
    String password;
    String email;
}
