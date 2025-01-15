package jblog.controller.api;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jblog.config.constant.HeaderName;
import jblog.service.PostService;

@RequestMapping("/api/{blogId:^(?!~).*}")
@RestController
public class BlogRestController {
    private final PostService postService;

    public BlogRestController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{categoryId}")
    public String getPostList(
        @PathVariable("blogId") String blogId,
        @PathVariable("categoryId") Long categoryId,
        HttpServletResponse res
    ) throws IOException {
        res.setHeader(HeaderName.CONTENT_TYPE, "application/json");

        return postService.getPostListJsonString(
            blogId, categoryId
        );
    }
}
