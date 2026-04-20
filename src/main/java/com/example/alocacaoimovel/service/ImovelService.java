package com.example.alocacaoimovel.service;

import com.example.alocacaoimovel.dto.ImovelRequest;
import com.example.alocacaoimovel.model.Imovel;

import java.util.List;

public interface ImovelService {
    List<Imovel> buscarImovel(ImovelRequest buscar);
}
