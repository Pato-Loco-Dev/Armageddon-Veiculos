package br.com.armageddon.veiculos.controller.response;

import br.com.armageddon.veiculos.entity.Veiculo;

public record VeiculoResponse(Veiculo veiculo) {

        VeiculoResponse toResponse(Veiculo veiculo) {
            return new VeiculoResponse(veiculo);
        }

}
