package club.tourdejeu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import club.tourdejeu.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    // not sure why this was added - need to check the real use of this method
    // public Role findByRole(String role);

}
