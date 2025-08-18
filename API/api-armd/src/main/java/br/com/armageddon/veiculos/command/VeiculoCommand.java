package br.com.armageddon.veiculos.command;

import java.math.BigDecimal;

public record VeiculoCommand(String fabricante, String modelo, String anoFab, String motor, String litragem, BigDecimal valor, String foto) {
}
