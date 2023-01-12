package com.codehh.board.api.service;

import com.codehh.board.api.dto.post.response.GetPostsRes;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
class PostServiceImplTest {

    @Autowired
    PostService postService;

    @Test
    void getPostsTest() {
        List<GetPostsRes> posts = postService.getPosts(0, 1);
        for (GetPostsRes post : posts) {
            System.out.println("post = " + post);
        }
    }

}