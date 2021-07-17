package br.com.desafio.service;

import br.com.desafio.model.Product;
import br.com.desafio.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService{

    @Autowired
    ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product update(Long id, Product product) {
        Optional<Product> updatedProduct = productRepository.findById(id);
        if (updatedProduct.isEmpty() ) {
            updatedProduct.get().setName(product.getName());
            updatedProduct.get().setDescription(product.getDescription());
            updatedProduct.get().setPrice(product.getPrice());
            return productRepository.save(updatedProduct.get());
        } else {
            return null;
        }
    }

    @Override
    public Optional<Product> read(Long id){
        return productRepository.findById(id);
    }

    @Override
    public List<Product> findAll(){
        return productRepository.findAll();
    }

    @Override
    public List<Product>search(String q, double minPrice, double maxPrice){

        if (!q.equals("")){
            if (minPrice == 0 && maxPrice == 0){
                return productRepository.findByNameContainingOrDescriptionContaining(q, q);
            } else if (minPrice != 0 && maxPrice != 0){
                return productRepository.findByNameContainingOrDescriptionContainingAndPriceGreaterThanEqualAndPriceLessThanEqual(q , q, minPrice, maxPrice);
            } else {
                if (minPrice != 0){
                    return productRepository.findByNameContainingOrDescriptionContainingAndPriceGreaterThanEqual(q , q, minPrice);
                } else {
                    return productRepository.findByNameContainingOrDescriptionContainingAndPriceLessThanEqual(q , q, maxPrice);
                }

            }
        } else {
            if (minPrice != 0 && maxPrice != 0){
                return productRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(minPrice, maxPrice);
            } else {
                return productRepository.findByPriceGreaterThanEqualOrPriceLessThanEqual(minPrice, maxPrice);
            }
        }
    }

    @Override
    public boolean delete(Long id) {
        try {
            productRepository.deleteById(id);
            return productRepository.findById(id).isEmpty();
        } catch (Exception e){
            return false;
        }
    }
}
