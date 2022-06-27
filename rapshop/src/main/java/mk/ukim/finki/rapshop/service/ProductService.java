package mk.ukim.finki.rapshop.service;

import mk.ukim.finki.rapshop.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    List<Product> findAll();

    Optional<Product> findById(Long id);

    Optional<Product> findByName(String name);

    Optional<Product> save(String name,Double price,Integer quantity, Long categoryId,Long artistId);

    Optional<Product> edit(Long id,String name,Double price,Integer quantity, Long categoryId,Long artistId);

    void deleteById(Long id);

}
