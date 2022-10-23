package br.com.devensina.alugacarros.carro.application.repository;

import br.com.devensina.alugacarros.carro.domain.Carro;

public interface CarroRepository {
	Carro salva(Carro carro);
}
