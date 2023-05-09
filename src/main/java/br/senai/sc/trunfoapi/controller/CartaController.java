package br.senai.sc.trunfoapi.controller;

import br.senai.sc.trunfoapi.model.dto.CartaDTO;
import br.senai.sc.trunfoapi.model.entity.Carta;
import br.senai.sc.trunfoapi.service.CartaService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/carta")
@CrossOrigin(origins = "*")
public class CartaController {

    private CartaService cartaService;

    @PostMapping
    public ResponseEntity<Carta> criar(
//            @Valid
                                           @RequestBody CartaDTO cartaDTO) {
        Carta carta = new Carta();
        BeanUtils.copyProperties(cartaDTO, carta);
        return ResponseEntity.ok(cartaService.criar(carta));
    }

    @GetMapping
    public ResponseEntity<List<Carta>> listarTodos() {
        return ResponseEntity.ok(cartaService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Carta> listarUm(@PathVariable Integer id) {
        return ResponseEntity.ok(cartaService.listarUm(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Carta> editar(@PathVariable Integer id, @RequestBody CartaDTO cartaDTO){
        Carta cartaExistente = cartaService.listarUm(id);
        BeanUtils.copyProperties(cartaDTO, cartaExistente);
        return ResponseEntity.status(200).body(cartaService.salvar(cartaExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id){
        return ResponseEntity.ok(cartaService.deletar(id));
    }

}
