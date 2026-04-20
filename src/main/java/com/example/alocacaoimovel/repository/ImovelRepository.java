package com.example.alocacaoimovel.repository;

import com.example.alocacaoimovel.model.Imovel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImovelRepository extends JpaRepository<Imovel, Long> {
}
