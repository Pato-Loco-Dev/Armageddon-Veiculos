package br.com.armageddon.veiculos.controller.request;

import br.com.armageddon.veiculos.command.VeiculoCommand;
import br.com.armageddon.veiculos.command.VeiculoVendidoCommand;
import br.com.armageddon.veiculos.entity.VeiculoVendido;

import java.math.BigDecimal;

public record VeiculoVendidoRequest(

        String fabricante,

        String modelo,

        String anoFab,

        String motor,

        String litragem,

        BigDecimal valor

) {

    public static VeiculoVendidoCommand toCommand(VeiculoVendidoRequest request) {
        return new VeiculoVendidoCommand(request.fabricante, request.modelo, request.anoFab, request.motor, request.litragem, request.valor);
    }

}
