package bradesco.banco.PokeApi.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import bradesco.banco.PokeApi.exception.PokemonNotFoundException;
import bradesco.banco.PokeApi.model.History;
import bradesco.banco.PokeApi.model.Pokedex;
import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.repository.HistoryRepository;
import bradesco.banco.PokeApi.repository.PokedexRepository;

@Service
public class PokemonService {
    private final RestTemplate restTemplate;
    private final String url = "https://pokeapi.co/api/v2/";
    private final HistoryRepository historyRepository;
    private final PokedexRepository pokedexRepository;

    public PokemonService(HistoryRepository historyRepository, PokedexRepository pokedexRepository) {
        this.restTemplate = new RestTemplate();
        this.historyRepository = historyRepository;
        this.pokedexRepository = pokedexRepository;
    }

    public Pokemon getPokemonByName(String name) {
        try {
            Pokemon pokemon = restTemplate.getForObject(url + "pokemon/" + name, Pokemon.class);

            // Salvar histórico
            History history = new History();
            history.setPokemonName(pokemon.getName());
            history.setTimestamp(LocalDateTime.now());
            historyRepository.save(history);

            return pokemon;
        } catch (HttpClientErrorException.NotFound e) {
            throw new PokemonNotFoundException(name);
        }
    }

    public Pokemon addPokemonToPokedex(String name, String trainer) {
        Pokemon pokemon = getPokemonByName(name);
        Pokedex pokedex = new Pokedex();
        pokedex.getPokemons().add(pokemon);
        pokedex.setTrainerName(trainer);
        pokedexRepository.save(pokedex);
        return pokemon;
    }
}
