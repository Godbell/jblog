package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jblog.dto.SignInDto;
import jblog.dto.UserJoinRequestDto;
import jblog.service.UserService;
import jblog.vo.UserVo;

@Controller
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/join")
    public String viewJoin(
        @ModelAttribute UserJoinRequestDto userJoinRequestDto
    ) {
        return "user/join";
    }

    @PostMapping("/join")
    public String join(
        @Valid @ModelAttribute UserJoinRequestDto userJoinRequestDto,
        BindingResult result,
        Model model
    ) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "user/join";
        }

        userService.createUser(userJoinRequestDto);

        return "redirect:/user/joinsuccess";
    }

    @GetMapping("/joinsuccess")
    public String viewJoinSuccess() {
        return "user/joinsuccess";
    }

    @GetMapping("/signin")
    public String signIn(@ModelAttribute SignInDto signInDto) {
        return "user/login";
    }

    @PostMapping("/signin")
    public String signIn(
        @Valid @ModelAttribute SignInDto signInDto,
        BindingResult result,
        Model model,
        HttpServletRequest req
    ) {
        if (result.hasErrors()) {
            model.addAllAttributes(result.getModel());
            return "user/login";
        }

        UserVo user = userService.getUser(signInDto.getId(), signInDto.getPassword());

        if (user == null) {
            return "user/login";
        }

        HttpSession session = req.getSession();
        session.setAttribute("user", user);

        return "redirect:/";
    }
}
