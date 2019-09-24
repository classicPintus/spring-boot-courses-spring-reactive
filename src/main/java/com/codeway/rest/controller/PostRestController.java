package com.codeway.rest.controller;

import com.codeway.rest.model.PostModel;
import org.springframework.http.ResponseEntity;

public interface PostRestController {

    ResponseEntity<PostModel> getPost(String id);
    ResponseEntity<PostModel> addPost();
}
