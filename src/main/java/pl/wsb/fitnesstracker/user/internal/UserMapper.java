package pl.wsb.fitnesstracker.user.internal;

import org.springframework.stereotype.Component;
import pl.wsb.fitnesstracker.user.api.User;
import pl.wsb.fitnesstracker.user.api.UserDto;
import pl.wsb.fitnesstracker.user.api.UserEmailDto;
import pl.wsb.fitnesstracker.user.api.UserSimpleDto;

/**
 * Maps between {@link User} entities and user DTOs.
 */
@Component
class UserMapper {

    UserDto toUserDto(final User user) {
        return new UserDto(user.getId(), user.getFirstName(), user.getLastName(), user.getBirthdate(), user.getEmail());
    }

    UserSimpleDto toUserSimpleDto(final User user) {
        return new UserSimpleDto(user.getId(), user.getFirstName(), user.getLastName());
    }

    UserEmailDto toUserEmailDto(final User user) {
        return new UserEmailDto(user.getId(), user.getEmail());
    }

    User toUser(final UserDto userDto) {
        return new User(userDto.firstName(), userDto.lastName(), userDto.birthdate(), userDto.email());
    }

}
