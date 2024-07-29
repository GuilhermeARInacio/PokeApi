package bradesco.banco.PokeApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import bradesco.banco.PokeApi.model.Pokemon;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PokemonRepository extends JpaRepository<Pokemon, Long> {
    Pokemon findByName(String name);

    @Query(value = "SELECT * FROM POKEMON AS P WHERE P.ID = :idPokemon AND P.POKEDEX_ID = :idPokedex", nativeQuery = true)
    List<Pokemon> findByPokemon(@Param("idPokemon") int idPokemon,@Param("idPokedex") Long idPokedex);
}
