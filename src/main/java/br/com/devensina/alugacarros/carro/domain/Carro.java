package br.com.devensina.alugacarros.carro.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Entity
public class Carro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(columnDefinition = "UUID", name = "idCarro", unique = true , nullable = false, updatable = false)
	private UUID idCarro;
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	@Enumerated(EnumType.STRING)
	private TipoCarro  tipo;
	@Enumerated(EnumType.STRING)
	private Marca marca;
	private String descricao;
	private String placa;
	private Boolean alugado = false;
	private LocalDate ano;
	private LocalDateTime dataHoraDoAluguel;
	private LocalDate dataHoraDaDevolucao;
	private int valorDiaria;
	private int atraso;
	
	public Carro(CarroRequest carroRequest) {
		this.categoria = carroRequest.getCategoria();
		this.tipo = carroRequest.getTipo();
		this.marca = carroRequest.getMarca();
		this.descricao = carroRequest.getDescricao();
		this.placa = carroRequest.getPlaca();
		this.ano = carroRequest.getAno();
		this.valorDiaria = carroRequest.getValorDiaria();
	}
}
