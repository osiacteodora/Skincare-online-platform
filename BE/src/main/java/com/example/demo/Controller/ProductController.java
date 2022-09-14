package com.example.demo.Controller;

import com.example.demo.DataModels.Product;
import com.example.demo.Service.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {
    private final ProductService productService = new ProductService();

    @CrossOrigin()
    @GetMapping("/products")
    public List<Product> getAll(){
        return productService.getAll();
    }

    @CrossOrigin()
    @PostMapping("/products")
    public Product AddProduct(@RequestBody Product product){
        return productService.addProduct(product);
    }

    @CrossOrigin()
    @PutMapping("/products/{id}")
    public Product UpdateProduct(@RequestBody Product product,@PathVariable int id){
        product.setId(id);
        return productService.UpdateProduct(product);
    }

    @CrossOrigin()
    @DeleteMapping("/products/{id}")
    public boolean DeleteProduct(@PathVariable int id){
        return productService.DeleteProduct(id);
    }

}
