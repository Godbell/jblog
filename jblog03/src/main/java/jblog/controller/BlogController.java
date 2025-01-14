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
import jblog.exception.NotFoundException;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

@RequestMapping("/{blogId:^(?!~).*}")
@Controller
public class BlogController {
    private final BlogService blogService;

    public BlogController(BlogService blogService) {
        this.blogService = blogService;
    }

    @GetMapping({"", "/", "/{categoryId}", "/{categoryId}/{postId}"})
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

        BlogVo blogVo = blogService.getBlog(blogId);

        if (blogVo == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.BLOG.name(), blogVo);
        return JBlogView.BLOG;
    }
}
