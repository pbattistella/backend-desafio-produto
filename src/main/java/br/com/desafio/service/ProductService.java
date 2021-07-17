package br.com.desafio.service;

import br.com.desafio.model.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {

    public Product create(Product product);
    public Product update(Long id, Product product);
    public List<Product> findAll();
    public Optional<Product> read(Long id);
    public List<Product>search(String q, double minPrice, double maxPrice);
    public boolean delete(Long id);

}
