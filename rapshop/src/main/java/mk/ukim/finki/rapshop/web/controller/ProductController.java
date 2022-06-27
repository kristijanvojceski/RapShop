package mk.ukim.finki.rapshop.web.controller;

import mk.ukim.finki.rapshop.model.Artist;
import mk.ukim.finki.rapshop.model.Category;
import mk.ukim.finki.rapshop.model.Product;
import mk.ukim.finki.rapshop.repository.ArtistRepository;
import mk.ukim.finki.rapshop.repository.CategoryRepository;
import mk.ukim.finki.rapshop.service.ArtistService;
import mk.ukim.finki.rapshop.service.CategoryService;
import mk.ukim.finki.rapshop.service.ProductService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;
    private final CategoryService categoryService;
    private final ArtistService artistService;

    public ProductController(ProductService productService,
                             CategoryService categoryService,
                             ArtistService artistService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.artistService = artistService;
    }

    @GetMapping
    public String getProductPage(@RequestParam(required = false) String error, Model model) {
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        List<Product> products = this.productService.findAll();
        model.addAttribute("products", products);
        model.addAttribute("bodyContent", "products");
        return "master-template";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.productService.deleteById(id);
        return "redirect:/products";
    }

    @GetMapping("/edit-form/{id}")
    public String editProductPage(@PathVariable Long id, Model model) {
        if (this.productService.findById(id).isPresent()) {
            Product product = this.productService.findById(id).get();
            List<Artist> artists = this.artistService.findAll();
            List<Category> categories = this.categoryService.listCategories();
            model.addAttribute("artists", artists);
            model.addAttribute("categories", categories);
            model.addAttribute("product", product);
            model.addAttribute("bodyContent", "add-product");
            return "master-template";
        }
        return "redirect:/products?error=ProductNotFound";
    }

    @GetMapping("/add-form")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public String addProductPage(Model model) {
        List<Artist> artists = this.artistService.findAll();
        List<Category> categories = this.categoryService.listCategories();
        model.addAttribute("artists", artists);
        model.addAttribute("categories", categories);
        model.addAttribute("bodyContent", "add-product");
        return "master-template";
    }

    @PostMapping("/add")
    public String saveProduct(
            @RequestParam(required = false) Long id,
            @RequestParam String name,
            @RequestParam Double price,
            @RequestParam Integer quantity,
            @RequestParam Long category,
            @RequestParam Long artist
    ) {
        if(id!=null){
            this.productService.edit(id,name,price,quantity,category,artist);
        }else{
            this.productService.save(name,price,quantity,category,artist);
        }
        return "redirect:/products";
    }


}
