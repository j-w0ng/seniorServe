package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.User;
import seniorServe.seniorServe.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("api/v1/user")
@RestController
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService)
    {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user)
    {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers()
    {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{username}")
    public User getUserByUsername(@PathVariable("username") String username)
    {
        return userService.getUserByUsername(username).orElse(null);
    }

    @DeleteMapping(path = "{username}")
    public void deleteUser(@PathVariable("username") String username)
    {
        userService.deleteUser(username);
    }

    @PutMapping(path = "{username}")
    public void updateUser(@PathVariable("username") String username, @Valid @NonNull @RequestBody User user)
    {
        userService.updateUser(username, user);
    }
}
