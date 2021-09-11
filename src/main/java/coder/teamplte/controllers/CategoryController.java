package coder.teamplte.controllers;

import coder.teamplte.models.Category;
import coder.teamplte.models.daos.CategoryDao;
import coder.teamplte.services.ImageUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/cats")
public class CategoryController {

   @Autowired
   ImageUploadService imageUploadService;

   @Autowired
   CategoryDao categoryDao;

   @GetMapping
   public String index(Model model) {
      model.addAttribute("cats", categoryDao.findAll());
      return "cats/index";
   }

   @GetMapping(value = "/create")
   public String create() {
      return "cats/create";
   }

   @PostMapping(value = "/store")
   public String store(MultipartFile file, @RequestParam String name) {
      String filename = imageUploadService.saveFile(file);
      Category cat = new Category(name, filename);
      categoryDao.save(cat);
      return "redirect:";
   }

   @GetMapping(value = "/edit/{id}")
   public String edit(Model model, @PathVariable int id) {
      model.addAttribute("cat", categoryDao.findById(id).orElse(null));
      return "cats/edit";
   }

   @PostMapping(value = "/update/{id}")
   public String update(@PathVariable int id, @RequestParam("file") MultipartFile file, @RequestParam String name) {
      Category cat = categoryDao.findById(id).orElse(null);
      if (file.getSize() > 0) {
         cat.setImage(imageUploadService.saveFile(file));
      }
      cat.setName(name);
      categoryDao.save(cat);
      return "redirect:/cats";
   }

}
