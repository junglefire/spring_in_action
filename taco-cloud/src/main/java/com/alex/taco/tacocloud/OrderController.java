package com.alex.taco.tacocloud;

import javax.validation.Valid;

import org.springframework.web.bind.support.SessionStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.Errors;

@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    private OrderRepository orderRepo;

    public OrderController(OrderRepository orderRepo) {
      this.orderRepo = orderRepo;
    }
  
    @GetMapping("/current")
    public String orderForm() {
      return "orderForm";
    }

    @PostMapping
    public String processOrder(@Valid Order order, Errors errors, SessionStatus sessionStatus) {
      if (errors.hasErrors()) {
        return "orderForm";
      }
    
      orderRepo.save(order);
      sessionStatus.setComplete();
    
      return "redirect:/";
    }
}