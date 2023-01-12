package com.codehh.board.api.controller;

import com.codehh.board.api.dto.post.response.GetPostsRes;
import com.codehh.board.api.service.PostService;
import com.codehh.board.db.entity.Post;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api")
@Slf4j
public class PostController {

    @Autowired
    PostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Object> getPosts(@RequestParam int order, @RequestParam int page) {
        HttpStatus status = HttpStatus.OK;
        HashMap<String, Object> result = new HashMap<String, Object>();

        List<GetPostsRes> posts = postService.getPosts(order, page);
        result.put("posts", posts);

        return ResponseEntity.status(status).body(result);
    }
}