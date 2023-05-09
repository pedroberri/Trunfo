package br.senai.sc.trunfoapi;

import br.senai.sc.trunfoapi.controller.UsuarioController;
import br.senai.sc.trunfoapi.model.dto.UsuarioDTO;
import br.senai.sc.trunfoapi.model.entity.Usuario;
import br.senai.sc.trunfoapi.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebMvcTest(UsuarioController.class)
public class testUsuarioController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UsuarioService usuarioService;

    @Test
    public void criar_retornaUsuario() throws Exception {
        Usuario usuario = new Usuario(1, "nome", "senha");
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome", "senha");

        when(usuarioService.criar(any())).thenReturn(usuario);

        mockMvc.perform(post("/usuario")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(usuario));
    }

    @Test
    public void listarTodos_retornaListadeUsuarios() throws Exception {
        List<Usuario> listaDeUsuarios = new ArrayList<>();
        Usuario usuario1 = new Usuario(1, "nome1", "senha1");
        Usuario usuario2 = new Usuario(2, "nome2", "senha2");
        listaDeUsuarios.add(usuario1);
        listaDeUsuarios.add(usuario2);

        when(usuarioService.listarTodos()).thenReturn(listaDeUsuarios);

        mockMvc.perform(get("/usuario")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").value(listaDeUsuarios.get(0)))
                .andExpect(jsonPath("$.[1]").value(listaDeUsuarios.get(1)));
    }

    @Test
    public void listarUm_retornaUsuario() throws Exception {
        Usuario usuario = new Usuario(1, "nome", "senha");

        when(usuarioService.listarUm(any())).thenReturn(usuario);

        mockMvc.perform(get("/usuario/{id}", usuario.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(usuario));
    }

    @Test
    public void editar_retornaUsuario() throws Exception {
        Usuario usuario = new Usuario(1, "nome", "senha");
        UsuarioDTO usuarioDTO = new UsuarioDTO("nome", "senha");

        when(usuarioService.salvar(any())).thenReturn(usuario);
        when(usuarioService.listarUm(any())).thenReturn(usuario);

        mockMvc.perform(put("/usuario/{id}", usuario.getId())
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(usuario));
    }

    @Test
    public void deletar_retornaString() throws Exception {
        Usuario usuario = new Usuario(1, "nome", "senha");
        String strReturn = "Usuário Deletado!";

        when(usuarioService.deletar(any())).thenReturn(strReturn);

        mockMvc.perform(delete("/usuario/{id}", usuario.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(strReturn));
    }
}
