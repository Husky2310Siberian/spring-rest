package aueb.gr.pm.schoolapp.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import aueb.gr.pm.schoolapp.model.User;

import java.time.LocalDate;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> , JpaSpecificationExecutor<User> {

    Optional<User> findByVat(String vat);
    Optional<User> findUserByUsername(String username);
}
