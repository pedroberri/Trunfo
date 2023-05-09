package br.senai.sc.trunfoapi.service;

import br.senai.sc.trunfoapi.model.entity.Carta;
import br.senai.sc.trunfoapi.repository.CartaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CartaService {

    private CartaRepository cartaRepository;

    public Carta criar(Carta carta) {
        return cartaRepository.save(carta);
    }

    public List<Carta> listarTodos() {
        return cartaRepository.findAll();
    }

    public Carta salvar(Carta carta) {
        return cartaRepository.save(carta);
    }

    public String deletar(Integer id){
        cartaRepository.deleteById(id);
        return "Carta Deletada!";
    }

    public Carta listarUm(Integer id) {
        Optional<Carta> cartaOptional = cartaRepository.findById(id);
        if (cartaOptional.isPresent()) {
            return cartaOptional.get();
        }
        throw new RuntimeException("Não encontrado");
    }
}
