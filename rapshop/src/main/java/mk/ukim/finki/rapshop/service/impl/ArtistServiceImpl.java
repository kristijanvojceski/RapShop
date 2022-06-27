package mk.ukim.finki.rapshop.service.impl;

import mk.ukim.finki.rapshop.model.Artist;
import mk.ukim.finki.rapshop.repository.ArtistRepository;
import mk.ukim.finki.rapshop.service.ArtistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ArtistServiceImpl implements ArtistService {

    private final  ArtistRepository artistRepository;

    public ArtistServiceImpl(ArtistRepository artistRepository) {
        this.artistRepository = artistRepository;
    }

    @Override
    public Optional<Artist> findById(Long id) {
        return this.artistRepository.findById(id);
    }

    @Override
    public List<Artist> findAll() {
        return this.artistRepository.findAll();
    }

    @Override
    public Optional<Artist> save(String name, String about) {
        return Optional.of(this.artistRepository.save(new Artist(name,about)));
    }

    @Override
    public void deleteVById(Long id) {
        this.artistRepository.deleteById(id);
    }
}
