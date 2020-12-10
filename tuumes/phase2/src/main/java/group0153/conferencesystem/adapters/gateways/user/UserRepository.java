package group0153.conferencesystem.adapters.gateways.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel, Long> {
    Optional<UserModel> findByEmail(String email);
    Optional<UserModel> findByResourceId(String id);
}

