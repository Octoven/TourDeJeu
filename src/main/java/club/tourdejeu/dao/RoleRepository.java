package club.tourdejeu.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import club.tourdejeu.entities.Role;

public interface RoleRepository extends JpaRepository<Role, String> {

    public Role findByRole(String role);

}
