package br.com.allan.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity(name="dog")
public class Dog {
	
	@Id
    @GeneratedValue
    private Long id;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(name="breedId")
	private Breed breed;
	
	public Dog() {
		super();
	}
	
	public Dog(String name, Breed breed) {
		super();
		this.name = name;
		this.breed = breed;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}
	
	
	
}
