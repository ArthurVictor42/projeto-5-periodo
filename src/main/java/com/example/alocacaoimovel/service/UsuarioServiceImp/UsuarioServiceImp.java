package com.example.alocacaoimovel.service.UsuarioServiceImp;

import com.example.alocacaoimovel.dto.UsuarioCadastroRequest;
import com.example.alocacaoimovel.dto.UsuarioRequest;
import com.example.alocacaoimovel.model.Usuario;
import com.example.alocacaoimovel.repository.UsuarioRepository;
import com.example.alocacaoimovel.service.UsuarioService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImp implements UsuarioService {

    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImp(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void cadastrarUsuario(UsuarioCadastroRequest usuario) {

        if (usuario.email() == null || usuario.email().isEmpty()) {
            throw new RuntimeException("Email invalido");
        }

        if (usuario.senha() == null || usuario.senha().isEmpty() || usuario.senha().length() < 8 || usuario.senha().length() > 16) {
            throw new RuntimeException("Senha deve ter entre 8 e 16 caracteres");
        }

        if (!usuario.senha().equals(usuario.confirmasenha())) {
            throw new RuntimeException("Senha incorreta");
        }

        if (usuarioRepository.existsByEmail(usuario.email())) {
            throw new RuntimeException("Email já cadastrado");
        }

        Usuario user = new Usuario();
        user.setEmail(usuario.email());

        String senhaCriptografada = passwordEncoder.encode(usuario.senha());
        user.setSenha(senhaCriptografada);

        usuarioRepository.save(user);
    }

    @Override
    public void realizarLogin(UsuarioRequest usuario) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmail(usuario.email());
        Usuario user = usuarioOptional.orElseThrow(() -> new RuntimeException("Usuario não encotrado"));

        if (!passwordEncoder.matches(usuario.senha(), user.getSenha())) {
            throw new RuntimeException("Senha incorreta");
        }
    }
}
