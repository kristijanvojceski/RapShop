package mk.ukim.finki.rapshop.repository;

import mk.ukim.finki.rapshop.model.ShoppingCart;
import mk.ukim.finki.rapshop.model.User;
import mk.ukim.finki.rapshop.model.enumerations.ShoppingCartStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ShoppingCartRepository extends JpaRepository<ShoppingCart,Long> {

    Optional<ShoppingCart> findByUserAndStatus(User user, ShoppingCartStatus status);

}
