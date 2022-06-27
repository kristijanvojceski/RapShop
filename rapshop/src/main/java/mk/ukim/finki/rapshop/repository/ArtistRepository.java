package mk.ukim.finki.rapshop.repository;

import mk.ukim.finki.rapshop.model.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist,Long> {
}
