package com.example.challange.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwSpeciesDto implements Serializable {

	@Expose
	private String name;

	@Expose
	private String classification;

	@Expose
	private String designation;

	@Expose
	private String average_height;

	@Expose
	private String skin_colors;

	@Expose
	private String hair_colors;

	@Expose
	private String eye_colors;

	@Expose
	private String average_lifespan;

	@Expose
	private String homeworld;

	@Expose
	private String language;

	@Expose
	private String people;

	@Expose
	private String films;

	@Expose
	private String created;

	@Expose
	private String edited;

	@Expose
	private String url;
}
