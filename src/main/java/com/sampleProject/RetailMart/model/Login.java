package com.sampleProject.RetailMart.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Entity
@Data
@Table(name="login")
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class Login {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
	private String userName;
    @Column(nullable = false,length = 20)
	private String password;
	private Long mobileNo;
}
