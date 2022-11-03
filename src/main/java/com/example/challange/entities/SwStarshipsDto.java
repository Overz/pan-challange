package com.example.challange.entities;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwStarshipsDto implements Serializable {

	@Expose
	private String name;

	@Expose
	private String model;

	@Expose
	private String manufacturer;

	@Expose
	private String cost_in_credits;

	@Expose
	private String length;

	@Expose
	private String max_atmosphering_speed;

	@Expose
	private String crew;

	@Expose
	private String passengers;

	@Expose
	private String cargo_capacity;

	@Expose
	private String consumables;

	@Expose
	private String hyperdrive_rating;

	@Expose
	private String MGLT;

	@Expose
	private String starship_class;

	@Expose
	private String pilots;

	@Expose
	private String films;

	@Expose
	private String created;

	@Expose
	private String edited;

	@Expose
	private String url;
}
