package mk.ukim.finki.emt.lab.service;

import mk.ukim.finki.emt.lab.model.Product;
import mk.ukim.finki.emt.lab.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public Product save(Product product) {
        return productRepository.save(product);
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    public List<Product> findByCategory(Long categoryId) {
        return productRepository.findByCategory_id(categoryId);
    }

    public List<Product> findByCategoryAndAuthor(Long categoryId, Long authorId) {
        return productRepository.findByCategory_idAndAuthor_Id(categoryId, authorId);
    }
}
