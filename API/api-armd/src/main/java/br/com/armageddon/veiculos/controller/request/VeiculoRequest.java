package br.com.armageddon.veiculos.controller.request;

import br.com.armageddon.veiculos.command.VeiculoCommand;

import java.math.BigDecimal;

public record VeiculoRequest(

        String fabricante,

        String modelo,

        String anoFab,

        String motor,

        String litragem,

        BigDecimal valor,

        String foto

        ) {

    public static VeiculoCommand toCommand(VeiculoRequest request) {
        return new VeiculoCommand(request.fabricante, request.modelo, request.anoFab, request.motor, request.litragem, request.valor, request.foto);
    }

}
