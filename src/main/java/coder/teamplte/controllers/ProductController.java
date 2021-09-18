package coder.teamplte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coder.teamplte.models.Category;
import coder.teamplte.models.Childcat;
import coder.teamplte.models.Product;
import coder.teamplte.models.Subcat;
import coder.teamplte.models.daos.ChildcatDao;
import coder.teamplte.models.daos.ProductDao;
import coder.teamplte.services.ImageUploadService;

@Controller
@RequestMapping("/products")
public class ProductController {

   @Autowired
   ProductDao productDao;
   @Autowired
   ChildcatDao childcatDao;
   @Autowired
   ImageUploadService imageUploadService;

   @GetMapping
   public String index(Model model) {
      model.addAttribute("products", productDao.findAll());
      return "products/index";
   }

   @GetMapping(value = "/create")
   public String create(Model model) {
      model.addAttribute("childcats", childcatDao.findAll());
      return "products/create";
   }

   @PostMapping(value = "/store")
   public String store(RedirectAttributes redirectAttr, @RequestParam String name, @RequestParam String brand,
         @RequestParam int price, @RequestParam int childcat_id, @RequestParam("files") MultipartFile[] files) {

      Childcat childcat = childcatDao.findById(childcat_id).orElse(null);
      Subcat subcat = childcat.getSubcat();
      Category category = subcat.getCategory();

      Product product = new Product();
      product.setName(name);
      product.setBrand(brand);
      product.setPrice(price);
      product.setImages(imageUploadService.saveMultipleFIle(files));
      product.setCategory(category);
      product.setSubcat(subcat);
      product.setChildcat(childcat);

      productDao.save(product);
      redirectAttr.addFlashAttribute("message", "Product Create Success!");
      return "redirect:/products";
   }

   @GetMapping(value = "/edit/{id}")
   public String edit(Model model, @PathVariable int id) {
      model.addAttribute("childcats", childcatDao.findAll());
      model.addAttribute("product", productDao.findById(id).orElse(null));
      return "products/edit";
   }

   @PostMapping(value = "/update/{id}")
   public String update(RedirectAttributes redirectAttr, @PathVariable int id, @RequestParam String name,
         @RequestParam String brand, @RequestParam int price, @RequestParam int childcat_id,
         @RequestParam("files") MultipartFile[] files) {
      Childcat childcat = childcatDao.findById(childcat_id).orElse(null);
      Subcat subcat = childcat.getSubcat();
      Category category = subcat.getCategory();

      Product product = productDao.findById(id).orElse(null);
      product.setName(name);
      product.setBrand(brand);
      product.setPrice(price);
      product.setChildcat(childcat);
      product.setSubcat(subcat);
      product.setCategory(category);

      if (files[0].getSize() > 0) {
         product.setImages(imageUploadService.saveMultipleFIle(files));
      }

      productDao.save(product);
      redirectAttr.addFlashAttribute("message", "Product Update Success!");
      return "redirect:/products";
   }

   @GetMapping(value = "/delete/{id}")
   public String delete(RedirectAttributes redirectAttr, @PathVariable int id) {
      Product product = productDao.findById(id).orElse(null);
      productDao.delete(product);
      redirectAttr.addFlashAttribute("message", "Product Deleted!");
      return "redirect:/products";
   }

}
