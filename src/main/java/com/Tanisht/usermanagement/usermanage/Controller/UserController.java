package com.Tanisht.usermanagement.usermanage.Controller;

import com.Tanisht.usermanagement.usermanage.Model.SignInInput;
import com.Tanisht.usermanagement.usermanage.Model.SignUpOutput;
import com.Tanisht.usermanagement.usermanage.Model.User;
import com.Tanisht.usermanagement.usermanage.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController

public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/home")
    public String homeController(){
        return "success";
    }
    @PostMapping("signup")
    public ResponseEntity<SignUpOutput> signup(@RequestBody @Validated User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody SignInInput signInInput) {

        return ResponseEntity.ok(userService.loginUser(signInInput));
    }

    @GetMapping("user/profile")

    public ResponseEntity<User> getUserProfile() {

        User user = userService.getCurrentUser();
        return ResponseEntity.ok(user);
    }

    @GetMapping("admin/{userId}")

    public ResponseEntity<User> getUser(@PathVariable Long userId) {

        User user = userService.getUserById(userId);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }



    @PatchMapping("admin/{userId}")

    public ResponseEntity<String> updateAttributes(@PathVariable Long userId, @RequestBody User user) {

        userService.updateUserAttributes(userId, user);
        return ResponseEntity.ok("User attributes updated successfully");
    }

    @DeleteMapping("admin/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        // This endpoint is accessible only to users with the ROLE_ADMIN role.
        // If the user is authorized, delete the user record.
        userService.deleteUser(userId);
        return ResponseEntity.ok("User deleted successfully");
    }
}
