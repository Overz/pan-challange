package com.example.challange.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwPlanetDto implements Serializable {

	@Expose
	private String name;

	@Expose
	private String rotation_period;

	@Expose
	private String orbital_period;

	@Expose
	private String diameter;

	@Expose
	private String climate;

	@Expose
	private String gravity;

	@Expose
	private String terrain;

	@Expose
	private String surface_water;

	@Expose
	private String population;

	@Expose
	private String residents;

	@Expose
	private String films;

	@Expose
	private String created;

	@Expose
	private String edited;

	@Expose
	private String url;
}
