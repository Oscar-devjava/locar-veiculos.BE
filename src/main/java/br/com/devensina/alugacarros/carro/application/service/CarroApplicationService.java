package br.com.devensina.alugacarros.carro.application.service;

import org.springframework.stereotype.Service;

import br.com.devensina.alugacarros.carro.application.api.CarroIdResponse;
import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import br.com.devensina.alugacarros.carro.application.repository.CarroRepository;
import br.com.devensina.alugacarros.carro.domain.Carro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@Service
public class CarroApplicationService implements CarroService {

	private final CarroRepository carroRepository;

	@Override
	public CarroIdResponse cadastraCarro(CarroRequest carroRequest) {
		log.info("[inicia] CarroApplicationService - cadastraCarro");
		Carro carro = carroRepository.salva(new Carro(carroRequest));
		log.info("[finaliza] CarroApplicationService - cadastraCarro");
		return CarroIdResponse.builder().idCarro(carro.getIdCarro()).build();
	}
}
