package com.citybank.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
public class ActionLog implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String email;
	private String description;
	private String actionMessage;
	private Long userId;

	public ActionLog() {
		super();
	}

	public ActionLog(String email, String description, String actionMessage, Long userId) {
		super();
		this.email = email;
		this.description = description;
		this.actionMessage = actionMessage;
		this.userId = userId;
	}

}
