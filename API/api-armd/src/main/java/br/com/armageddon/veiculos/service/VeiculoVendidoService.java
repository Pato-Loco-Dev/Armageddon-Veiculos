package br.com.armageddon.veiculos.service;

import br.com.armageddon.veiculos.command.VeiculoCommand;
import br.com.armageddon.veiculos.command.VeiculoVendidoCommand;
import br.com.armageddon.veiculos.entity.Veiculo;
import br.com.armageddon.veiculos.entity.VeiculoVendido;
import br.com.armageddon.veiculos.repository.VeiculoRepository;
import br.com.armageddon.veiculos.repository.VeiculoVendidoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Set;

import static io.quarkus.hibernate.orm.panache.Panache.getEntityManager;
import static io.quarkus.hibernate.orm.panache.PanacheEntityBase.persist;
@ApplicationScoped
public class VeiculoVendidoService {

    @Inject
    VeiculoVendidoRepository veiculoVendidoRepository;

    /*LISTAR TODOS*/

    public List<VeiculoVendido> findAll() {
        return veiculoVendidoRepository.findAll().list();
    }


    /*LISTAR POR ID*/
    public VeiculoVendido listarVeiculoVendidoPorCodigo(Long id) {

        String jpql = """
                    SELECT c FROM VeiculoVendido c WHERE c.id = :id
                """;

        return getEntityManager()
                .createQuery(jpql, VeiculoVendido.class)
                .setParameter("id", id)
                .getResultStream()
                .findAny()
                .orElseThrow();
    }

    /*ADICIONAR VEICULO*/

    @Transactional
    public void addVeiculoVendido(VeiculoVendidoCommand veiculoVendidoCommand) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        VeiculoVendido veiculoVendido = new VeiculoVendido();
        Set<ConstraintViolation<VeiculoVendido>> violations = validator.validate(veiculoVendido);

        veiculoVendido = new VeiculoVendido();
        veiculoVendido.setFabricante(veiculoVendidoCommand.fabricante());
        veiculoVendido.setModelo(veiculoVendidoCommand.modelo());
        veiculoVendido.setAnoFab(veiculoVendidoCommand.anoFab());
        veiculoVendido.setMotor(veiculoVendidoCommand.motor());
        veiculoVendido.setLitragem(veiculoVendidoCommand.litragem());
        veiculoVendido.setValor(veiculoVendidoCommand.valor());

        persist(veiculoVendido);

    }

    @Transactional
    public VeiculoVendido atualizar(Long id, VeiculoVendidoCommand veiculoVendidoCommand) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        VeiculoVendido veiculoVendido = new VeiculoVendido();
        Set<ConstraintViolation<VeiculoVendido>> violations = validator.validate(veiculoVendido);

        VeiculoVendido veiculoVendidoEncontrado = listarVeiculoVendidoPorCodigo(id);
        veiculoVendidoEncontrado.setFabricante(veiculoVendidoCommand.fabricante());
        veiculoVendidoEncontrado.setModelo(veiculoVendidoCommand.modelo());
        veiculoVendidoEncontrado.setAnoFab(veiculoVendidoCommand.anoFab());
        veiculoVendidoEncontrado.setMotor(veiculoVendidoCommand.motor());
        veiculoVendidoEncontrado.setLitragem(veiculoVendidoCommand.litragem());
        veiculoVendidoEncontrado.setValor(veiculoVendidoCommand.valor());
        return veiculoVendidoEncontrado;

    }

    @Transactional
    public Response deletarVeiculoVendido(@PathParam("id") Long id) {
        VeiculoVendido veiculoVendido = listarVeiculoVendidoPorCodigo(id);
        if (veiculoVendido == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        veiculoVendidoRepository.delete(veiculoVendido);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
