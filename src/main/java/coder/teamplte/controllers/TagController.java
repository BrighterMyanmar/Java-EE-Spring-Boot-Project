package coder.teamplte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import coder.teamplte.dtos.TagDto;
import coder.teamplte.services.TagService;

@Controller
@RequestMapping("/tags")
public class TagController {

   @Autowired
   TagService tagService;

   @GetMapping
   public String index(Model model) {
      model.addAttribute("tags", tagService.all());
      return "tags/index";
   }

   @GetMapping(value = "/create")
   public String create(@ModelAttribute("tag") TagDto tagDto) {
      return "tags/create";
   }

   @PostMapping(value = "/create")
   public String store(@ModelAttribute("tag") TagDto tagDto) {
      System.out.println("We are here");
      System.out.println(tagDto);
      return "redirect:/tags";
   }

}
