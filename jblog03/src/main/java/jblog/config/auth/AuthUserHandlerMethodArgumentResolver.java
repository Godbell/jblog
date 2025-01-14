package jblog.config.auth;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebArgumentResolver;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import jakarta.servlet.http.HttpServletRequest;
import jblog.config.constant.JBlogAttribute;
import jblog.vo.UserVo;
public class AuthUserHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        AuthUser user = parameter.getParameterAnnotation(AuthUser.class);

        if (user == null || !parameter.getParameterType().equals(UserVo.class)) {
            return false;
        }

        return true;
    }

    @Override
    public Object resolveArgument(
        MethodParameter parameter,
        ModelAndViewContainer modelAndViewContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) throws Exception {
        if (!supportsParameter(parameter)) {
            return WebArgumentResolver.UNRESOLVED;
        }

        HttpServletRequest req = (HttpServletRequest)webRequest.getNativeRequest();
        return req.getSession().getAttribute(JBlogAttribute.SIGNED_USER.name());
    }
}
