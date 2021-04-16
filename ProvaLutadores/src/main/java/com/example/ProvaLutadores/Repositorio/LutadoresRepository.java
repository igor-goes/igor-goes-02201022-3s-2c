package com.example.ProvaLutadores.Repositorio;

import com.example.ProvaLutadores.Dominio.Lutadores;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface LutadoresRepository extends JpaRepository<Lutadores, Integer> {

    List<Lutadores> findByVivoTrue();
    List<Lutadores> findByVivoFalse();

    List<Lutadores> findByConcentracoesRealizadas(Integer concentracoesRealizadas);

}
