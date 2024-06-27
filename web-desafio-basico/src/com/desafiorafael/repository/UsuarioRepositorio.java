package com.desafiorafael.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// import javax.persistence.EntityManager;
// import javax.persistence.PersistenceContext;

import org.jvnet.hk2.annotations.Service;

import com.desafiorafael.entity.Usuario;
import com.desafiorafael.exception.UsuarioException;
import com.desafiorafael.exception.UsuarioNotFoundException;

@Service
public class UsuarioRepositorio {
    // @PersistenceContext
    // private EntityManager em;

    // public List<Usuario> findAll() {
    // System.out.println("passei aqui...");
    // return em.createQuery("SELECT * FROM \"Usuario\"",
    // Usuario.class).getResultList();
    // }
    private static final List<Usuario> banco = new ArrayList<>();
    static {
        banco.add(new Usuario(0L, "Rafael Lima", "senha 123", "rafaelimaferreira@gmail.com"));
        banco.add(new Usuario(1L, "Gustavo Gomes", "123teste", "gustavo_go@teste.com"));
        banco.add(new Usuario(2L, "Larissa Duarte", "teste123", "lari_duarte@teste.com"));
        banco.add(new Usuario(3L, "Luana Cavalcante", "123senha", "lua_cavalcante@teste.com"));
        banco.add(new Usuario(4L, "Gabriela Souza", "teste 123", "gabi_souza@teste.com"));
        banco.add(new Usuario(5L, "Thiago Costa", "123Teste", "thiago_costa@teste.com"));
    }

    public Usuario find(Long id) {
        return banco.stream().filter(u -> u.getId().equals(id))
                .findFirst().orElse(null);
    }

    public Usuario findByLogin(String login) {
        return banco.stream().filter(u -> u.getLogin().equalsIgnoreCase(login))
                .findFirst().orElse(null);
    }

    public List<Usuario> findAll() {
        System.out.println("Passou UsuarioRepository...");
        return banco;
    }

    public void save(Usuario usuario) {
        banco.add(usuario);
    }

    public void update(Usuario usuario) throws UsuarioException {
        Optional<Usuario> usuarioOpt = banco.stream().filter(u -> u.getId().equals(usuario.getId())).findFirst();

        if (usuarioOpt.isPresent()) {
            Usuario usuarioExistente = usuarioOpt.get();
            usuarioExistente.setLogin(usuario.getLogin());
            usuarioExistente.setSenha(usuario.getEmail());
            usuarioExistente.setEmail(usuario.getEmail());
        } else {
            throw new UsuarioNotFoundException(
                    "Usarario com o id " + usuario.getId() + " não foi encontrado no sistema.");
        }
    }

    public void delete(Long id) throws UsuarioException {
        Optional<Usuario> usuarioOpt = banco.stream().filter(u -> u.getId().equals(id)).findFirst();

        if (usuarioOpt.isPresent()) {
            banco.remove(usuarioOpt.get());
        } else {
            throw new UsuarioNotFoundException("Usarario com o id " + id + " não foi encontrado no sistema.");
        }
    }
}