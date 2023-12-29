package br.com.devensina.alugacarros.carro.application.api;


import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.UUID;

@RequestMapping("/adm/carro")
public interface CarroAPI {
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	CarroIdResponse postCadastraCarro(@Valid @RequestBody CarroRequest carroRequest);


	@GetMapping("/listar-carros")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<List<CarroBodyResponse>> getListaTodosCarros();


	@GetMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<CarroBodyResponse> getBuscaCarroPorId(@PathVariable("id") UUID idCarro);


	@PutMapping("/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<CarroBodyResponse> updateAtualizaCarro(@PathVariable("id") UUID idCarro, @RequestBody CarroRequest carroRequest);


	@DeleteMapping("/{id}")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	ResponseEntity<Void> deleteRemoveCarro(@PathVariable("id") UUID idCarro);


	@PatchMapping("/aluga/{id}/{data-devolver}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<CarroBodyResponse> patchAtualizaCarroAlugado(@PathVariable("id") UUID idCarro,@PathVariable("data-devolver") String localDate);


	@PatchMapping("/devolve/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<CarroBodyResponse> patchAtualizaCarroDevolver(@PathVariable("id") UUID idCarro);

}





