package br.com.devensina.alugacarros.carro.infra;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import br.com.devensina.alugacarros.carro.application.repository.CarroRepository;
import br.com.devensina.alugacarros.carro.domain.Carro;
import br.com.devensina.alugacarros.handler.APIException;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Log4j2
@RequiredArgsConstructor
@Repository
public class CarroInfraRepository implements CarroRepository {

	private final CarroSpringDataJPARepository carroSpringDataJPARepository;

	@Autowired
	private ConversorData conversorData;

	@Override
	public Carro salva(Carro carro) {
		log.info("[inicia] CarroInfraRepository - salva");
		try {
			carroSpringDataJPARepository.save(carro);
		} catch (DataIntegrityViolationException e) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Existem dados duplicados ou esse veiculo já foi cadastrado",e);
		}
		log.info("[finaliza] CarroInfraRepository - salva");
		return carro;
	}

	@Override
	public List<Carro> listarCarros() {
		log.info("[inicia] CarroInfraRepository - listarCarros");
		List<Carro> carros = carroSpringDataJPARepository.findAll();
		if (carros.isEmpty()) {
			throw APIException.build(HttpStatus.NOT_FOUND,"Não há carros cadastrados no sistema.");
		}
		log.info("[finaliza] CarroInfraRepository - listarCarros");
		return carros;
		}

	@Override
	public Carro buscarCarro(UUID idCarro) {
		log.info("[inicia] CarroInfraRepository - buscarCarro");
		Optional<Carro> carro;
		try {
			carro = carroSpringDataJPARepository.findById(idCarro);
			carro.orElseThrow(()-> new NoSuchElementException());
		} catch (NoSuchElementException e) {
			throw APIException.build(HttpStatus.NOT_FOUND, "Carro não encontrado no banco de dados, ID " + idCarro, e);
		}
		log.info("[finaliza] CarroInfraRepository - buscarCarro");
		return carro.get();
	}

	@Override
	public Carro atualizarCarro(UUID idCarro, Carro updatecarro) {
		Optional<Carro> carro;
		try{
			carro = carroSpringDataJPARepository.findById(idCarro);
			carro.orElseThrow(()-> new NoSuchElementException());
			carro.get().atualizarCarro(updatecarro);
		}catch (NoSuchElementException  e){
			throw APIException.build(HttpStatus.NOT_FOUND, "Carro não encontrado no banco de dados, ID " + idCarro, e);
		}
		return carroSpringDataJPARepository.save(carro.get());
	}

	@Override
	public void deletarCarro(UUID idCarro) {
		log.info("[inicia] CarroInfraRepository - deletarCarro");
		try {
			Optional<Carro> carro = carroSpringDataJPARepository.findById(idCarro);
			carro.orElseThrow(() -> new NoSuchElementException());
			carroSpringDataJPARepository.deleteById(idCarro);
		}catch (NoSuchElementException  e){
			throw APIException.build(HttpStatus.NOT_FOUND, "Carro não encontrado no banco de dados, ID " + idCarro, e);
		}
		log.info("[finaliza] CarroInfraRepository - deletarCarro");
	}

	@Override
	public Carro atualizarCarroAlugado(UUID idCarro, String localDate) {
		log.info("[inicia] CarroInfraRepository - atualizarCarroAlugado");
		Optional<Carro> carro;
		LocalDate date;
		try {
			date = conversorData.converterData(localDate);
			carro = carroSpringDataJPARepository.findById(idCarro);
			carro.orElseThrow(() -> new NoSuchElementException());
			if (carro.get().getAlugado() == true) {
				throw new IllegalArgumentException();
			}
			carro.get().alugarCarro(date);
		}catch (NoSuchElementException e){
			throw APIException.build(HttpStatus.NOT_FOUND, "Carro não encontrado no banco de dados, ID " + idCarro, e);
		}catch (IllegalArgumentException e){
			throw APIException.build(HttpStatus.UNPROCESSABLE_ENTITY,"Carro encontra-se alugado.", e);
		}catch (DateTimeParseException e){
			throw APIException.build(HttpStatus.UNPROCESSABLE_ENTITY,"Formato de data inválido, digite no formato dd/MM/yyyy", e);
		}
		log.info("[finaliza] CarroInfraRepository - atualizarCarroAlugado");
		return carroSpringDataJPARepository.save(carro.get());
	}


	public Carro atualizarCarroDevolver(UUID idCarro){
		log.info("[inicia] CarroInfraRepository - atualizarCarroDevolver");
		Optional<Carro> carro;
		try{
			carro = carroSpringDataJPARepository.findById(idCarro);
			carro.orElseThrow(() -> new NoSuchElementException());
			carro.get().devolverCarro();
		}catch (NoSuchElementException e){
			throw APIException.build(HttpStatus.NOT_FOUND, "Carro não encontrado no banco de dados, ID " + idCarro, e);
		}
		log.info("[finaliza] CarroInfraRepository - atualizarCarroDevolver");
		return carro.get();
	}
}

