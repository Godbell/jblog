package jblog.config.blog;

import java.util.Map;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.config.constant.JBlogAttribute;
import jblog.exception.NotFoundException;
import jblog.service.BlogService;
import jblog.vo.BlogVo;

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

        BlogInfo blogInfo = null;

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if (handlerMethod.getMethodAnnotation(BlogInfo.class) != null) {
            blogInfo = handlerMethod.getMethodAnnotation(BlogInfo.class);
        }
        if (handlerMethod.getBeanType().getAnnotation(BlogInfo.class) != null) {
            blogInfo = handlerMethod.getBeanType().getAnnotation(BlogInfo.class);
        }

        if (blogInfo == null) {
            return true;
        }

        Map<String, String> pathVariables = (Map<String, String>)request.getAttribute(
            HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE
        );

        String blogId = pathVariables.get("blogId");

        if (blogId == null) {
            throw new NotFoundException();
        }

        BlogVo blogVo = blogService.getBlog(blogId);

        if (blogVo == null) {
            throw new NotFoundException();
        }

        request.setAttribute(JBlogAttribute.BLOG.name(), blogVo);

        return true;
    }
}
