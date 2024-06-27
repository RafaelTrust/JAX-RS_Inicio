package com.desafiorafael.service;

import java.util.List;

import javax.inject.Inject;

import org.glassfish.jersey.process.internal.RequestScoped;

import com.desafiorafael.entity.Usuario;
import com.desafiorafael.exception.UsuarioAlreadyExistsException;
import com.desafiorafael.exception.UsuarioException;
import com.desafiorafael.repository.UsuarioRepositorio;

public class UsuarioService {

    UsuarioRepositorio usuarioRepo = new UsuarioRepositorio();

    public void createUsuario(Usuario usuario) throws UsuarioException {
        Usuario loginUsuario = this.getByLoginUsuario(usuario.getLogin());
        if (loginUsuario == null) {
            // usuario.setSenha(senhaUtil.hashSenha(usuario.getSenha()));
            usuarioRepo.save(usuario);
        } else {
            throw new UsuarioAlreadyExistsException(
                    "Usuario com login " + usuario.getLogin() + " ja é existente no banco de dados");
        }
    }

    public Usuario getByLoginUsuario(String login) {
        return usuarioRepo.findByLogin(login);
    }

    public Usuario getUsuario(Long id) {
        return usuarioRepo.find(id);
    }

    public List<Usuario> getAllUsuarios() {
        System.out.println("Passou UsuarioService...");
        return usuarioRepo.findAll();
    }

    public void updateUsuario(Usuario usuario) throws UsuarioException {
        usuarioRepo.update(usuario);
    }

    public void deleteUsuario(Long id) throws UsuarioException {
        usuarioRepo.delete(id);
    }
}
