package com.example.demo.Service;

import com.example.demo.DataModels.Product;
import com.example.demo.Repository.ProductRepository;
import org.aspectj.apache.bcel.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class ProductService {
    private final ProductRepository productRepository = new ProductRepository();

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product addProduct(Product product) {
        product.setId(productRepository.getAvailableProductId());
        return productRepository.addProduct(product);
    }

    public Product UpdateProduct(Product product) {
        return productRepository.UpdateProduct(product);
    }

    public boolean DeleteProduct(int id) {
        return productRepository.DeleteProduct(id);
    }
}
