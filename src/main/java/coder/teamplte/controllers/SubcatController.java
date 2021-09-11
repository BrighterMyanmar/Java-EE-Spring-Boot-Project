package coder.teamplte.controllers;

import coder.teamplte.models.Category;
import coder.teamplte.models.Subcat;
import coder.teamplte.models.daos.CategoryDao;
import coder.teamplte.models.daos.SubcatDao;
import coder.teamplte.services.ImageUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/subcats")
public class SubcatController {

   @Autowired
   SubcatDao subcatDao;
   @Autowired
   CategoryDao categoryDao;
   @Autowired
   ImageUploadService imageUploadService;

   @GetMapping
   public String index(Model model) {
      model.addAttribute("subcats", subcatDao.findAll());
      return "subcats/index";
   }

   @GetMapping(value = "/create")
   public String create(Model model) {
      model.addAttribute("cats", categoryDao.findAll());
      return "subcats/create";
   }

   @PostMapping(value = "/store")
   public String store(@RequestParam("file") MultipartFile file, @RequestParam String name,
         @RequestParam int category_id) {
      Category cat = categoryDao.findById(category_id).orElse(null);
      Subcat subcat = new Subcat();
      subcat.setName(name);
      subcat.setImage(imageUploadService.saveFile(file));
      subcat.setCategory(cat);
      subcatDao.save(subcat);
      return "redirect:";
   }

   @GetMapping(value = "/edit/{id}")
   public String edit(Model model, @PathVariable int id) {
      model.addAttribute("cats", categoryDao.findAll());
      model.addAttribute("subcat", subcatDao.findById(id).orElse(null));
      return "subcats/edit";
   }

   @PostMapping(value = "/update/{id}")
   public String update(@PathVariable int id, @RequestParam String name, @RequestParam int category_id,
         @RequestParam("file") MultipartFile file) {
      Category cat = categoryDao.findById(category_id).orElse(null);
      Subcat subcat = subcatDao.findById(id).orElse(null);
      if (file.getSize() > 0) {
         subcat.setImage(imageUploadService.saveFile(file));
      }
      subcat.setName(name);
      subcat.setCategory(cat);
      subcatDao.save(subcat);

      return "redirect:/subcats";
   }

   @GetMapping(value = "/delete/{id}")
   public String delete(@PathVariable int id) {
      Subcat subcat = subcatDao.findById(id).orElse(null);
      subcatDao.delete(subcat);
      return "redirect:/subcats";
   }

}
