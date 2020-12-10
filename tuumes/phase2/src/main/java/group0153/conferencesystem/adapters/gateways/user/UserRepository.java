package group0153.conferencesystem.adapters.gateways.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    /**
     * Return a list of UserModels that are signed up to the specified email.
     *
     * @param email the String email of a user
     * @return List of UserModels that correspond to the provided email.
     */
    List<UserModel> findByEmail(String email);

    /**
     * Return an Optional that may contain the UserModel corresponding to the id specified
     *
     * @param userId the id of a user
     * @return Optional possibly containing the UserModel corresponding to the specified id
     */
    Optional<UserModel> findById(String userId);
}

