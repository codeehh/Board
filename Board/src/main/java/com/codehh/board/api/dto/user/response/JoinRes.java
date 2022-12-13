package com.codehh.board.api.dto.user.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class JoinRes {
    int userId;
    String nickname;
}
