package com.daw.themadridnews.favourite;

import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonView;

@Entity
public class Favourite {

	public static interface Basic {}
	public static interface Details {}

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@JsonView(Details.class)
    private long id;
    
	@NotNull
	@JsonView(Basic.class)
    private boolean madrid;
	@NotNull
	@JsonView(Basic.class)
    private boolean spain;
	@NotNull
	@JsonView(Basic.class)
    private boolean world;
	@NotNull
	@JsonView(Basic.class)
    private boolean sports;
	@NotNull
	@JsonView(Basic.class)
    private boolean technology;
	@NotNull
	@JsonView(Basic.class)
    private boolean culture;


    public Favourite(){
    }
    
    public Favourite(boolean madrid, boolean spain, boolean world, boolean sports, boolean technology, boolean culture) {
		this.madrid = madrid;
		this.spain = spain;
		this.world = world;
		this.sports = sports;
		this.technology = technology;
		this.culture = culture;
	}

	public long getId(){
        return this.id;
    }
    
    public boolean getMadrid(){
        return this.madrid;
    }
    
    public boolean getSpain(){
        return this.spain;
    }
    
    public boolean getWorld(){
        return this.world;
    }
    
    public boolean getSports(){
        return this.sports;
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
    
    public void setMadrid(boolean madrid){
        this.madrid = madrid;
    }
    
    public void setSpain(boolean spain){
        this.spain = spain;
    }
    
    public void setWorld(boolean world){
        this.world = world;
    }
    
    public void setSports(boolean sports){
        this.sports = sports;
    }
    
    public void setTechnology(boolean technology){
        this.technology = technology;
    }
    
    public void setCulture(boolean culture){
        this.culture = culture;
    }


	public String getRandom() {
		ArrayList<String> list = new ArrayList<String>();

		if(getMadrid()) list.add("madrid");
		if(getSpain()) list.add("spain");
		if(getWorld()) list.add("world");
		if(getSports()) list.add("sports");
		if(getTechnology()) list.add("technology");
		if(getCulture()) list.add("culture");
		
		int size = list.size() - 1;
		
		if(size < 0) return null;
		
		int random = (int)(Math.random() * size);
		
		return list.get(random);
	}

	@Override
	public String toString() {
		return "Favourite [id=" + id + ", madrid=" + madrid + ", spain=" + spain + ", world=" + world + ", sports="
				+ sports + ", technology=" + technology + ", culture=" + culture + "]";
	}
}
