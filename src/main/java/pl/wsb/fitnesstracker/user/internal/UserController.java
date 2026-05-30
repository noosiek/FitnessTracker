package pl.wsb.fitnesstracker.user.internal;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.wsb.fitnesstracker.user.api.*;

import java.time.LocalDate;
import java.util.List;

/**
 * REST controller exposing CRUD operations for {@link User} resources.
 */
@RestController
@RequestMapping("/v1/users")
@RequiredArgsConstructor
class UserController {

    private final UserService userService;

    private final UserProvider userProvider;

    private final UserMapper userMapper;

    /**
     * Creates a new user.
     *
     * @param userDto user data from the request body
     * @return created user
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UserDto addUser(@RequestBody final UserDto userDto) {
        final User createdUser = userService.createUser(userMapper.toUser(userDto));
        return userMapper.toUserDto(createdUser);
    }

    /**
     * Returns all users with full details.
     *
     * @return list of users
     */
    @GetMapping
    public List<UserDto> getUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Returns simplified user information (id, first name, last name).
     *
     * @return list of simplified users
     */
    @GetMapping("/simple")
    public List<UserSimpleDto> getSimpleUsers() {
        return userProvider.findAllUsers().stream()
                .map(userMapper::toUserSimpleDto)
                .toList();
    }

    /**
     * Returns user details by identifier.
     *
     * @param id user identifier
     * @return user details
     */
    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable final Long id) {
        return userProvider.getUser(id)
                .map(userMapper::toUserDto)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Searches users by email fragment (case-insensitive).
     *
     * @param email email fragment
     * @return matching users (id and email only)
     */
    @GetMapping("/email")
    public List<UserEmailDto> getUsersByEmail(@RequestParam final String email) {
        return userProvider.findUsersByEmailFragment(email).stream()
                .map(userMapper::toUserEmailDto)
                .toList();
    }

    /**
     * Returns users born before the given date.
     *
     * @param time birth date threshold
     * @return users older than the threshold
     */
    @GetMapping("/older/{time}")
    public List<UserDto> getUsersOlderThan(@PathVariable final LocalDate time) {
        return userProvider.findUsersOlderThan(time).stream()
                .map(userMapper::toUserDto)
                .toList();
    }

    /**
     * Updates an existing user.
     *
     * @param userId  user identifier
     * @param userDto updated user data
     * @return updated user
     */
    @PutMapping("/{userId}")
    public UserDto updateUser(@PathVariable final Long userId, @RequestBody final UserDto userDto) {
        final User updatedUser = userService.updateUser(userId, userMapper.toUser(userDto));
        return userMapper.toUserDto(updatedUser);
    }

    /**
     * Deletes a user by identifier.
     *
     * @param userId user identifier
     */
    @DeleteMapping("/{userId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUser(@PathVariable final Long userId) {
        userService.deleteUser(userId);
    }

}
