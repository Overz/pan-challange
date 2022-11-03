package com.example.challange.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.Hibernate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = SwBaseDto.SW_BASE_TABLE)
@Entity(name = SwBaseDto.SW_BASE_TABLE)
public class SwBaseDto implements Serializable {

	public static final String SW_BASE_TABLE = "swbase";
	public static final String SW_BASE_ID = "resource";

	@Id
	@Column(name = SW_BASE_ID, nullable = false)
	private String resource;

	@Column(name = "content", nullable = false, length = Integer.MAX_VALUE)
	private String content;

}
