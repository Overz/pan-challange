package com.example.challange.controllers.caches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultCache implements Serializable {

	@Expose
	@SerializedName("version")
	private int version;

	@Expose
	@SerializedName("name")
	private String name;

	@Expose
	@SerializedName("height")
	private String height;

	@Expose
	@SerializedName("mass")
	private String mass;

	@Expose
	@SerializedName("hair_color")
	private String hair_color;

	@Expose
	@SerializedName("skin_color")
	private String skin_color;

	@Expose
	@SerializedName("eye_color")
	private String eye_color;

	@Expose
	@SerializedName("birth_year")
	private String birth_year;

	@Expose
	@SerializedName("gender")
	private String gender;

	@Expose
	@SerializedName("homeworld")
	private String homeworld;

	@Expose
	@SerializedName("films")
	private List<String> films;

	@Expose
	@SerializedName("species")
	private List<String> species;

	@Expose
	@SerializedName("starships")
	private List<String> starships;

	@Expose
	@SerializedName("created")
	private String created;

	@Expose
	@SerializedName("edited")
	private String edited;

	@Expose
	@SerializedName("url")
	private String url;
}
