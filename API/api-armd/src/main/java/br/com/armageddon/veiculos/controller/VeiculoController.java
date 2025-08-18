package br.com.armageddon.veiculos.controller;

import br.com.armageddon.veiculos.command.VeiculoCommand;
import br.com.armageddon.veiculos.controller.request.VeiculoRequest;
import br.com.armageddon.veiculos.controller.response.VeiculoResponse;
import br.com.armageddon.veiculos.service.VeiculoService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/veiculos/veiculos-a-venda")
public class VeiculoController {

    @Inject
    VeiculoService veiculoService;

    @GET
    public Response retrieveVeiculo(){

        List<VeiculoResponse> responses = veiculoService.findAll().stream().map(veiculo -> new VeiculoResponse(veiculo)).toList();
        return Response.ok(responses).build();
    }

    @POST
    public void addVeiculo(@RequestBody VeiculoRequest request) {

        VeiculoCommand veiculoCommand = VeiculoRequest.toCommand(request);

        veiculoService.addVeiculo(veiculoCommand);

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculoPeloCodigo(
            @PathParam("id") Long id) {
        VeiculoResponse responses = new VeiculoResponse(veiculoService.listarVeiculoPorCodigo(id));

        return Response.ok(responses).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(
            @PathParam("id") Long id,
            @RequestBody VeiculoCommand veiculoCommand) {

        VeiculoResponse responses = new VeiculoResponse(veiculoService.atualizar(id, veiculoCommand));

        return Response.ok(responses).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("id") Long id) {

        VeiculoResponse responses = new VeiculoResponse(veiculoService.listarVeiculoPorCodigo(id));
        veiculoService.deletarVeiculo(id);

        return Response.ok(responses).build();
    }


}
