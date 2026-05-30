package pl.wsb.fitnesstracker.user.api;

/**
 * Simplified user data transfer object containing basic identification fields.
 */
public record UserSimpleDto(Long id, String firstName, String lastName) {

}
