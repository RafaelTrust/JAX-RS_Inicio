package com.desafiorafael.resources;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
//import javax.ws.rs.Priorities;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.desafiorafael.dto.UsuarioDTO;
import com.desafiorafael.entity.Usuario;
import com.desafiorafael.exception.UsuarioException;
import com.desafiorafael.exception.UsuarioNotFoundException;
import com.desafiorafael.service.UsuarioService;

@Path("/usuario")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RestUsuario {

    UsuarioService usuarioService = new UsuarioService();

    @GET
    // @Priority(Priorities.AUTHENTICATION)
    @Produces(MediaType.APPLICATION_JSON)
    public List<UsuarioDTO> getAllUsuarios() throws UsuarioException {
        System.out.println("Passou RestUsuario...");
        List<Usuario> usuarios = usuarioService.getAllUsuarios();
        if (usuarios == null) {
            System.out.println("Deu errado");
            throw new UsuarioNotFoundException("Lista de usuarios não encontrada");
        }
        System.out.println("Usuario 1: \n Login: " + usuarios.get(0).getLogin()
                + "\n Senha: " + usuarios.get(0).getSenha()
                + "\n Email: " + usuarios.get(0).getEmail()
                + "\n Id: " + usuarios.get(0).getId());
        return new ArrayList<>(usuarios.stream().map(this::convertToDTO).collect(Collectors.toList()));
    }

    @GET
    @Path("/{id}")
    // @Priority(Priorities.AUTHENTICATION)
    @Produces(MediaType.APPLICATION_JSON)
    public UsuarioDTO getUsuario(@PathParam("id") Long id) throws UsuarioException {
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario == null) {
            throw new UsuarioNotFoundException("Usuario com o id " + id + " não foi encontrado");
        }
        return convertToDTO(usuario);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUsuario(@Valid UsuarioDTO usuarioDTO) throws UsuarioException {
        usuarioDTO.validar();
        Usuario usuario = convertoToEntity(usuarioDTO);
        usuarioService.createUsuario(usuario);
        return Response.status(Response.Status.CREATED)
                .entity(convertToDTO(usuario)).build();
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    // @Priority(Priorities.AUTHENTICATION)
    public Response updateUsuario(@PathParam("id") Long id, @Valid UsuarioDTO usuarioDTO) throws UsuarioException {
        Usuario usuario = usuarioService.getUsuario(id);
        if (usuario == null) {
            return Response.status(Response.Status.NOT_FOUND).entity("Usuario não encontrado com o id " + id).build();
        }
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        usuarioService.updateUsuario(usuario);
        return Response.ok(usuarioDTO).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    // @Priority(Priorities.AUTHENTICATION)
    public Response deleteUsuario(@PathParam("id") Long id) throws UsuarioException {
        usuarioService.deleteUsuario(id);
        return Response.noContent().build();
    }

    public UsuarioDTO convertToDTO(Usuario usuario) {
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(usuario.getId());
        usuarioDTO.setLogin(usuario.getLogin());
        usuarioDTO.setSenha(usuario.getSenha());
        usuarioDTO.setEmail(usuario.getEmail());
        return usuarioDTO;
    }

    public Usuario convertoToEntity(UsuarioDTO usuarioDTO) {
        Usuario usuario = new Usuario();
        if (usuarioDTO.getId() != null) {
            usuario.setId(usuarioDTO.getId());
        }
        usuario.setLogin(usuarioDTO.getLogin());
        usuario.setSenha(usuarioDTO.getSenha());
        usuario.setEmail(usuarioDTO.getEmail());
        return usuario;
    }
}
