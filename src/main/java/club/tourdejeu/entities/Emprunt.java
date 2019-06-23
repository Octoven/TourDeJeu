package club.tourdejeu.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Emprunt implements Serializable {

    @Id
    @GeneratedValue
    private Long idEmprunt;
    @ManyToOne
    @JoinColumn(name = "PSEUDO_UTILISATEUR")
    private Utilisateur utilisateur;
    @ManyToOne
    @JoinColumn(name = "ID_JEU")
    private Jeu jeu;
    private Date dateEmprunt;
    private Date dateRetour;

    public Emprunt() {

    }

    public Emprunt(Utilisateur utilisateur, Jeu jeu, Date dateEmprunt) {
	this.utilisateur = utilisateur;
	this.jeu = jeu;
	this.dateEmprunt = dateEmprunt;
    }

    public Long getIdEmprunt() {
	return idEmprunt;
    }

    public void setIdEmprunt(Long idEmprunt) {
	this.idEmprunt = idEmprunt;
    }

    public Utilisateur getUtilisateur() {
	return utilisateur;
    }

    public void setUtilisateur(Utilisateur utilisateur) {
	this.utilisateur = utilisateur;
    }

    public Jeu getJeu() {
	return jeu;
    }

    public void setJeu(Jeu jeu) {
	this.jeu = jeu;
    }

    public Date getDateEmprunt() {
	return dateEmprunt;
    }

    public void setDateEmprunt(Date dateEmprunt) {
	this.dateEmprunt = dateEmprunt;
    }

    public Date getDateRetour() {
	return dateRetour;
    }

    public void setDateRetour(Date dateRetour) {
	this.dateRetour = dateRetour;
    }

}
