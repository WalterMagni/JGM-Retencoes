package br.com.jgm.model;

import org.hibernate.annotations.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity 
@Data @NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode(exclude = {"codigo", "nome", "ativa"})
public class Empresa {

	private String codigo;
	private String nome;
	private String inscrMunicipal;
	private Boolean ativa = false;
	
}
