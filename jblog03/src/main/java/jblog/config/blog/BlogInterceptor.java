package jblog.config.blog;

import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.config.constant.JBlogAttribute;
import jblog.config.constant.JBlogRequestMapping;
import jblog.exception.NotFoundException;
import jblog.service.BlogService;
import jblog.vo.BlogVo;
import jblog.vo.UserVo;

public class BlogInterceptor implements HandlerInterceptor {
    private final BlogService blogService;

    public BlogInterceptor(BlogService blogService) {
        this.blogService = blogService;
    }

    @Override
    public boolean preHandle(
        HttpServletRequest request,
        HttpServletResponse response,
        Object handler
    ) throws
        Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Blog blog = null;

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if (handlerMethod.getMethodAnnotation(Blog.class) != null) {
            blog = handlerMethod.getMethodAnnotation(Blog.class);
        }
        if (handlerMethod.getBeanType().getAnnotation(Blog.class) != null) {
            blog = handlerMethod.getBeanType().getAnnotation(Blog.class);
        }

        if (blog == null) {
            return true;
        }

        Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(
            HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );

        String blogId = pathVariables.get("blogId");
        System.out.println("blogId " + blogId);

        System.out.println("ownership requirement: " + blog.requiresOwnership());

        if (blog.requiresOwnership() == true) {
            UserVo userVo = (UserVo)request.getSession().getAttribute(JBlogAttribute.SIGNED_USER.name());
            System.out.println("userId " + userVo.getId());

            if (userVo == null) {
                response.sendRedirect(JBlogRequestMapping.USER + JBlogRequestMapping.USER_SIGNIN);
                return false;
            }

            if (!blogId.equals(userVo.getId())) {
                throw new NotFoundException();
            }
        }

        BlogVo blogVo = blogService.getBlog(blogId);

        if (blogVo == null) {
            throw new NotFoundException();
        }

        request.setAttribute(JBlogAttribute.BLOG.name(), blogVo);

        return true;
    }
}
