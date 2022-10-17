package com.example.challange.controllers.caches;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseCache<T> implements Serializable {

	private String url;
	private T data;
}
