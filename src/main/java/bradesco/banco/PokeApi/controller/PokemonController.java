package bradesco.banco.PokeApi.controller;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import bradesco.banco.PokeApi.model.Pokemon;
import bradesco.banco.PokeApi.service.PokemonService;

@RestController
@RequestMapping("api/pokemon")
public class PokemonController {
    
    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/{name}")
    public Pokemon getPokemonByName(@PathVariable String name) {
        return pokemonService.getPokemonByName(name);
    }

    @PutMapping
    public String addPokemonToPokedex(@RequestParam String name, @RequestParam String trainer) {
        return pokemonService.addPokemonToPokedex(name, trainer);
    }

    @DeleteMapping
    public String removePokemonFromPokedex(@RequestParam String name, @RequestParam String trainer) {
        return pokemonService.removePokemonFromPokedex(name, trainer);
    }
}
