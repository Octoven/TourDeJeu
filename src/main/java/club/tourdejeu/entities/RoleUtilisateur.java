package club.tourdejeu.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RoleUtilisateur implements Serializable {

    @Id
    @GeneratedValue
    private Long idRoUt;
    @ManyToOne
    @JoinColumn(name = "USERNAME")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "ROLE")
    private Role role;

    public RoleUtilisateur() {

    }

    public RoleUtilisateur(Utilisateur utilisateur, Role role) {
	this.utilisateur = utilisateur;
	this.role = role;
    }

    public Long getIdRoUt() {
	return idRoUt;
    }

    public void setIdRoUt(Long idRoUt) {
	this.idRoUt = idRoUt;
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    public Role getRole() {
	return role;
    }

    public void setRole(Role role) {
	this.role = role;
    }

}
