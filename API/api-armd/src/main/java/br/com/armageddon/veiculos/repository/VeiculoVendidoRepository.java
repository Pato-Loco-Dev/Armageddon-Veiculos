package br.com.armageddon.veiculos.repository;

import br.com.armageddon.veiculos.entity.Veiculo;
import br.com.armageddon.veiculos.entity.VeiculoVendido;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class VeiculoVendidoRepository implements PanacheRepository<VeiculoVendido> {
}
