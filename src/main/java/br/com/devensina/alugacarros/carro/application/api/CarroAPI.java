package br.com.devensina.alugacarros.carro.application.api;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

@RequestMapping("/adm/carro")
public interface CarroAPI {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	CarroIdResponse postCadastraCarro(@Valid @RequestBody CarroRequest carroRequest);
}
