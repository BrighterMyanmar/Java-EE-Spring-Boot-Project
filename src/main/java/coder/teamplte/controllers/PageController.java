package coder.teamplte.controllers;
import coder.teamplte.models.Category;
import coder.teamplte.models.daos.CategoryDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/cats")
public class PageController {
    static String UPLOAD_FOLDER = "src/main/resources/static/uploads/";

    @Autowired
    CategoryDao categoryDao;

    @GetMapping
    public String index(Model model){
        model.addAttribute("cats",categoryDao.findAll());
        return "cats/index";
    }

    @GetMapping(value="/create")
    public String create(){
        return "cats/create";
    }

    @PostMapping(value="/store")
    public String store(MultipartFile file, @RequestParam String name){
       String filename =  saveFile(file);
       Category cat = new Category(name,filename);
       categoryDao.save(cat);
       return "redirect:";
    }

    @GetMapping(value="/edit/{id}")
    public String edit(Model model,@PathVariable int id){
        model.addAttribute("cat",categoryDao.findById(id).orElse(null));
        return "cats/edit";
    }

    @PostMapping(value="/update/{id}")
    public String update(@PathVariable int id,@RequestParam("file") MultipartFile file,@RequestParam String name){
        Category cat = categoryDao.findById(id).orElse(null);
        if(file.getSize() > 0){
            cat.setImage(saveFile(file));
        }
        cat.setName(name);
        categoryDao.save(cat);
        return "redirect:/cats";
    }

    public String saveFile(MultipartFile file){
        String filename = file.getOriginalFilename();
        try{
            byte[] bytes = file.getBytes();
            Path path = Paths.get(UPLOAD_FOLDER + filename);
            Files.write(path,bytes);
        }catch(IOException e){
            System.out.println("File Write Error");
        }
        return filename;
    }

}
