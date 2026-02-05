package com.javanauta.agendadortarefa.infrastructure.security;


import com.javanauta.agendadortarefa.infrastructure.business.dto.UsuarioDTO;
import com.javanauta.agendadortarefa.infrastructure.client.UsuarioClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl {

    // Repositório para acessar dados de usuário no banco de dados
    @Autowired
    private UsuarioClient client;

    // Implementação do método para carregar detalhes do usuário pelo e-mail
    public UserDetails carregaDdosDeUsuario(String email, String token){

        UsuarioDTO usuarioDTO = client.buscarUsuarioPorEmail(email, token);//carrega as informações do usuario passado lá no endPoint

        return User
                .withUsername(usuarioDTO.getEmail())
                .password(usuarioDTO.getSenha())
                .build();
    }
}
