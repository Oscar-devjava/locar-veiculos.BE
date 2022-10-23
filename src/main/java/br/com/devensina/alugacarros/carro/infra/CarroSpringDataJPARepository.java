package br.com.devensina.alugacarros.carro.infra;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.devensina.alugacarros.carro.domain.Carro;

public interface CarroSpringDataJPARepository extends JpaRepository<Carro, UUID> {

}
