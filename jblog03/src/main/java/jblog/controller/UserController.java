package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.validation.Valid;
import jblog.dto.UserJoinRequestDto;
import jblog.service.UserService;

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
}
