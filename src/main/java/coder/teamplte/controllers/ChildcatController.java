package coder.teamplte.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import coder.teamplte.models.Childcat;
import coder.teamplte.models.Subcat;
import coder.teamplte.models.daos.ChildcatDao;
import coder.teamplte.models.daos.SubcatDao;
import coder.teamplte.services.ImageUploadService;

@Controller
@RequestMapping("/childcats")
public class ChildcatController {

   @Autowired
   ChildcatDao childcatDao;
   @Autowired
   SubcatDao subcatDao;
   @Autowired
   ImageUploadService imageUploadService;

   @GetMapping
   public String index(Model model) {
      // childcatDao.findAll().forEach(data -> System.out.println(data));
      model.addAttribute("childcats", childcatDao.findAll());
      return "childcats/index";
   }

   @GetMapping(value = "/create")
   public String create(Model model) {
      model.addAttribute("subcats", subcatDao.findAll());
      return "childcats/create";
   }

   @PostMapping(value = "/store")
   public String create(RedirectAttributes redirectAttr, @RequestParam String name, @RequestParam int subcat_id,
         @RequestParam("file") MultipartFile file) {

      Subcat subcat = subcatDao.findById(subcat_id).orElse(null);
      Childcat childcat = new Childcat();
      childcat.setName(name);
      childcat.setImage(imageUploadService.saveFile(file));
      childcat.setSubcat(subcat);
      childcatDao.save(childcat);

      redirectAttr.addFlashAttribute("message", "Child Category Creat Success!");

      return "redirect:/childcats";
   }

}
