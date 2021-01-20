package br.com.alura.livraria.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named("Themes")
@SessionScoped
public class Themes implements Serializable{

	private static final long serialVersionUID = 1L;
	private final List<String> themes;
	
	public Themes() {
		themes = Arrays.asList("afterdark", "afternoon", "afterwork", "aristo",
            "black-tie", "blitzer", "bluesky", "bootstrap", "casablanca",
            "cupertino", "cruze", "dark-hive", "delta", "dot-luv",
            "eggplant", "excite-bike", "flick", "glass-x", "home",
            "hot-sneaks", "humanity", "le-frog", "midnight", "mint-choc",
            "overcast", "pepper-grinder", "redmond", "rocket", "sam",
            "smoothness", "south-street", "start", "sunny", "swanky-purse",
            "trontastic", "ui-darkness", "ui-lightness", "vader");
	}

	public List<String> getThemes(){
		return new ArrayList<>(themes);
	}
	
}
