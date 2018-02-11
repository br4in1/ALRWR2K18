/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.Entities;

import java.util.Date;
import org.joda.time.DateTime;

/**
 *
 * @author Sof
 */
public class Evenement {
      private int id ; 
    private String nom ; 
    private double geolat ; 
    private double geolong ; 
    private DateTime dateDebut ;  
    private DateTime dateFin ;  
    private String Link ; 
    private String Image ; 
    private String City ; 

    public Evenement(int id, String nom, double geolat, double geolong, DateTime dateDebut, DateTime dateFin, String Link, String Image, String City) {
        this.id = id;
        this.nom = nom;
        this.geolat = geolat;
        this.geolong = geolong;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.Link = Link;
        this.Image = Image;
        this.City = City;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public double getGeolat() {
        return geolat;
    }

    public double getGeolong() {
        return geolong;
    }

    public DateTime getDateDebut() {
        return dateDebut;
    }

    public DateTime getDateFin() {
        return dateFin;
    }

    public String getLink() {
        return Link;
    }

    public String getImage() {
        return Image;
    }

    public String getCity() {
        return City;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setGeolat(double geolat) {
        this.geolat = geolat;
    }

    public void setGeolong(double geolong) {
        this.geolong = geolong;
    }

    public void setDateDebut(DateTime dateDebut) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin(DateTime dateFin) {
        this.dateFin = dateFin;
    }

    public void setLink(String Link) {
        this.Link = Link;
    }

    public void setImage(String Image) {
        this.Image = Image;
    }

    public void setCity(String City) {
        this.City = City;
    }
    
}
