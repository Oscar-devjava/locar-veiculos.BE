package br.com.devensina.alugacarros.carro.application.api;

import br.com.devensina.alugacarros.carro.domain.Categoria;
import br.com.devensina.alugacarros.carro.domain.Marca;
import br.com.devensina.alugacarros.carro.domain.TipoCarro;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;


@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class CarroBodyResponse {
    private UUID idCarro;
    private Categoria categoria;
    private TipoCarro tipo;
    private Marca marca;
    private String descricao;
    private String placa;
    private Boolean alugado = false;
    private LocalDate ano;
    private int valorDiaria;
    private LocalDateTime dataHoraDoAluguel;
    private LocalDate dataHoraDaDevolucao;
    private int atraso;
}
