package br.senai.sc.trunfoapi;

import br.senai.sc.trunfoapi.controller.CartaController;
import br.senai.sc.trunfoapi.model.dto.CartaDTO;
import br.senai.sc.trunfoapi.model.entity.Carta;
import br.senai.sc.trunfoapi.model.enums.Posicao;
import br.senai.sc.trunfoapi.service.CartaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CartaController.class)
public class testCartaController {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CartaService cartaService;

    @Test
    public void criar_retornaCarta() throws Exception {
        CartaDTO cartaDTO = new CartaDTO("nomeDTO", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);
        Carta carta = new Carta(1, "nome", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);

        when(cartaService.criar(any())).thenReturn(carta);

        mockMvc.perform(post("/carta")
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cartaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(carta));
    }

    @Test
    public void listarTodos_retornaListadeCartas() throws Exception {
        List<Carta> listaCartas = new ArrayList<>();
        Carta carta1 = new Carta(1, "nome1", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);
        Carta carta2 = new Carta(2, "nome2", Posicao.ZAG, 5, 5, 99, 99, 99, 99, 99, 99, false);
        listaCartas.add(carta1);
        listaCartas.add(carta2);

        when(cartaService.listarTodos()).thenReturn(listaCartas);

        mockMvc.perform(get("/carta")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0]").value(listaCartas.get(0)))
                .andExpect(jsonPath("$.[1]").value(listaCartas.get(1)));
    }

    @Test
    public void listarUm_retornaCarta() throws Exception {
        Carta carta = new Carta(1, "nome", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);

        when(cartaService.listarUm(any())).thenReturn(carta);

        mockMvc.perform(get("/carta/{id}", carta.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(carta));
    }

    @Test
    public void editar_retornaCarta() throws Exception {
        Carta carta = new Carta(1, "nome", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);
        CartaDTO cartaDTO = new CartaDTO("nome", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);

        when(cartaService.salvar(any())).thenReturn(carta);
        when(cartaService.listarUm(any())).thenReturn(carta);

        mockMvc.perform(put("/carta/{id}", carta.getId())
                .contentType(APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(cartaDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(carta));
    }

    @Test
    public void deletar_retornaString() throws Exception {
        Carta carta = new Carta(1, "nome", Posicao.ATA, 5, 5, 99, 99, 99, 99, 99, 99, false);
        String strReturn = "Carta Deletada!";
        when(cartaService.deletar(any())).thenReturn(strReturn);

        mockMvc.perform(delete("/carta/{id}", carta.getId())
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value(strReturn));

    }
}
