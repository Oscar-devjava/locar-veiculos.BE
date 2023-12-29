package br.com.devensina.alugacarros.carro.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;
import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import javax.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

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

	public void atualizarCarro(Carro carro){
		this.categoria = carro.getCategoria();
		this.tipo = carro.getTipo();
		this.marca = carro.getMarca();
		this.descricao = carro.getDescricao();
		this.placa = carro.getPlaca();
		this.ano = carro.getAno();
		this.valorDiaria = carro.getValorDiaria();
	}

	public void  alugarCarro(LocalDate dataHoraDevolucao){
		if(!this.alugado) {
			this.alugado = true;
			this.dataHoraDoAluguel = LocalDateTime.now();
			this.dataHoraDaDevolucao = dataHoraDevolucao;
		}
	}

	public void devolverCarro(){
		LocalDate dataDevolucaoAtual = LocalDate.now();
		if(this.dataHoraDaDevolucao != null && this.dataHoraDaDevolucao.equals(dataDevolucaoAtual)){
			this.alugado = false;
			this.dataHoraDaDevolucao = null;
			this.dataHoraDoAluguel = null ;
			this.atraso = 0;
		}
		if(this.dataHoraDaDevolucao != null && !this.dataHoraDaDevolucao.equals(dataDevolucaoAtual)){
			long atraso = dataDevolucaoAtual.toEpochDay() - this.dataHoraDaDevolucao.toEpochDay();
			this.atraso = (int) atraso;
			this.alugado = false;
			this.dataHoraDaDevolucao = null;
			this.dataHoraDoAluguel = null ;
		}
	}
}
