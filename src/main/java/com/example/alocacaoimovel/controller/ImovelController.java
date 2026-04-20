package com.example.alocacaoimovel.controller;

import com.example.alocacaoimovel.dto.ImovelRequest;
import com.example.alocacaoimovel.model.Imovel;
import com.example.alocacaoimovel.service.ImovelService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/imoveis")
public class ImovelController {

    private final ImovelService imovelService;

    public ImovelController(ImovelService imovelService) {
        this.imovelService = imovelService;
    }

    @PostMapping("/buscar")
    public ResponseEntity<List<Imovel>> buscarImovel(@RequestBody ImovelRequest buscar) {
        List<Imovel> resultado = imovelService.buscarImovel(buscar);
        return ResponseEntity.ok(resultado);
    }
}
