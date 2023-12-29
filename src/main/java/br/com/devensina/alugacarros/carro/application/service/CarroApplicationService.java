package br.com.devensina.alugacarros.carro.application.service;

import br.com.devensina.alugacarros.carro.application.api.CarroBodyResponse;
import org.springframework.stereotype.Service;
import br.com.devensina.alugacarros.carro.application.api.CarroIdResponse;
import br.com.devensina.alugacarros.carro.application.api.CarroRequest;
import br.com.devensina.alugacarros.carro.application.repository.CarroRepository;
import br.com.devensina.alugacarros.carro.domain.Carro;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


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

	@Override
	public List<CarroBodyResponse> obterListaDeCarros() {
		log.info("[inicia] CarroApplicationService - obterListaDeCarros");
		List<Carro> listaCarros = carroRepository.listarCarros();
		List<CarroBodyResponse> listacarrosResponse = new ArrayList<>();
		for(Carro carro : listaCarros){
			listacarrosResponse.add(CarroBodyResponse.builder()
					.idCarro(carro.getIdCarro())
					.categoria(carro.getCategoria())
					.tipo(carro.getTipo())
					.marca(carro.getMarca())
					.descricao(carro.getDescricao())
					.placa(carro.getPlaca())
					.alugado(carro.getAlugado())
					.ano(carro.getAno())
					.valorDiaria(carro.getValorDiaria())
					.build());
		}
		log.info("[finaliza] CarroApplicationService - obterListaDeCarros");
		return listacarrosResponse;
	}

	@Override
	public CarroBodyResponse obterCarroPorId(UUID idCarro) {
		log.info("[inicia] CarroApplicationService - obterCarroPorId");
		Carro carro = carroRepository.buscarCarro(idCarro);
		CarroBodyResponse carroBodyResponse = CarroBodyResponse.builder()
				.idCarro(carro.getIdCarro())
				.categoria(carro.getCategoria())
				.tipo(carro.getTipo())
				.marca(carro.getMarca())
				.descricao(carro.getDescricao())
				.placa(carro.getPlaca())
				.alugado(carro.getAlugado())
				.ano(carro.getAno())
				.valorDiaria(carro.getValorDiaria())
				.build();
		log.info("[finaliza] CarroApplicationService - obterCarroPorId");
		return carroBodyResponse;
	}

	@Override
	public CarroBodyResponse atualizaCarro(UUID idCarro, CarroRequest carroRequest) {
		log.info("[inicia] CarroApplicationService - atualizaCarro");
		Carro carro = carroRepository.atualizarCarro(idCarro, new Carro(carroRequest));
		CarroBodyResponse carroBodyResponse = CarroBodyResponse.builder()
				.idCarro(carro.getIdCarro())
				.categoria(carro.getCategoria())
				.tipo(carro.getTipo())
				.marca(carro.getMarca())
				.descricao(carro.getDescricao())
				.placa(carro.getPlaca())
				.alugado(carro.getAlugado())
				.ano(carro.getAno())
				.valorDiaria(carro.getValorDiaria())
				.build();
		log.info("[finaliza] CarroApplicationService - atualizaCarro");
		return carroBodyResponse;
	}

	@Override
	public void removerCarro(UUID idCarro) {
		log.info("[inicia] CarroApplicationService - removerCarro");
		carroRepository.deletarCarro(idCarro);
		log.info("[finaliza] CarroApplicationService - removerCarro");
	}

	@Override
	public CarroBodyResponse atualizaCarroAlugado(UUID idCarro, String localDate) {
		log.info("[inicia] CarroApplicationService - atualizaCarroAlugado");
		Carro carro = carroRepository.atualizarCarroAlugado(idCarro, localDate);
		CarroBodyResponse carroBodyResponse = CarroBodyResponse.builder()
				.idCarro(carro.getIdCarro())
				.categoria(carro.getCategoria())
				.tipo(carro.getTipo())
				.marca(carro.getMarca())
				.descricao(carro.getDescricao())
				.placa(carro.getPlaca())
				.alugado(carro.getAlugado())
				.ano(carro.getAno())
				.valorDiaria(carro.getValorDiaria())
				.dataHoraDoAluguel(carro.getDataHoraDoAluguel())
				.dataHoraDaDevolucao(carro.getDataHoraDaDevolucao())
				.build();
		log.info("[finaliza] CarroApplicationService - atualizaCarroAlugado");
		return carroBodyResponse;
	}

	@Override
	public CarroBodyResponse atualizaCarroDevolvido(UUID idCarro) {
		log.info("[inicia] CarroApplicationService - atualizaCarroDevolvido");
		Carro carro = carroRepository.atualizarCarroDevolver(idCarro);
		CarroBodyResponse carroBodyResponse = CarroBodyResponse.builder()
				.idCarro(carro.getIdCarro())
				.categoria(carro.getCategoria())
				.tipo(carro.getTipo())
				.marca(carro.getMarca())
				.descricao(carro.getDescricao())
				.placa(carro.getPlaca())
				.alugado(carro.getAlugado())
				.ano(carro.getAno())
				.valorDiaria(carro.getValorDiaria())
				.dataHoraDoAluguel(carro.getDataHoraDoAluguel())
				.dataHoraDaDevolucao(carro.getDataHoraDaDevolucao())
				.atraso(carro.getAtraso())
				.build();
		log.info("[inicia] CarroApplicationService - atualizaCarroDevolvido");
		return carroBodyResponse;
	}
}
