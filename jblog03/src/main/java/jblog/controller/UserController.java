package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jblog.config.auth.Auth;
import jblog.config.constant.HeaderName;
import jblog.config.constant.JBlogAttribute;
import jblog.config.constant.JBlogRequestMapping;
import jblog.config.constant.JBlogView;
import jblog.dto.SignInDto;
import jblog.dto.UserJoinRequestDto;
import jblog.service.UserService;
import jblog.vo.UserVo;

@Controller
@RequestMapping(JBlogRequestMapping.USER)
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping(JBlogRequestMapping.USER_JOIN)
    public String viewJoin(
        @ModelAttribute UserJoinRequestDto userJoinRequestDto
    ) {
        return JBlogView.USER_JOIN;
    }

    @PostMapping(JBlogRequestMapping.USER_JOIN)
    public String join(
        @Valid @ModelAttribute UserJoinRequestDto userJoinRequestDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return JBlogView.USER_JOIN;
        }

        userService.createUser(userJoinRequestDto);

        return String.format(
            "redirect:%s%s"
            , JBlogRequestMapping.USER
            , JBlogRequestMapping.USER_JOINSUCCESS
        );
    }

    @GetMapping(JBlogRequestMapping.USER_JOINSUCCESS)
    public String viewJoinSuccess() {
        return JBlogView.USER_JOINSUCCESS;
    }

    @GetMapping(JBlogRequestMapping.USER_SIGNIN)
    public String signIn(@ModelAttribute SignInDto signInDto) {
        return JBlogView.USER_SIGNIN;
    }

    @PostMapping(JBlogRequestMapping.USER_SIGNIN)
    public String signIn(
        @Valid @ModelAttribute SignInDto signInDto,
        BindingResult result,
        Model model,
        HttpSession session
    ) {
        if (result.hasErrors()) {
            model.addAttribute(
                JBlogAttribute.ERRORS.name(),
                result.getAllErrors()
            );
            return JBlogView.USER_SIGNIN;
        }

        UserVo user = userService.getUser(signInDto.getId(), signInDto.getPassword());

        if (user == null) {
            model.addAttribute(
                JBlogAttribute.ERRORS.name(),
                "login failed"
            );
            return JBlogView.USER_SIGNIN;
        }

        session.setAttribute(JBlogAttribute.SIGNED_USER.name(), user);
        return "redirect:" + JBlogRequestMapping.MAIN;
    }

    @Auth
    @GetMapping(JBlogRequestMapping.USER_SIGNOUT)
    public String signOut(
        HttpSession session, @RequestHeader(HeaderName.REFERER) String referer
    ) {
        session.removeAttribute(JBlogAttribute.SIGNED_USER.name());
        session.invalidate();

        return "redirect:" + referer;
    }
}
