package com.citybank.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.AccessLevel;
import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Data
public abstract class BaseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(updatable = false)
	private String createdBy;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(updatable = false)
	private Date createdAt;
	private String updatedBy;
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedAt;
	private Boolean isActive;

	@PrePersist
	public void setPreInsertData() {
		this.createdAt = new Date();
		this.isActive = true;
	}

	@PreUpdate
	public void setPreUpdateData() {
		this.updatedAt = new Date();
	}

}
