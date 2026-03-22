package com.example.alocacaoimovel.controller;


import com.example.alocacaoimovel.dto.UsuarioCadastroRequest;
import com.example.alocacaoimovel.dto.UsuarioRequest;
import com.example.alocacaoimovel.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<Void> cadastrarUsuario(@RequestBody UsuarioCadastroRequest usuario) {
        usuarioService.cadastrarUsuario(usuario);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<Void> loginUsuario(@RequestBody UsuarioRequest usuario) {
        usuarioService.realizarLogin(usuario);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
