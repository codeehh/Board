package com.codehh.board.api.service;

import com.codehh.board.api.dto.post.response.GetPostsRes;
import com.codehh.board.db.entity.Post;
import com.codehh.board.db.repository.PostRepository;
import com.codehh.board.db.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    PostRepository postRepository;
    @Autowired
    UserRepository userRepository;

    @Override
    public List<GetPostsRes> getPosts(int order, int page) {
        PageRequest pageRequest = PageRequest.of(page - 1, 10, Sort.Direction.DESC, "postId");
        Page<Post> posts = postRepository.findAll(pageRequest);
        List<GetPostsRes> lists = new ArrayList<>();
        for (Post post : posts) {
            GetPostsRes getPostsRes = new GetPostsRes();

            getPostsRes.setPostId(post.getPostId());
            getPostsRes.setTitle(post.getTitle());
            getPostsRes.setContent(post.getContent());
            getPostsRes.setNickname(userRepository.findByUserId(post.getUserId()).getNickname());
            getPostsRes.setTime(post.getTime());
            getPostsRes.setViewCount(post.getViewCount());
            getPostsRes.setLike(post.getLike());
            getPostsRes.setUnlike(post.getUnlike());

            lists.add(getPostsRes);
        }

        return lists;
    }
}
