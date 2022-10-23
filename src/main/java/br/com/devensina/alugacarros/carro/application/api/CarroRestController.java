package br.com.devensina.alugacarros.carro.application.api;

import org.springframework.web.bind.annotation.RestController;

import br.com.devensina.alugacarros.carro.application.service.CarroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@RestController
public class CarroRestController implements CarroAPI {

	private final CarroService carroService;

	@Override
	public CarroIdResponse postCadastraCarro( CarroRequest carroRequest) {
		log.info("[inicia] CarroRestController - postCadastraCarro");
		CarroIdResponse idCarro = carroService.cadastraCarro(carroRequest);
		log.info("[finaliza] CarroRestController - postCadastraCarro");
		return idCarro;
	}
}
