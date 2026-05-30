package pl.wsb.fitnesstracker.user.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.wsb.fitnesstracker.user.api.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Query searching users by email address. It matches by exact match.
     *
     * @param email email of the user to search
     * @return {@link Optional} containing found user or {@link Optional#empty()} if none matched
     */
    default Optional<User> findByEmail(String email) {
        return findAll().stream()
                .filter(user -> Objects.equals(user.getEmail(), email))
                .findFirst();
    }

    /**
     * Finds users whose email contains the given fragment, ignoring case.
     *
     * @param emailFragment fragment of the email address
     * @return list of matching users
     */
    default List<User> findByEmailFragmentIgnoreCase(String emailFragment) {
        final String normalizedFragment = emailFragment.toLowerCase(Locale.ROOT);
        return findAll().stream()
                .filter(user -> user.getEmail().toLowerCase(Locale.ROOT).contains(normalizedFragment))
                .toList();
    }

    /**
     * Finds users born before the given date.
     *
     * @param birthdateThreshold birth date threshold
     * @return list of users older than the threshold
     */
    default List<User> findByBirthdateBefore(LocalDate birthdateThreshold) {
        return findAll().stream()
                .filter(user -> user.getBirthdate().isBefore(birthdateThreshold))
                .toList();
    }

}
