package com.example.demo.model;

import java.io.IOException;
import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Restaurant")
@Table(name = "restaurant")
@Data
@NoArgsConstructor
public class Restaurant implements Serializable {
	
	private static final long serialVersionUID = -7991880517032661710L;

    @Id
    @Column(name = "id")
	private String id;

	@Column(name = "address")
	private String address;

	@Column(name = "address_line_2")
	private String altAddress;

	@Column(name = "name", length = 100)
	private String name;

	@Column(name = "outcode", length = 10)
	private String outcode;

	@Column(name = "postcode", length = 10)
	private String postcode;
	
	@Column(name = "rating")
	private float rating;
	
	@Column(name = "type_of_food")
	private String typeOfFood;
	

	/*
	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(this.fileUID).toHashCode();
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof Attachment) {
			Attachment that = (Attachment) other;
			return new EqualsBuilder().append(this.fileUID, that.fileUID).isEquals();
		} else {
			return false;
		}
	}*/
}
