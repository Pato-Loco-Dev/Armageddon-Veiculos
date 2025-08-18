package br.com.armageddon.veiculos.controller.response;

import br.com.armageddon.veiculos.entity.Veiculo;
import br.com.armageddon.veiculos.entity.VeiculoVendido;

public record VeiculoVendidoResponse(VeiculoVendido veiculoVendido) {

    VeiculoVendidoResponse toResponse(VeiculoVendido veiculoVendido) {
        return new VeiculoVendidoResponse(veiculoVendido);
    }


}
