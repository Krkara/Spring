package com.kristjan.webshop.controller;

import com.kristjan.webshop.entity.Order;
import com.kristjan.webshop.entity.OrderRow;
import com.kristjan.webshop.exception.NotEnoughInStockException;
import com.kristjan.webshop.repository.OrderRepository;
import com.kristjan.webshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
public class OrderController {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    OrderService orderService;

    @GetMapping("orders")
    public ResponseEntity<List<Order>> getOrders() {
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @PostMapping("orders/{personId}")
    public ResponseEntity<String> addOrder(
            @RequestBody List<OrderRow> orderRows,
            @PathVariable Long personId
    ) throws NotEnoughInStockException, ExecutionException {
        double totalSum = orderService.getTotalSum(orderRows);
        Long id = orderService.saveOrderToDb(totalSum, orderRows, personId);
        String paymentUrl = orderService.makePayment(totalSum, id);
        return new ResponseEntity<>(paymentUrl, HttpStatus.CREATED);
    }

    @DeleteMapping("orders/{id}")
    public ResponseEntity<List<Order>> deleteOrder(@PathVariable Long id) {
        orderRepository.deleteById(id);
        return new ResponseEntity<>(orderRepository.findAll(), HttpStatus.OK);
    }

    @GetMapping("orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable Long id) {
        return new ResponseEntity<>(orderRepository.findById(id).get(), HttpStatus.OK);
    }

    @PutMapping("orders/{id}")
    public List<Order> updateOrder(@PathVariable Long id, @RequestBody Order order) {
        if (orderRepository.existsById(id)) {
            order.setId(orderRepository.findById(id).get().getId());
            orderRepository.save(order);
        }
        return orderRepository.findAll();
    }

    @GetMapping("check-payment/{paymentReference}")
    public ResponseEntity<Boolean> checkPayment(@PathVariable String paymentReference) {
        return new ResponseEntity<>(orderService.checkPayment(paymentReference),HttpStatus.OK);
    }
}
