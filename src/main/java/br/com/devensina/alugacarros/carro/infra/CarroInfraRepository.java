package br.com.devensina.alugacarros.carro.infra;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.devensina.alugacarros.carro.application.repository.CarroRepository;
import br.com.devensina.alugacarros.carro.domain.Carro;
import br.com.devensina.alugacarros.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
@Log4j2
@RequiredArgsConstructor
@Repository
public class CarroInfraRepository implements CarroRepository {

	private final CarroSpringDataJPARepository carroSpringDataJPARepository;

	@Override
	public Carro salva(Carro carro) {
		log.info("[inicia] CarroInfraRepository - salva");
		try {
			carroSpringDataJPARepository.save(carro);
		} catch (DataIntegrityViolationException e) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Existem dados duplicados ou esse veiculo já foi cadastrado",e);
		}
		log.info("[inicia] CarroInfraRepository - salva");
		return carro;
	}
}
