package com.codehh.board.api.service;

import com.codehh.board.api.dto.post.response.GetPostsRes;

import java.util.List;

public interface PostService {
    List<GetPostsRes> getPosts(int order, int page);
}
