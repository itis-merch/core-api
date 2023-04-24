package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@Entity
@Table(name = "option")
@Data
public class Option {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@NotNull
	private Integer id;

	@Column(name = "name")
	@NotBlank
	@NotNull
	private String name;

	@Column(name = "properties")
	@NotBlank
	@NotNull
	private String properties;

	public Option(@NotNull @NotBlank String name, @NotNull @NotBlank String properties) {
		this.name = name;
		this.properties = properties;
	}
}
