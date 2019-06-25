package club.tourdejeu.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import club.tourdejeu.entities.RoleUtilisateur;

public interface RoleUtilisateurRepository extends JpaRepository<RoleUtilisateur, Long> {

    public final static String CHERCHER_ROLE = "select ru from RoleUtilisateur ru where ru.utilisateur.username like :x order by ru.role desc";
    public final static String VERIF_ROLE = "select ru from RoleUtilisateur ru where ru.utilisateur.username like :x and ru.role.role like :y";

    // fetching role and user association page by username
    @Query(CHERCHER_ROLE)
    Page<RoleUtilisateur> rolesUtilisateur(@Param("x") String pseudo, Pageable pageable);

    // fetching role and user association by username and role
    @Query(VERIF_ROLE)
    RoleUtilisateur verifierRole(@Param("x") String username, @Param("y") String role);

}
