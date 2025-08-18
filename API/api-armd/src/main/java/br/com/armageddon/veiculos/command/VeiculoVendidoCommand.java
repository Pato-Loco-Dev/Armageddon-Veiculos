package br.com.armageddon.veiculos.command;

import java.math.BigDecimal;

public record VeiculoVendidoCommand(String fabricante, String modelo, String anoFab, String motor, String litragem, BigDecimal valor) {
}
