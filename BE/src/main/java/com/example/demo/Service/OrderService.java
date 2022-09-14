package com.example.demo.Service;

import com.example.demo.DataModels.Order;
import com.example.demo.DataModels.Product;
import com.example.demo.Repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Service
public class OrderService {
  private final OrderRepository orderRepository = new OrderRepository();
  ProductService productService = new ProductService();

    public List<Order> getAll() {
       return orderRepository.getAll();
    }

    public boolean addOrder(Order order) {
        order.setOrder_Id((long) orderRepository.getAvailableOrderId());
        AtomicInteger idx = new AtomicInteger(-1);
        order.getProductIds().forEach((product) -> {
            idx.getAndIncrement();
            List<Product> productFound = productService.getAll().stream().filter((productC) -> productC.getId() == product).collect(Collectors.toList());

            productFound.get(0).setStock(productFound.get(0).getStock() - order.getQuantities().get(idx.get()));

            productService.UpdateProduct(productFound.get(0));
        });
        return orderRepository.addOrder(order);
    }

    public void UpdateOrder(Order order){
        orderRepository.UpdateOrder(order);
    }

    public void DeleteOrder(Long id) {
        orderRepository.DeleteOrder(id);
    }



}
