package com.example.challange.utils.annotations;

public @interface SortBy {
	String[] values();

	String defaultSort() default "";
}
