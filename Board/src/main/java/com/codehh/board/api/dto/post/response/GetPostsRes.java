package com.codehh.board.api.dto.post.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GetPostsRes {
    int postId;
    String title;
    String content;
    String time;
    int viewCount;
    int like;
    int unlike;
    String nickname;
}
