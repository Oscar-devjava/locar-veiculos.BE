package br.com.devensina.alugacarros.carro.application.api;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import br.com.devensina.alugacarros.carro.application.service.CarroService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@RestController
public class CarroRestController implements CarroAPI {

	private final CarroService carroService;

	@Override
	public CarroIdResponse postCadastraCarro(CarroRequest carroRequest) {
		log.info("[inicia] CarroRestController - postCadastraCarro");
		CarroIdResponse idCarro = carroService.cadastraCarro(carroRequest);
		log.info("[finaliza] CarroRestController - postCadastraCarro");
		return idCarro;
	}

	@Override
	public ResponseEntity<List<CarroBodyResponse>> getListaTodosCarros() {
		log.info("[inicia] CarroRestController - getListaTodosCarros");
		List<CarroBodyResponse> carros = carroService.obterListaDeCarros();
		log.info("[finaliza] CarroRestController - getListaTodosCarros");
		return ResponseEntity.ok(carros);
	}

	@Override
	public ResponseEntity<CarroBodyResponse> getBuscaCarroPorId(UUID idCarro) {
		log.info("[inicia] CarroRestController - getObteCarroPorId");
		CarroBodyResponse carro = carroService.obterCarroPorId(idCarro);
		log.info("[finaliza] CarroRestController - getObteCarroPorId");
		return ResponseEntity.ok(carro);
	}

	@Override
	public ResponseEntity<CarroBodyResponse> updateAtualizaCarro(UUID idCarro, CarroRequest carroRequest) {
		log.info("[inicia] CarroRestController - updateAtualizaCarro");
		CarroBodyResponse carro = carroService.atualizaCarro(idCarro, carroRequest);
		log.info("[finaliza] CarroRestController - updateAtualizaCarro");
		return ResponseEntity.ok(carro);
	}

	@Override
	public ResponseEntity<Void> deleteRemoveCarro(UUID idCarro) {
		log.info("[inicia] CarroRestController - deleteRemoveCarro");
		carroService.removerCarro(idCarro);
		log.info("[finaliza] CarroRestController - deleteRemoveCarro");
		return ResponseEntity.noContent().build();
	}

	@Override
	public ResponseEntity<CarroBodyResponse> patchAtualizaCarroAlugado(UUID idCarro, String localDate) {
		log.info("[inicia] CarroRestController - patchAtualizaCarroAlugado");
		CarroBodyResponse carroBodyResponse = carroService.atualizaCarroAlugado(idCarro, localDate);
		log.info("[finaliza] CarroRestController - patchAtualizaCarroAlugado");
		return ResponseEntity.ok(carroBodyResponse);
	}

	@Override
	public ResponseEntity<CarroBodyResponse> patchAtualizaCarroDevolver(UUID idCarro) {
		log.info("[inicia] CarroRestController - patchAtualizaCarroAlugado");
		CarroBodyResponse carroBodyResponse = carroService.atualizaCarroDevolvido(idCarro);
		log.info("[finaliza] CarroRestController - patchAtualizaCarroAlugado");
		return ResponseEntity.ok(carroBodyResponse);
	}
}