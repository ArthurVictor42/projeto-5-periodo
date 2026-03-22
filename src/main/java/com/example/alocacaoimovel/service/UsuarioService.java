package com.example.alocacaoimovel.service;

import com.example.alocacaoimovel.dto.UsuarioCadastroRequest;
import com.example.alocacaoimovel.dto.UsuarioRequest;

public interface UsuarioService {

    void cadastrarUsuario(UsuarioCadastroRequest usuario);

    void realizarLogin(UsuarioRequest usuario);
}
