package com.example.springbootbackend.services;

import com.example.springbootbackend.exceptions.ResourceNotFoundException;
import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class ProductService {
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public List<Product> getProductsByCategoryAndSubcategory(String category, String subcategory) {
        String finalCategory = category.trim().toLowerCase();
        String finalSubCategory = subcategory.trim().toLowerCase();
        List<Product> filteredProductsByCategoryAndSubcategory = productRepository.findAll();
        filteredProductsByCategoryAndSubcategory = filteredProductsByCategoryAndSubcategory.stream()
                .filter(product -> product.getCategory() != null)
                .filter(product -> product.getCategory().trim().toLowerCase().equals(finalCategory))
                .collect(Collectors.toList());
        if(!finalSubCategory.equals("all")){
            filteredProductsByCategoryAndSubcategory = filteredProductsByCategoryAndSubcategory.stream()
                    .filter(product -> product.getSubcategory() != null)
                    .filter(product -> product.getSubcategory().trim().toLowerCase().equals(finalSubCategory))
                    .collect(Collectors.toList());
        }
        return filteredProductsByCategoryAndSubcategory;
    }

    public List<Product> getProductsBySportCategory(String sportCategory) {
        String finalSportCategory = sportCategory.trim().toLowerCase();
        if(finalSportCategory.equals("all")) return productRepository.findAll();
        return productRepository.findAll().stream()
                .filter(product -> product.getSportCategory() != null)
                .filter(product -> product.getSportCategory().trim().toLowerCase().equals(finalSportCategory))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsBySportBrand(String brand) {
        String finalBrand = brand.trim().toLowerCase();
        if(finalBrand.equals("all")) return productRepository.findAll();
        return productRepository.findAll().stream()
                .filter(product -> product.getBrand() != null)
                .filter(product -> product.getBrand().trim().toLowerCase().equals(finalBrand))
                .collect(Collectors.toList());
    }

    public List<Product> getProductsByPartOfName(String soughtFragment) {
        String finalSoughtFragment = soughtFragment.trim().toLowerCase();
        return productRepository.findAll().stream()
                .filter(product -> product.getName() != null)
                .filter(product -> product.getName().trim().toLowerCase().contains(finalSoughtFragment))
                .collect(Collectors.toList());
    }

    public Product createProduct(Product product) {
        System.out.println(product);
        return productRepository.save(product);
    }

    public ResponseEntity<Product> getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        return ResponseEntity.ok(product);
    }

    public ResponseEntity<Product> updateProduct(Long id, Product productDetails) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));
        // todo validates the data
        product.setBrand(productDetails.getBrand());
        product.setCategory(productDetails.getCategory());
        product.setSubcategory(productDetails.getSubcategory());
        product.setColors(productDetails.getColors());
        product.setDescription(productDetails.getDescription());
        product.setName(productDetails.getName());
        product.setOldPrice(productDetails.getOldPrice());
        product.setPrice(productDetails.getPrice());
        product.setSizes(productDetails.getSizes());
        product.setStock(productDetails.getStock());
        product.setGenre(productDetails.getGenre());
        product.setImagerUrl((productDetails.getImagerUrl()));


        Product updateProduct = productRepository.save(product);
        return ResponseEntity.ok(updateProduct);
    }

    public ResponseEntity<Map<String, Boolean>> deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not exist with id: " + id));

        productRepository.delete(product);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
