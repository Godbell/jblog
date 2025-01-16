package jblog.controller.api;

import java.io.IOException;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletResponse;
import jblog.config.blog.Blog;
import jblog.config.constant.HeaderName;
import jblog.dto.CategoryCreateDto;
import jblog.exception.BadRequestException;
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

    @Blog(requiresOwnership = true)
    @PostMapping("/category")
    public String createCategory(
        @PathVariable("blogId") String blogId,
        @RequestBody CategoryCreateDto dto
    ) {
        if (
            dto.getName() == null || "".equals(dto.getName())
        ) {
            throw new BadRequestException();
        }

        dto.setBlogId(blogId);
        categoryService.createCategory(dto);
        return "OK";
    }

    @Blog(requiresOwnership = true)
    @GetMapping("/category/{categoryId}/delete")
    public String deleteCategory(
        @PathVariable("blogId") String blogId,
        @PathVariable("categoryId") Long categoryId
    ) {
        categoryService.deleteCategory(categoryId, blogId);
        return "OK";
    }
}
