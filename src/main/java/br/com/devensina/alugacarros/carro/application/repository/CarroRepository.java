package br.com.devensina.alugacarros.carro.application.repository;

import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import br.com.devensina.alugacarros.carro.domain.Carro;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarroRepository {
	Carro salva(Carro carro);

	List<Carro> listarCarros();

	Carro buscarCarro(UUID idCarro);

	Carro atualizarCarro(UUID idCarro, Carro carro);

	void deletarCarro(UUID idCarro);

	Carro atualizarCarroAlugado(UUID idCarro, String localDate);

	Carro atualizarCarroDevolver(UUID idCarro);





}
