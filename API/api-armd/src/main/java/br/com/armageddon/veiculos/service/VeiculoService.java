package br.com.armageddon.veiculos.service;

import br.com.armageddon.veiculos.command.VeiculoCommand;
import br.com.armageddon.veiculos.entity.Veiculo;
import br.com.armageddon.veiculos.repository.VeiculoRepository;


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
public class VeiculoService {

    @Inject
    VeiculoRepository veiculoRepository;

    /*LISTAR TODOS*/

    public List<Veiculo> findAll() {
        return veiculoRepository.findAll().list();
    }


    /*LISTAR POR ID*/
    public Veiculo listarVeiculoPorCodigo(Long id) {

        String jpql = """
                    SELECT c FROM Veiculo c WHERE c.id = :id
                """;

        return getEntityManager()
                .createQuery(jpql, Veiculo.class)
                .setParameter("id", id)
                .getResultStream()
                .findAny()
                .orElseThrow();
    }

    /*ADICIONAR VEICULO*/

    @Transactional
    public void addVeiculo(VeiculoCommand veiculoCommand) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Veiculo veiculo = new Veiculo();
        Set<ConstraintViolation<Veiculo>> violations = validator.validate(veiculo);

        veiculo = new Veiculo();
        veiculo.setFabricante(veiculoCommand.fabricante());
        veiculo.setModelo(veiculoCommand.modelo());
        veiculo.setAnoFab(veiculoCommand.anoFab());
        veiculo.setMotor(veiculoCommand.motor());
        veiculo.setLitragem(veiculoCommand.litragem());
        veiculo.setValor(veiculoCommand.valor());
        veiculo.setFoto(veiculoCommand.foto());

        persist(veiculo);

    }

    @Transactional
    public Veiculo atualizar(Long id, VeiculoCommand veiculoCommand) {

        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Veiculo veiculo = new Veiculo();
        Set<ConstraintViolation<Veiculo>> violations = validator.validate(veiculo);

        Veiculo veiculoEncontrado = listarVeiculoPorCodigo(id);
        veiculoEncontrado.setFabricante(veiculoCommand.fabricante());
        veiculoEncontrado.setModelo(veiculoCommand.modelo());
        veiculoEncontrado.setAnoFab(veiculoCommand.anoFab());
        veiculoEncontrado.setMotor(veiculoCommand.motor());
        veiculoEncontrado.setLitragem(veiculoCommand.litragem());
        veiculoEncontrado.setValor(veiculoCommand.valor());
        veiculoEncontrado.setFoto(veiculoCommand.foto());
        return veiculoEncontrado;

    }

    @Transactional
    public Response deletarVeiculo(@PathParam("id") Long id) {
        Veiculo veiculo = listarVeiculoPorCodigo(id);
        if (veiculo == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        veiculoRepository.delete(veiculo);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
