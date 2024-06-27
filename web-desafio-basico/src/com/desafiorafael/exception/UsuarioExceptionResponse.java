package com.desafiorafael.exception;

import javax.swing.text.html.parser.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class UsuarioExceptionResponse implements ExceptionMapper<UsuarioException> {
    @Override
    public Response toResponse(UsuarioException exception) {
        Response.Status status;
        Erro erro = new Erro(0, "", exception.getMessage());

        if (exception instanceof UsuarioAlreadyExistsException) {
            erro.setStatus(Response.Status.CONFLICT.getStatusCode());
            erro.setMenssagem("Usuario já existente");
        } else if (exception instanceof UsuarioNotFoundException) {
            erro.setStatus(Response.Status.NOT_FOUND.getStatusCode());
            erro.setMenssagem("Usuario já existente");
        } else if (exception instanceof InvalidUsuarioDataException) {
            erro.setStatus(Response.Status.BAD_REQUEST.getStatusCode());
            erro.setMenssagem("Usuario já existente");
        } else {
            erro.setStatus(Response.Status.INTERNAL_SERVER_ERROR.getStatusCode());
            erro.setMenssagem("Usuario já existente");
        }

        return Response.status(erro.getStatus()).type(MediaType.APPLICATION_JSON).entity(erro).build();
    }
}
