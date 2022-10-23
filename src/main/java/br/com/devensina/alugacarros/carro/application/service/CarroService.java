package br.com.devensina.alugacarros.carro.application.service;

import br.com.devensina.alugacarros.carro.application.api.CarroIdResponse;
import br.com.devensina.alugacarros.carro.application.api.CarroRequest;

public interface CarroService {
	CarroIdResponse cadastraCarro(CarroRequest carroRequest);
}
