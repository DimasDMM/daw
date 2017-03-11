package com.daw.themadridnews.theme;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Theme {
    //Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private boolean sports;
    private boolean madrid;
    private boolean international;
    private boolean national;
    private boolean technology;
    private boolean culture;
    
    //Constructor
    public Theme(){
    }
    
    //Methods Getter & Setter
    public long getId(){
        return this.id;
    }
    
    public boolean getSports(){
        return this.sports;
    }
    
    public boolean getMadrid(){
        return this.madrid;
    }
    
    public boolean getInternational(){
        return this.international;
    }
    
    public boolean getNational(){
        return this.national;
    }
    
    public boolean getTechnology(){
        return this.technology;
    }
    
    public boolean getCulture(){
        return this.culture;
    }
    
    public void setId(long id){
        this.id = id;
    }
    
    public void setSports(boolean sports){
        this.sports = sports;
    }
    
    public void setMadrid(boolean madrid){
        this.madrid = madrid;
    }
    
    public void setInternational(boolean international){
        this.international = international;
    }
    
    public void setNational(boolean national){
        this.national = national;
    }
    
    public void setTechnology(boolean technology){
        this.technology = technology;
    }
    
    public void setCulture(boolean culture){
        this.culture = culture;
    }
    
    
    
}
