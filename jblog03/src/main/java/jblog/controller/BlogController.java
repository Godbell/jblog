package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.config.constant.HeaderName;
import jblog.config.constant.JBlogAttribute;
import jblog.config.constant.JBlogView;
import jblog.dto.PostResponseDto;
import jblog.exception.NotFoundException;
import jblog.service.BlogService;
import jblog.service.PostService;
import jblog.vo.BlogVo;

@RequestMapping("/{blogId:^(?!~).*}")
@Controller
public class BlogController {
    private final BlogService blogService;
    private final PostService postService;

    public BlogController(BlogService blogService, PostService postService) {
        this.blogService = blogService;
        this.postService = postService;
    }

    @GetMapping({"admin", "admin/", "/admin/basic"})
    public String viewAdminBasic(
        @PathVariable("blogId") String blogId,
        Model model
    ) {
        BlogVo blog = blogService.getBlog(blogId);

        if (blog == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.BLOG.name(), blog);

        return JBlogView.BLOG_ADMIN_BASIC;
    }

    @GetMapping("/admin/category")
    public String viewAdminCategory(
        @PathVariable("blogId") String blogId,
        Model model
    ) {
        BlogVo blog = blogService.getBlog(blogId);

        if (blog == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.BLOG.name(), blog);

        return JBlogView.BLOG_ADMIN_CATEGORY;
    }

    @GetMapping("/admin/write")
    public String viewAdminWrite(
        @PathVariable("blogId") String blogId,
        Model model
    ) {
        BlogVo blog = blogService.getBlog(blogId);

        if (blog == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.BLOG.name(), blog);

        return JBlogView.BLOG_ADMIN_WRITE;
    }

    @GetMapping({"", "/", "/{categoryId:^[0-9]*$}", "/{categoryId:^[0-9]*$}/{postId:^[0-9]*$}"})
    public String viewBlog(
        @PathVariable("blogId") String blogId,
        @PathVariable(value = "categoryId", required = false) Long categoryId,
        @PathVariable(value = "postId", required = false) Long postId,
        @RequestHeader(value = HeaderName.REFERER, required = false) String referer,
        Model model
    ) {
        if (categoryId == null && postId != null) {
            if (referer != null) {
                return "redirect:" + referer;
            } else {
                return "redirect:" + JBlogView.MAIN;
            }
        }

        BlogVo blog = blogService.getBlog(blogId);

        if (blog == null) {
            throw new NotFoundException();
        }

        PostResponseDto post = postService.getPost(
            postId, blogId, categoryId
        );

        if (categoryId != null && postId != null && post == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.BLOG.name(), blog);
        model.addAttribute(JBlogAttribute.POST.name(), post);

        return JBlogView.BLOG;
    }
}
