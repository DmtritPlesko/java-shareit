package ru.practicum.shareit.user;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.practicum.shareit.user.model.User;
import ru.practicum.shareit.user.service.UserService;

/**
 * TODO Sprint add-controllers.
 */
@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public User addNewUser(@Valid @RequestBody User user) {
        log.info("я зашел в добавление ");
        return userService.addNewUser(user);
    }

    @PatchMapping("/{userId}")
    public User updateUser(@PathVariable("userId") Long userId,
                           @Valid @RequestBody User user) {
        return userService.updateUser(userId, user);
    }

    @GetMapping("/{userId}")
    public User getUserBuId(@PathVariable("userId") Long userId) {
        return userService.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserBuID(@Positive @PathVariable("userId") Long userId) {
        userService.deleteUserById(userId);
    }

}
