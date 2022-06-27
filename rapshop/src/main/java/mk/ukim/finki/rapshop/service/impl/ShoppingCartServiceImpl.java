package mk.ukim.finki.rapshop.service.impl;

import mk.ukim.finki.rapshop.model.Product;
import mk.ukim.finki.rapshop.model.ShoppingCart;
import mk.ukim.finki.rapshop.model.User;
import mk.ukim.finki.rapshop.model.enumerations.ShoppingCartStatus;
import mk.ukim.finki.rapshop.model.exceptions.ProductAlreadyInShoppingCartException;
import mk.ukim.finki.rapshop.model.exceptions.ProductNotFoundException;
import mk.ukim.finki.rapshop.model.exceptions.ShoppingCartNotFoundException;
import mk.ukim.finki.rapshop.model.exceptions.UserNotFoundException;
import mk.ukim.finki.rapshop.repository.ShoppingCartRepository;
import mk.ukim.finki.rapshop.repository.UserRepository;
import mk.ukim.finki.rapshop.service.ProductService;
import mk.ukim.finki.rapshop.service.ShoppingCartService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    private final ShoppingCartRepository shoppingCartRepository;
    private final UserRepository userRepository;
    private final ProductService productService;

    public ShoppingCartServiceImpl(ShoppingCartRepository shoppingCartRepository,
                                   UserRepository userRepository,
                                   ProductService productService) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.userRepository = userRepository;
        this.productService = productService;
    }

    @Override
    public List<Product> listAllProductsInShoppingCart(Long cartId) {
        if(!this.shoppingCartRepository.findById(cartId).isPresent())
            throw new ShoppingCartNotFoundException(cartId);
        return this.shoppingCartRepository.findById(cartId).get().getProducts();
    }

    @Override
    public ShoppingCart getActiveShoppingCart(String username) {
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(()-> new UserNotFoundException(username));
        return this.shoppingCartRepository
                .findByUserAndStatus(user, ShoppingCartStatus.CREATED)
                .orElseGet(()->{
                   ShoppingCart shoppingCart = new ShoppingCart(user);
                   return this.shoppingCartRepository.save(shoppingCart);
                });

    }

    @Override
    public ShoppingCart addProductToShoppingCart(String username, Long productId) {
        ShoppingCart shoppingCart = this.getActiveShoppingCart(username);
        Product product = this.productService.findById(productId)
                .orElseThrow(()-> new ProductNotFoundException(productId));
        if(shoppingCart.getProducts().stream().filter(r->r.getId().equals(productId))
                .collect(Collectors.toList()).size()>0)
            throw new ProductAlreadyInShoppingCartException(productId,username);
        shoppingCart.getProducts().add(product);
        return this.shoppingCartRepository.save(shoppingCart);
    }
}
