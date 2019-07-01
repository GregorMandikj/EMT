package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Product;
import mk.ukim.finki.emt.lab.service.CategoryService;
import mk.ukim.finki.emt.lab.service.ProductService;
import mk.ukim.finki.emt.lab.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/rest")
public class ProductRestController {

    @Autowired
    private ProductService productService; //Go povrzuvam za baza

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @GetMapping("/products")
    public List<Product> findAll() {
        return productService.findAll();
    }  // mi vraka json preku postman na produkti

    @GetMapping("/products/{id}")
    public Optional<Product> findById(@PathVariable("id") Long id) {
        return productService.findById(id);
    } //produktot so toa id

    @GetMapping("/product/category/{categoryId}")
    public List<Product> findByCategory(@PathVariable("categoryId") Long categoryId) {
        return productService.findByCategory(categoryId); // taa kategorija i toj id
    }

    @GetMapping("/product/category/{categoryId}/author/{authorId}")
    public List<Product> findByCategoryAndAuthor(@PathVariable("categoryId") Long categoryId,
                                                       @PathVariable("authorId") Long authorId) {
        return productService.findByCategoryAndAuthor(categoryId, authorId);
    }
}
