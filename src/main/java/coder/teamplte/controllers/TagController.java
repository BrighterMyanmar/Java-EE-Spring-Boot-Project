package coder.teamplte.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import coder.teamplte.dtos.TagDto;
import coder.teamplte.models.Tag;
import coder.teamplte.services.ImageUploadService;
import coder.teamplte.services.TagService;

@Controller
@RequestMapping("/tags")
public class TagController {

   @Autowired
   TagService tagService;
   @Autowired
   ImageUploadService imageUploadService;

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
   public String store(@ModelAttribute("tag") @Valid TagDto tagDto, Errors errors) {
      if (errors.hasErrors()) {
         return "tags/create";
      } else {
         tagService.save(tagDto);
         return "redirect:/tags";
      }
   }

   @GetMapping(value = "/edit/{id}")
   public String edit(@ModelAttribute("tag") TagDto tagDto, @PathVariable int id, Model model) {
      model.addAttribute("oldtag", tagService.findById(id));
      return "tags/edit";
   }

   @PostMapping(value = "/edit/{id}")
   public String update(@ModelAttribute("tag") TagDto tagDto, @PathVariable int id) {
      Tag tag = tagService.findById(id);
      tag.setName(tagDto.getName());
      if (!tagDto.getFile().isEmpty()) {
         tag.setImage(imageUploadService.saveFile(tagDto.getFile()));
      }
      tagService.update(tag);
      return "redirect:/tags";
   }

   @GetMapping(value = "/delete/{id}")
   public String drop(@PathVariable int id) {
      Tag tag = tagService.findById(id);
      tagService.delete(tag);
      return "redirect:/tags";
   }

}
