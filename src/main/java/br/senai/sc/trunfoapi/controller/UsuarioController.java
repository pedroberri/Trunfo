package br.senai.sc.trunfoapi.controller;

import br.senai.sc.trunfoapi.model.dto.UsuarioDTO;
import br.senai.sc.trunfoapi.model.entity.Usuario;
import br.senai.sc.trunfoapi.service.UsuarioService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<Usuario> criar(@RequestBody UsuarioDTO usuarioDTO){
        Usuario usuario = new Usuario();
        BeanUtils.copyProperties(usuarioDTO, usuario);
        return ResponseEntity.ok(usuarioService.criar(usuario));
    }

    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> listarUm(@PathVariable Integer id) {
        return ResponseEntity.ok(usuarioService.listarUm(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> editar(@PathVariable Integer id, @RequestBody UsuarioDTO usuarioDTO){
        Usuario usuarioExistente = usuarioService.listarUm(id);
        BeanUtils.copyProperties(usuarioDTO, usuarioExistente);
        return ResponseEntity.status(200).body(usuarioService.salvar(usuarioExistente));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletar(@PathVariable Integer id){
        return ResponseEntity.ok(usuarioService.deletar(id));
    }
}
