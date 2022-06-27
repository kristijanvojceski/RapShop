package mk.ukim.finki.rapshop.service;

import mk.ukim.finki.rapshop.model.Product;
import mk.ukim.finki.rapshop.model.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {

    List<Product> listAllProductsInShoppingCart(Long cartId);

    ShoppingCart getActiveShoppingCart(String username);

    ShoppingCart addProductToShoppingCart(String username,Long productId);

}
