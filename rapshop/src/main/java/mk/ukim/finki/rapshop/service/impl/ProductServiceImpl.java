package mk.ukim.finki.rapshop.service.impl;

import mk.ukim.finki.rapshop.model.Artist;
import mk.ukim.finki.rapshop.model.Category;
import mk.ukim.finki.rapshop.model.Product;
import mk.ukim.finki.rapshop.model.exceptions.ArtistNotFoundException;
import mk.ukim.finki.rapshop.model.exceptions.CategoryNotFoundException;
import mk.ukim.finki.rapshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.rapshop.repository.ArtistRepository;
import mk.ukim.finki.rapshop.repository.CategoryRepository;
import mk.ukim.finki.rapshop.repository.ProductRepository;
import mk.ukim.finki.rapshop.service.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final ArtistRepository artistRepository;

    public ProductServiceImpl(ProductRepository productRepository,
                              CategoryRepository categoryRepository,
                              ArtistRepository artistRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.artistRepository = artistRepository;
    }

    @Override
    public List<Product> findAll() {
        return this.productRepository.findAll();
    }

    @Override
    public Optional<Product> findById(Long id) {
        return this.productRepository.findById(id);
    }

    @Override
    public Optional<Product> findByName(String name) {
        return this.productRepository.findByName(name);
    }

    @Override
    public Optional<Product> save(String name, Double price, Integer quantity, Long categoryId, Long artistId) {
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()->new CategoryNotFoundException(categoryId));
        Artist artist = this.artistRepository.findById(artistId)
                .orElseThrow(()-> new ArtistNotFoundException(artistId));
        this.productRepository.deleteByName(name);
        return Optional.of(this.productRepository.save(new Product(name,price,quantity,category,artist)));
    }

    @Override
    public Optional<Product> edit(Long id, String name, Double price, Integer quantity, Long categoryId, Long artistId) {
        Product product = this.productRepository.findById(id).orElseThrow(()-> new ProductNotFoundException(id));
        product.setName(name);
        product.setPrice(price);
        product.setQuantity(quantity);
        Category category = this.categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException(categoryId));
        product.setCategory(category);
        Artist artist = this.artistRepository.findById(artistId)
                .orElseThrow(()-> new ArtistNotFoundException(id));
        product.setArtist(artist);
        return Optional.of(this.productRepository.save(product));
    }

    @Override
    public void deleteById(Long id) {
        this.productRepository.deleteById(id);
    }
}
