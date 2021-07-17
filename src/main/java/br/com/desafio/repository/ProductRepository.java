package br.com.desafio.repository;

import br.com.desafio.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    public List<Product> findByNameContainingOrDescriptionContaining(String qName, String qDescription);
    public List<Product> findByNameContainingOrDescriptionContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(String qName, String qDescription, double minPrice, double maxPrice);
    public List<Product> findByNameContainingOrDescriptionContainingAndPriceGreaterThanEqual(String qName, String qDescription, double minPrice);
    public List<Product> findByNameContainingOrDescriptionContainingAndPriceLessThanEqual(String qName, String qDescription, double maxPrice);

    public List<Product> findByPriceGreaterThanEqualAndPriceLessThanEqual(double minPrice, double maxPrice);
    public List<Product> findByPriceGreaterThanEqualOrPriceLessThanEqual(double minPrice, double maxPrice);


}
