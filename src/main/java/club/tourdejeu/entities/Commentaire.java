package club.tourdejeu.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.validator.constraints.NotBlank;

@Entity
public class Commentaire implements Serializable {

    @Id
    @GeneratedValue
    private Long idCommentaire;
    @NotBlank
    @Column(length = 666)
    private String commentaire;
    @ManyToOne
    @JoinColumn(name = "PSEUDO_UTILISATEUR")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "ID_EVENEMENT")
    private Evenement evenement;

    public Commentaire() {

    }

    public Commentaire(String commentaire, Utilisateur utilisateur, Evenement evenement) {
	this.commentaire = commentaire;
	this.utilisateur = utilisateur;
	this.evenement = evenement;
    }

    public Long getIdCommentaire() {
	return idCommentaire;
    }

    public void setIdCommentaire(Long idCommentaire) {
	this.idCommentaire = idCommentaire;
    }

    public String getCommentaire() {
	return commentaire;
    }

    public void setCommentaire(String commentaire) {
	this.commentaire = commentaire;
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    public Evenement getInformation() {
	return evenement;
    }

    public void setInformation(Evenement information) {
	this.evenement = information;
    }

    public Evenement getEvenement() {
	return evenement;
    }

    public void setEvenement(Evenement evenement) {
	this.evenement = evenement;
    }

}
