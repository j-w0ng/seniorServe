package seniorServe.seniorServe.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.*;
import seniorServe.seniorServe.model.User;
import seniorServe.seniorServe.service.UserService;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RequestMapping("api/v1/user")
@RestController
public class UserController
{

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void addUser(@Valid @NonNull @RequestBody User user) {
        userService.addUser(user);
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getAllUsers();
    }

    @GetMapping(path = "{username}")
    public User getUserByUsername(@PathVariable("username") String username) {
        return userService.getUserByUsername(username);
    }

    @DeleteMapping(path = "{username}")
    public void deleteUser(@PathVariable("username") String username)
    {
        userService.deleteUser(username);
    }

    @PutMapping(path = "{username}")
    public void updateUser(@PathVariable("username") String username, @Valid @NonNull @RequestBody User user) {
        userService.updateUser(username, user);
    }

    @GetMapping(path = "/senior")
    public List<User> getSeniors() {
        return userService.selectSenior();
    }

    @DeleteMapping(path = "/senior/username={user}")
    public int deleteSenior(@PathVariable("user") String username) {
        return userService.deleteSenior(username);
    }

    @PostMapping(path = "/senior")
    public int insertSenior(@Valid @NonNull @RequestBody User user) {
        return userService.insertSenior(user);
    }

    @GetMapping(path = "/senior/{user}")
    public int isSenior(@PathVariable("user") String username) {
        return userService.isSenior(username);
    }

    @GetMapping(path = "/volunteer")
    public List<User> getVolunteers() {
        return userService.selectVolunteer();
    }

    @DeleteMapping(path = "/volunteer/username={user}")
    public int deleteVolunteer(@PathVariable("user") String username) {
        return userService.deleteVolunteer(username);
    }

    @PostMapping(path = "/volunteer")
    public int insertVolunteer(@Valid @NonNull @RequestBody User user) {
        return userService.insertVolunteer(user);
    }

    @GetMapping(path = "/volunteer/{user}")
    public int isVolunteer(@PathVariable("user") String username) {
        return userService.isVolunteer(username);
    }

    @GetMapping(path = "/volunteerAllSenior")
    public List<User> getVolunteerVolunteeredForAllSenior() {
        return userService.getVolunteerVolunteeredForAllSeniors();
    }
}
