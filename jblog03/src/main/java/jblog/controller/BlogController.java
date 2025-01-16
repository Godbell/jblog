package jblog.controller;

import java.io.IOException;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import jblog.config.auth.Auth;
import jblog.config.blog.Blog;
import jblog.config.constant.HeaderName;
import jblog.config.constant.JBlogAttribute;
import jblog.config.constant.JBlogView;
import jblog.dto.PostCreateDto;
import jblog.dto.PostResponseDto;
import jblog.exception.NotFoundException;
import jblog.service.BlogService;
import jblog.service.PostService;
import jblog.vo.PostVo;

@RequestMapping("/{blogId:^(?!~).*}")
@Controller
public class BlogController {
    private final BlogService blogService;
    private final PostService postService;

    public BlogController(BlogService blogService, PostService postService) {
        this.blogService = blogService;
        this.postService = postService;
    }

    @Auth
    @Blog(requiresOwnership = true)
    @GetMapping({"admin", "admin/", "/admin/basic"})
    public String viewAdminBasic() {
        return JBlogView.BLOG_ADMIN_BASIC;
    }

    @Auth
    @Blog(requiresOwnership = true)
    @PostMapping("/admin/basic")
    public String updateBlog(
        @PathVariable("blogId") String blogId,
        @RequestParam("title") String title,
        @RequestParam("logo-file") MultipartFile file,
        HttpServletRequest request
    ) throws IOException {
        if (title == null || title.isEmpty()) {
            return JBlogView.BLOG_ADMIN_BASIC;
        }

        blogService.updateBlogInfo(blogId, title, file,
            request.getServletContext().getRealPath("/META-INF/uploads")
        );

        return "redirect:/" + blogId + "/admin/basic";
    }

    @Auth
    @Blog(requiresOwnership = true)
    @GetMapping("/admin/category")
    public String viewAdminCategory() {
        return JBlogView.BLOG_ADMIN_CATEGORY;
    }

    @Auth
    @Blog(requiresOwnership = true)
    @GetMapping("/admin/write")
    public String viewAdminWrite(@ModelAttribute PostCreateDto postCreateDto) {
        return JBlogView.BLOG_ADMIN_WRITE;
    }

    @Auth
    @Blog(requiresOwnership = true)
    @PostMapping("/admin/write")
    public String writePost(
        @PathVariable("blogId") String blogId,
        @ModelAttribute @Valid PostCreateDto postCreateDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return JBlogView.BLOG_ADMIN_WRITE;
        }

        PostVo postVo = postService.createPost(postCreateDto);

        return "redirect:/" + blogId + "/" + postVo.getCategoryId() + "/" + postVo.getId();
    }

    @GetMapping({"", "/", "/{categoryId:^[0-9]*$}", "/{categoryId:^[0-9]*$}/{postId:^[0-9]*$}"})
    @Blog
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

        PostResponseDto post = postService.getPost(
            postId, blogId, categoryId
        );

        if (categoryId != null && postId != null && post == null) {
            throw new NotFoundException();
        }

        model.addAttribute(JBlogAttribute.POST.name(), post);

        return JBlogView.BLOG;
    }
}
