package com.itis.merch.core.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@NoArgsConstructor
@Entity
@Table(name = "option")
@Data
public class Option {

	@Id
	private Integer id;
}
