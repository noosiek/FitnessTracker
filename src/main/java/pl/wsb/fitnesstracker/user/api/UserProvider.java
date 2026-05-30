package pl.wsb.fitnesstracker.user.api;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface UserProvider {

    /**
     * Retrieves a user based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param userId id of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUser(Long userId);

    /**
     * Retrieves a user based on their email.
     * If the user with given email is not found, then {@link Optional#empty()} will be returned.
     *
     * @param email The email of the user to be searched
     * @return An {@link Optional} containing the located user, or {@link Optional#empty()} if not found
     */
    Optional<User> getUserByEmail(String email);

    /**
     * Retrieves all users.
     *
     * @return list of all users
     */
    List<User> findAllUsers();

    /**
     * Retrieves users whose email contains the given fragment (case-insensitive).
     *
     * @param emailFragment fragment of the email address
     * @return matching users
     */
    List<User> findUsersByEmailFragment(String emailFragment);

    /**
     * Retrieves users born before the given date (older than the given birth date threshold).
     *
     * @param birthdateThreshold birth date threshold
     * @return users older than the threshold
     */
    List<User> findUsersOlderThan(LocalDate birthdateThreshold);

}
