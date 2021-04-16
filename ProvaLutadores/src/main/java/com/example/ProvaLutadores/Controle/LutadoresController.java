package com.example.ProvaLutadores.Controle;

import com.example.ProvaLutadores.Dominio.Lutadores;
import com.example.ProvaLutadores.Repositorio.LutadoresRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/lutadores")
public class LutadoresController {

    @Autowired
    private LutadoresRepository repository;

    @GetMapping("/lutadores")
    public ResponseEntity getLutadores() {
        List<Lutadores> lutadores = repository.findAll();

        if (lutadores.isEmpty()) {
            return ResponseEntity.status(204).build();
        } else {
            return ResponseEntity.status(200).body(lutadores);
        }
    }

    @PostMapping("/concentrar")
    public ResponseEntity getConcentracoesRealizadas
            (@RequestBody Lutadores novoLutadores) {

        Integer total = novoLutadores.getConcentracoesRealizadas();

        return ResponseEntity.status(200).body(total++);
    }


    @GetMapping("/contagem-vivos")
    public ResponseEntity getVivo() {
        return ResponseEntity.status(200).body(repository.findByVivoTrue());
    }

    @GetMapping("/mortos")
    public ResponseEntity getMortos() {
        return ResponseEntity.status(200).body(repository.findByVivoFalse());
    }


    @GetMapping("/{id}")
    public ResponseEntity getLutadores(@PathVariable int id) {
        return ResponseEntity.of(repository.findById(id));
    }

    @PostMapping
    public ResponseEntity postLutadoro(@RequestBody Lutadores novoLutadores) {
        if (novoLutadores.getForcaGolpe() < 0.0) {
            return ResponseEntity.status(400).body("Número invalido");
        }
        novoLutadores.setVida(100.0);

        novoLutadores.setConcentracoesRealizadas(0);

        novoLutadores.setVivo(true);

        repository.save(novoLutadores);

        return ResponseEntity.status(200).build();
    }
}
