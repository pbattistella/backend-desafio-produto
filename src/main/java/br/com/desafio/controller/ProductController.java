package br.com.desafio.controller;

import br.com.desafio.model.Product;
import br.com.desafio.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductServiceImpl productService;

    @PostMapping(value = "/", produces = "application/json")
    public ResponseEntity<Product> create(@RequestBody Product product)
        throws URISyntaxException {
        Product createdProduct = productService.create(product);
        if(createdProduct == null){
            return ResponseEntity.notFound().build();
        } else {
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(createdProduct.getId())
                    .toUri();

            return ResponseEntity.created(uri).body(createdProduct);
        }
    }

    @PutMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Product> update(@RequestBody Product product, @PathVariable Long id){
        Product updatedProduct = productService.update(id, product);
        if(updatedProduct == null){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(updatedProduct);
        }
    }

    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Optional<Product>> read(@PathVariable Long id){
        Optional<Product> foundProduct = productService.read(id);
        if (foundProduct.isEmpty()){
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(foundProduct);
        }
    }

    @GetMapping(value = "/", produces = "application/json")
    public ResponseEntity<List<Product>> findAll(){
        List<Product> allProducts = productService.findAll();
        if(allProducts.size() == 0){
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(allProducts);
        }
    }

    @GetMapping(value = "/search")
    public ResponseEntity<List<Product>> search(
            @RequestParam(value = "min_price", required = false) String paramMinPrice,
            @RequestParam(value ="max_price", required = false) String paramMaxPrice,
            @RequestParam(value ="q", required = false) String paramQ){

        double minPrice = (paramMinPrice != null) ? Double.parseDouble(paramMinPrice) : 0;
        double maxPrice = (paramMaxPrice != null) ? Double.parseDouble(paramMaxPrice) : 0;
        String q = (paramQ != null) ? paramQ : "";
        List<Product> searchesProducts = productService.search(q, minPrice, maxPrice);
        System.out.println("Procura: " + searchesProducts);
        if(searchesProducts.size() == 0){
            return ResponseEntity.notFound().build();
        } else {

            return ResponseEntity.ok(searchesProducts);
        }
    }



    @DeleteMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<Object> delete(@PathVariable Long id){
        if (productService.delete(id)){
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
