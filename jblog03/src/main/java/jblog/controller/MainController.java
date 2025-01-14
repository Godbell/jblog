package jblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jblog.config.constant.JBlogView;

@Controller
@RequestMapping
public class MainController {
    @GetMapping({"", "/"})
    public String viewMain() {
        return JBlogView.MAIN;
    }
}
