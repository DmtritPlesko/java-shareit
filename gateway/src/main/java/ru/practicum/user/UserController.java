package ru.practicum.user;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.practicum.user.dto.UserDto;

@RestController
@RequestMapping(path = "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserClient userClient;

    @PostMapping
    public ResponseEntity<Object> addNewUser(@Valid @RequestBody UserDto user) {
        return userClient.addNewUser(user);
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<Object> updateUser(@PathVariable("userId") Long userId,
                                             @Valid @RequestBody UserDto user) {
        return userClient.updateUser(userId, user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<Object> getUserBuId(@PathVariable("userId") Long userId) {
        return userClient.getUserById(userId);
    }

    @DeleteMapping("/{userId}")
    public void deleteUserBuID(@PathVariable("userId") Long userId) {
        userClient.deleteUserById(userId);
    }
}
