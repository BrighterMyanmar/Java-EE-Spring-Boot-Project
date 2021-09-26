package coder.teamplte.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import coder.teamplte.dtos.UserDto;
import coder.teamplte.models.Product;
import coder.teamplte.models.User;
import coder.teamplte.models.daos.ProductDao;
import coder.teamplte.services.UserService;

@Controller
public class PageController { 
   @Autowired 
   ProductDao productDao;

   private UserService userService;

   public PageController(UserService userService) {
      this.userService = userService;
   }

   @PostMapping(value = "/order")
   public String order(@RequestParam String orders) {
      System.out.println(orders);
      // " 1:2 , 2:5, 3:10"
      String[] pList = orders.split(",");
      // 1:2
      // 2:5,
      // 3:10"
      for (String p : pList) {
         String[] pp = p.split(":"); // 1:2 [ 1, 2]
         int pId = Integer.parseInt(pp[0]);
         int pCount = Integer.parseInt(pp[1]);
         // Product product = productDao.findById(pId).orElse(null);
         // Order order = new Order();
         // order.setPid(pId);
         // order.setPrice(price);
         // order.setCount(count);
         // orderRepository.save(order);
         System.out.println("Pid is " + pId + " Product Count " + pCount);

         
      }
      return "home";
   }

   @GetMapping // (value = "/")
   public String index() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      if (auth instanceof AnonymousAuthenticationToken) {
         System.out.println("Anonymous User");
         return "home";
      } else {
         System.out.println("User name " + auth.getName());
         return "users/info";
      }
   }

   @GetMapping(value = "/info")
   public String info() {
      Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      User user = userService.get(auth.getName());
      System.out.println(user);
      return "users/info";
   }

   @GetMapping(value = "/login")
   public String login() {
      return "users/login";
   }

   @GetMapping(value = "/register")
   public String showRegister(Model model) {
      model.addAttribute("user", new UserDto());
      return "users/register";
   }

   @PostMapping(value = "/register")
   public String register(@ModelAttribute("user") UserDto userDto) {
      userService.save(userDto);
      return "redirect:/";
   }

}
