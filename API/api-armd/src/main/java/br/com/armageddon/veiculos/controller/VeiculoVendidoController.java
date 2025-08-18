package br.com.armageddon.veiculos.controller;

import br.com.armageddon.veiculos.command.VeiculoVendidoCommand;
import br.com.armageddon.veiculos.controller.request.VeiculoVendidoRequest;
import br.com.armageddon.veiculos.controller.response.VeiculoVendidoResponse;
import br.com.armageddon.veiculos.service.VeiculoVendidoService;
import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/veiculos/veiculos-vendidos")
public class VeiculoVendidoController {


    @Inject
    VeiculoVendidoService veiculoVendidoService;

    @GET
    public Response retrieveVeiculoVendido(){

        List<VeiculoVendidoResponse> responses = veiculoVendidoService.findAll().stream().map(veiculoVendido -> new VeiculoVendidoResponse(veiculoVendido)).toList();

        return Response.ok(responses).build();
    }

    @POST
    public void addVeiculoVendido(@RequestBody VeiculoVendidoRequest request) {

        VeiculoVendidoCommand veiculoVendidoCommand = VeiculoVendidoRequest.toCommand(request);

        veiculoVendidoService.addVeiculoVendido(veiculoVendidoCommand);

    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listarVeiculoVendidoPeloCodigo(
            @PathParam("id") Long id) {
        VeiculoVendidoResponse responses = new VeiculoVendidoResponse(veiculoVendidoService.listarVeiculoVendidoPorCodigo(id));

        return Response.ok(responses).build();
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response atualizar(
            @PathParam("id") Long id,
            @RequestBody VeiculoVendidoCommand veiculoVendidoCommand) {

        VeiculoVendidoResponse responses = new VeiculoVendidoResponse(veiculoVendidoService.atualizar(id, veiculoVendidoCommand));

        return Response.ok(responses).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response delete(
            @PathParam("id") Long id) {

        VeiculoVendidoResponse responses = new VeiculoVendidoResponse(veiculoVendidoService.listarVeiculoVendidoPorCodigo(id));
        veiculoVendidoService.deletarVeiculoVendido(id);

        return Response.ok(responses).build();
    }



}
