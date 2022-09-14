package com.example.demo.Controller;


import com.example.demo.DataModels.Order;
import com.example.demo.DataModels.Product;
import com.example.demo.Service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OrderController {
    private final OrderService  orderService = new OrderService();

    @CrossOrigin()
    @GetMapping ("/orders")
    public List<Order> getAll(){
        return orderService.getAll();
    }

    @CrossOrigin()
    @PostMapping ("/orders")
    public boolean AddOrder(@RequestBody Order order){
        return orderService.addOrder(order);
    }

    @DeleteMapping("/orders/{id}")
    public void DeleteProduct(@PathVariable Long id){
        orderService.DeleteOrder(id);
    }

}
