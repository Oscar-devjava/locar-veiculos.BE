package br.com.devensina.alugacarros.carro.application.service;

import br.com.devensina.alugacarros.carro.application.api.CarroBodyResponse;
import br.com.devensina.alugacarros.carro.application.api.CarroIdResponse;
import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import br.com.devensina.alugacarros.carro.domain.Carro;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

public interface CarroService {
	CarroIdResponse cadastraCarro(CarroRequest carroRequest);

	List<CarroBodyResponse> obterListaDeCarros();

	CarroBodyResponse obterCarroPorId(UUID idCarro);

	CarroBodyResponse atualizaCarro (UUID idCarro, CarroRequest carroRequest);

	void removerCarro(UUID idCarro);

	CarroBodyResponse atualizaCarroAlugado(UUID idCarro, String localDate);

	CarroBodyResponse atualizaCarroDevolvido(UUID idCarro);

}

