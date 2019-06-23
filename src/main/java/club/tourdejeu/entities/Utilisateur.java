package club.tourdejeu.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Utilisateur implements Serializable {

    @Id
    @NotBlank
    @Column(unique = true, length = 26)
    @Size(max = 26)
    private String username;
    @NotBlank
    @Email
    private String email;
    @NotBlank
    private String password;
    @NotBlank
    private String prenom;
    @NotBlank
    private String nom;
    @Size(max = 5)
    private String codePostal;
    @Size(max = 20)
    private String telephone;
    private boolean enabled;
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private Collection<Emprunt> emprunts;
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private Collection<Commentaire> commentaires;
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private Collection<Benevolat> benevolats;
    @OneToMany(mappedBy = "utilisateur", fetch = FetchType.LAZY)
    private Collection<RoleUtilisateur> rolesUtilisateur;

    public Utilisateur() {

    }

    public Utilisateur(String username, String email, String password, String prenom, String nom, String codePostal,
	    String telephone, boolean enabled) {
	this.username = username;
	this.email = email;
	this.password = password;
	this.prenom = prenom;
	this.nom = nom;
	this.codePostal = codePostal;
	this.telephone = telephone;
	this.enabled = enabled;
    }

    public String getUsername() {
	return username;
    }

    public void setUsername(String username) {
	this.username = username;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public String getPassword() {
	return password;
    }

    public void setPassword(String password) {
	this.password = password;
    }

    public String getPrenom() {
	return prenom;
    }

    public void setPrenom(String prenom) {
	this.prenom = prenom;
    }

    public String getNom() {
	return nom;
    }

    public void setNom(String nom) {
	this.nom = nom;
    }

    public String getCodePostal() {
	return codePostal;
    }

    public void setCodePostal(String codePostal) {
	this.codePostal = codePostal;
    }

    public String getTelephone() {
	return telephone;
    }

    public void setTelephone(String telephone) {
	this.telephone = telephone;
    }

    public boolean isEnabled() {
	return enabled;
    }

    public void setEnabled(boolean enabled) {
	this.enabled = enabled;
    }

    public Collection<Emprunt> getEmprunts() {
	return emprunts;
    }

    public void setEmprunts(Collection<Emprunt> emprunts) {
	this.emprunts = emprunts;
    }

    public Collection<Commentaire> getCommentaires() {
	return commentaires;
    }

    public void setCommentaires(Collection<Commentaire> commentaires) {
	this.commentaires = commentaires;
    }

    public Collection<Benevolat> getBenevolats() {
	return benevolats;
    }

    public void setBenevolats(Collection<Benevolat> benevolats) {
	this.benevolats = benevolats;
    }

    public Collection<RoleUtilisateur> getRolesUtilisateur() {
	return rolesUtilisateur;
    }

    public void setRolesUtilisateur(Collection<RoleUtilisateur> rolesUtilisateur) {
	this.rolesUtilisateur = rolesUtilisateur;
    }

}
