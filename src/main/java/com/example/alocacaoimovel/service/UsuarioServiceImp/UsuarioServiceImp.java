package com.example.alocacaoimovel.service.UsuarioServiceImp;

import com.example.alocacaoimovel.dto.UsuarioRequest;
import com.example.alocacaoimovel.model.Usuario;
import com.example.alocacaoimovel.repository.UsuarioRepository;
import com.example.alocacaoimovel.service.UsuarioService;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServiceImp implements UsuarioService {

    UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public void cadastrarUsuario(UsuarioRequest usuario) {
        var user = new Usuario();
        user.setEmail(usuario.email());
        user.setSenha(usuario.senha());

        usuarioRepository.save(user);
    }
}
