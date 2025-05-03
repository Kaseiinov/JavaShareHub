package kg.attractor.javasharehub.repository;

import kg.attractor.javasharehub.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByRole(String role);
}
