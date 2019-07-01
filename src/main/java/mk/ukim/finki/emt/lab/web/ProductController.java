package mk.ukim.finki.emt.lab.web;

import mk.ukim.finki.emt.lab.model.Author;
import mk.ukim.finki.emt.lab.model.Category;
import mk.ukim.finki.emt.lab.model.Product;
import mk.ukim.finki.emt.lab.service.AuthorService;
import mk.ukim.finki.emt.lab.service.CategoryService;
import mk.ukim.finki.emt.lab.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class ProductController {


    @Autowired
    private ProductService productService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AuthorService authorService;

    @PostConstruct
    public void loadData()
    {
        Author a = new Author();
        a.setName("George R.R. Martin");
        authorService.save(a);

        Author a1 = new Author();
        a1.setName("J.R.R. Tolkien");
        authorService.save(a1);

        Category c = new Category();
        c.setName("Medieval fantasy");
        categoryService.save(c);

        Category c2 = new Category();
        c2.setName("High fantasy");
        categoryService.save(c2);

        Product p = new Product();
        p.setPicture_link("https://upload.wikimedia.org/wikipedia/en/thumb/a/a3/AFeastForCrows.jpg/220px-AFeastForCrows.jpg");
        p.setDescription("First book");
        p.setName("Game of Thrones");
        p.setPrice(50);
        p.setCategory(c);
        p.setAuthor(a);
        productService.save(p);

    }
    @GetMapping("/products") // prikaz na products
    public String Products(Model model) {
        model.addAttribute("ProductList",productService.findAll());
        return "Products.form";
    }

    @GetMapping("/product/{id}")
    public String ProductId (Model model, @PathVariable("id") Long id){
        //Get index from product list with this id
        Product product = null;
        for (Product p: this.productService.findAll()
        ) {
            if(p.getId().equals(id)){
                product = p;
                break;
            }
        }
        model.addAttribute("product",product);
        return "Product.idform";
    }


    @GetMapping("/products/add")
    public String addProduct(Model model,@Valid @ModelAttribute Product product){
        model.addAttribute(authorService.findAll());
        model.addAttribute(categoryService.findAll());

        return "Products.add";
    }

    @PostMapping("/products/add") //oa treba da vnese nesto korisnikot
    public String addProduct(@Valid @ModelAttribute Product product){
        //product.setId(getNextId());
        for (Author m: this.authorService.findAll()
        ) {
            if(m.getId().equals(product.getAuthor().getId())){
                product.setAuthor(m);
                break; // preku id go zima manufact
            }
        }
        for (Category c: this.categoryService.findAll()
        ) {
            if(c.getId().equals(product.getCategory().getId())){
                product.setCategory(c);
                break;
            }
        }
        productService.save(product);
        return "redirect:/products/";
    }

}
