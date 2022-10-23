package br.com.devensina.alugacarros.carro.application.api;

import java.time.LocalDate;

import br.com.devensina.alugacarros.carro.domain.Categoria;
import br.com.devensina.alugacarros.carro.domain.Marca;
import br.com.devensina.alugacarros.carro.domain.TipoCarro;
import lombok.Value;

@Value
public class CarroRequest {
	private Categoria categoria;
	private TipoCarro  tipo;
	private Marca marca;
	private String descricao;
	private String placa;
	private Boolean alugado = false;
	private LocalDate ano;
	private int valorDiaria;
	
}
