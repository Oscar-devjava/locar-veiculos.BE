package br.com.devensina.alugacarros.carro.infra;

import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class ConversorData {
    private static final DateTimeFormatter FORMATO_ENTRADA = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    private static final DateTimeFormatter FORMATO_SAIDA = DateTimeFormatter.ofPattern("yyyy,MM,dd");

    private LocalDate converterStringParaLocalDate(String dataString) {
        LocalDate data = LocalDate.parse(dataString, FORMATO_ENTRADA);
        return data;
    }

    private LocalDate formatarLocalDatePadrao(LocalDate data) {
        String dataFormatada = data.format(FORMATO_SAIDA);
        return LocalDate.parse(dataFormatada, FORMATO_SAIDA);
    }

    public LocalDate converterData(String data){
        LocalDate resultado = converterStringParaLocalDate(data);
        LocalDate resultadoFormatado = formatarLocalDatePadrao(resultado);
        return resultadoFormatado;
    }
}
