package jblog.controller.api;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jblog.config.constant.HeaderName;
import jblog.service.CategoryService;
import jblog.service.PostService;

@RequestMapping("/api/{blogId:^(?!~).*}")
@RestController
public class BlogRestController {
    private final PostService postService;
    private final CategoryService categoryService;

    public BlogRestController(
        PostService postService,
        CategoryService categoryService
    ) {
        this.postService = postService;
        this.categoryService = categoryService;
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

    @GetMapping("/category/all")
    public String getCategoryList(
        @PathVariable("blogId") String blogId,
        HttpServletResponse res
    ) throws IOException {
        res.setHeader(HeaderName.CONTENT_TYPE, "application/json");
        return categoryService.getCategoryListJsonString(blogId);
    }
}
