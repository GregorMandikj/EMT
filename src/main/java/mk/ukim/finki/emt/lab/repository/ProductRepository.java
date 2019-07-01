package mk.ukim.finki.emt.lab.repository;

import mk.ukim.finki.emt.lab.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByCategory_id(Long id);
    List<Product> findByCategory_idAndAuthor_Id(Long categoryId, Long authorId);
}
