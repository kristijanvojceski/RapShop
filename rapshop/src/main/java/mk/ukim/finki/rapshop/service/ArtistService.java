package mk.ukim.finki.rapshop.service;

import mk.ukim.finki.rapshop.model.Artist;

import java.util.List;
import java.util.Optional;

public interface ArtistService {

    Optional<Artist> findById(Long id);

    List<Artist> findAll();

    Optional<Artist> save(String name,String about);

    void deleteVById(Long id);


}
