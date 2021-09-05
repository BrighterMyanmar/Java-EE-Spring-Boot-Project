package coder.teamplte.controllers;

import coder.teamplte.models.Category;
import coder.teamplte.models.Subcat;
import coder.teamplte.models.daos.CategoryDao;
import coder.teamplte.models.daos.SubcatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/subcats")
public class SubcatController {
    static String UPLOAD_FOLDER = "src/main/resources/static/uploads/";
    @Autowired
    SubcatDao subcatDao;
    @Autowired
    CategoryDao categoryDao;

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
    public String store(@RequestParam("file") MultipartFile file, @RequestParam String name, @RequestParam int category_id) {
        Category cat = categoryDao.findById(category_id).orElse(null);
        Subcat subcat = new Subcat();
        subcat.setName(name);
        subcat.setImage(saveFile(file));
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
    public String update(@PathVariable int id, @RequestParam String name,
                         @RequestParam int category_id, @RequestParam("file") MultipartFile file) {
        Category cat = categoryDao.findById(category_id).orElse(null);
        Subcat subcat = subcatDao.findById(id).orElse(null);
        if (file.getSize() > 0) {
            subcat.setImage(saveFile(file));
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

    public String saveFile(MultipartFile file) {
        String filename = file.getOriginalFilename();
        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + filename);
            Files.write(path, bytes);
        } catch (IOException e) {
            System.out.println("File Write Error");
        }
        return filename;
    }

}
