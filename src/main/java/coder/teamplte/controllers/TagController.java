package coder.teamplte.controllers;

import coder.teamplte.models.Tag;
import coder.teamplte.models.daos.TagDao;
import coder.teamplte.services.ImageUploadService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/tags")
public class TagController {
   @Autowired
   TagDao tagDao;
   @Autowired
   ImageUploadService imageUploadService;

   @GetMapping
   public String index(Model model) {
      model.addAttribute("tags", tagDao.findAll());
      return "tag/index";
   }

   @GetMapping(value = "/create")
   public String create() {
      return "tag/create";
   }

   @PostMapping(value = "/store")
   public String store(@RequestParam("files") MultipartFile[] files, @RequestParam String name) {
      String filenames = imageUploadService.saveMultipleFIle(files);
      Tag tag = new Tag();
      tag.setName(name);
      tag.setImages(filenames);
      tagDao.save(tag);
      return "redirect:";
   }

}
