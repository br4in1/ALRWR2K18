/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import java.sql.Date;

/**
 *
 * @author raiiz
 */
public class Article {
    private int id;
    private String titre;
    private String contenu;
    private int idEntity;
    private String typeEntity;
    private Date datePublication;
    private Date derniereModification;
    private int auteur;

    public Article(int id, String titre, String contenu, int idEntity, String typeEntity, Date datePublication, Date derniereModification, int auteur) {
        this.id = id;
        this.titre = titre;
        this.contenu = contenu;
        this.idEntity = idEntity;
        this.typeEntity = typeEntity;
        this.datePublication = datePublication;
        this.derniereModification = derniereModification;
        this.auteur = auteur;
    }

    public Article() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public int getIdEntity() {
        return idEntity;
    }

    public void setIdEntity(int idEntity) {
        this.idEntity = idEntity;
    }

    public String getTypeEntity() {
        return typeEntity;
    }

    public void setTypeEntity(String typeEntity) {
        this.typeEntity = typeEntity;
    }

    public Date getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(Date datePublication) {
        this.datePublication = datePublication;
    }

    public Date getDerniereModification() {
        return derniereModification;
    }

    public void setDerniereModification(Date derniereModification) {
        this.derniereModification = derniereModification;
    }

    public int getAuteur() {
        return auteur;
    }

    public void setAuteur(int auteur) {
        this.auteur = auteur;
    }

    @Override
    public String toString() {
        return "Article{" + "id=" + id + ", titre=" + titre + ", contenu=" + contenu + ", idEntity=" + idEntity + ", typeEntity=" + typeEntity + ", datePublication=" + datePublication + ", derniereModification=" + derniereModification + ", auteur=" + auteur + '}';
    }
}
