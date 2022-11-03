package com.example.challange.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwPeopleDto implements Serializable {

	@Expose
	private String name;

	@Expose
	private String height;

	@Expose
	private String mass;

	@Expose
	private String hair_color;

	@Expose
	private String skin_color;

	@Expose
	private String eye_color;

	@Expose
	private String birth_year;

	@Expose
	private String gender;

	@Expose
	private String homeworld;

	@Expose
	private List<String> films;

	@Expose
	private List<String> species;

	@Expose
	private List<String> vehicles;

	@Expose
	private List<String> starships;

	@Expose
	private String created;

	@Expose
	private String edited;

	@Expose
	private String url;
}
