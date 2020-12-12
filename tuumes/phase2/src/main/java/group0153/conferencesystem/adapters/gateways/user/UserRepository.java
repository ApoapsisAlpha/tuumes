package group0153.conferencesystem.adapters.gateways.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * An interface for database operations pertaining to users.
 */
public interface UserRepository extends JpaRepository<UserModel, Long> {
    /**
     * Finds a user based on their email.
     *
     * @param email The users email.
     * @return List of users that match the email.
     */
    Optional<UserModel> findByEmail(String email);

    /**
     * Find a user based on their database/resource id.
     *
     * @param id The user's database/resource id.
     * @return The user with the id. (Empty if they don't exist)
     */
    Optional<UserModel> findByResourceId(String id);
}

