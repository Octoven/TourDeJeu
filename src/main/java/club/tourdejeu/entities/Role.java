package club.tourdejeu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Role implements Serializable {

    @Id
    @NotBlank
    @Column(unique = true, length = 26)
    private String role;
    @OneToMany(mappedBy = "role", fetch = FetchType.LAZY)
    private Collection<RoleUtilisateur> roleUtilisateurs;

    public Role() {

    }

    public Role(String role) {
	this.role = role;
    }

    public String getRole() {
	return role;
    }

    public void setRole(String role) {
	this.role = role;
    }

    public Collection<RoleUtilisateur> getRoleUtilisateurs() {
	return roleUtilisateurs;
    }

    public void setRoleUtilisateurs(Collection<RoleUtilisateur> roleUtilisateurs) {
	this.roleUtilisateurs = roleUtilisateurs;
    }

}
