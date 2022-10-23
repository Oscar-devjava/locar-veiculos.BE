package br.com.devensina.alugacarros.carro.application.api;

import java.util.UUID;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class CarroIdResponse {
	private UUID idCarro;
}
