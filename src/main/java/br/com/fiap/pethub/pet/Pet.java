package br.com.fiap.pethub.pet;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Entity
@Data
public class Pet {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;

	@NotBlank
	String nome;

	@Size(min = 10)
	String descricao;

	@NotBlank
	String raca;

	@Min(0) @Max(150)
	Integer idade;
}
