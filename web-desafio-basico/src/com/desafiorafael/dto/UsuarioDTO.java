package com.desafiorafael.dto;

import javax.xml.bind.annotation.XmlRootElement;

import com.desafiorafael.exception.InvalidUsuarioDataException;
import com.desafiorafael.exception.UsuarioException;

// import javax.validation.constraints.Size;

@XmlRootElement
public class UsuarioDTO {
    private Long id;

    // @NotBlank(message = "Login é obrigatório")
    // @Size(min = 4, max = 30, message = "O Login deve ser entre 4 a 30
    // caracteres")
    private String login;

    // @NotBlank(message = "Senha é obrigatório")
    // @Size(min = 8, message = "Senha deve ter no minimo 8 caracteres")
    private String senha;

    // @NotBlank(message = "Email é obrigatório")
    // @Email(message = "Email deve ser válido")
    private String email;

    public UsuarioDTO() {
    }

    // public UsuarioDTO(
    // @NotBlank(message = "Login é obrigatório") @Size(min = 4, max = 30, message =
    // "O Login deve ser entre 4 a 30 caracteres") String login,
    // @NotBlank(message = "Senha é obrigatório") @Size(min = 8, message = "Senha
    // deve ter no minimo 8 caracteres") String senha,
    // @NotBlank(message = "Email é obrigatório") @Email(message = "Email deve ser
    // válido") String email) {
    // this.login = login;
    // this.senha = senha;
    // this.email = email;
    // }

    public UsuarioDTO(String login, String senha, String email) {
        this.login = login;
        this.senha = senha;
        this.email = email;
    }

    public void validar() throws UsuarioException {
        String erros = "";
        if (login == null) {
            erros += "Login é obrigatório; ";
        }
        if (senha == null) {
            erros += "Senha é obrigatório; ";
        }
        if (email == null) {
            erros += "Email é obrigatório; ";
        }
        if (!erros.isEmpty()) {
            throw new InvalidUsuarioDataException(erros);
        }
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "UsuarioDTO [id=" + id + ", login=" + login + ", senha=" + senha + ", email=" + email + "]";
    }
}
