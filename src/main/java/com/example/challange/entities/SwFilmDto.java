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
public class SwFilmDto implements Serializable {

	@Expose
	private String title;

	@Expose
	private Integer episode_id;

	@Expose
	private String opening_crawl;

	@Expose
	private String director;

	@Expose
	private String producer;

	@Expose
	private String release_date;

	@Expose
	private String created;

	@Expose
	private String edited;

	@Expose
	private String url;

	@Expose
	private List<String> characters;

	@Expose
	private List<String> planets;

	@Expose
	private List<String> starships;

	@Expose
	private List<String> vehicles;

	@Expose
	private List<String> species;
}
