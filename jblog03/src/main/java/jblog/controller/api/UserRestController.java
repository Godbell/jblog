package jblog.controller.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jblog.dto.UserIdAvailabilityResponseDto;
import jblog.service.UserService;

@RequestMapping("/api/user")
@RestController
public class UserRestController {
    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/id/availability")
    public UserIdAvailabilityResponseDto getUserIdAvailability(@RequestParam("id") String id) {
        return new UserIdAvailabilityResponseDto(this.userService.isUserIdAvailable(id));
    }
}
