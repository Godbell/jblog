package jblog.config.auth;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jblog.config.constant.JBlogAttribute;
import jblog.vo.UserVo;
public class AuthInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler)
        throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Auth auth = null;

        HandlerMethod handlerMethod = (HandlerMethod)handler;
        if (handlerMethod.getMethodAnnotation(Auth.class) != null) {
            auth = handlerMethod.getMethodAnnotation(Auth.class);
        }
        if (handlerMethod.getBeanType().getAnnotation(Auth.class) != null) {
            auth = handlerMethod.getBeanType().getAnnotation(Auth.class);
        }

        // if no @Auth annotated
        if (auth == null) {
            // run controller method
            return true;
        }

        UserVo userVo = (UserVo)req.getSession().getAttribute(JBlogAttribute.SIGNED_USER.name());

        if (userVo == null) {
            res.sendRedirect(req.getContextPath() + "/user/signin");
            return false;
        }

        return true;
    }
}
