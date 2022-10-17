package com.example.challange.controllers.caches;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentCache<T> implements Serializable {

	@Expose
	@SerializedName("count")
	private int count;

	@Expose
	@SerializedName("next")
	private String next;

	@Expose
	@SerializedName("previous")
	private String previous;

	@Expose
	@SerializedName("results")
	private T results;
}
