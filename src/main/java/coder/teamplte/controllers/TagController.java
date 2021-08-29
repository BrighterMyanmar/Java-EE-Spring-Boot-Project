package coder.teamplte.controllers;

import coder.teamplte.models.Tag;
import coder.teamplte.models.daos.TagDao;
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
    static String UPLOAD_FOLDER = "src/main/resources/static/uploads/";

    @GetMapping
    public String index(Model model){
        model.addAttribute("tags",tagDao.findAll());
        return "tag/index";
    }

    @GetMapping(value="/create")
    public String create(){
        return "tag/create";
    }

    @PostMapping(value="/store")
    public String store(@RequestParam("files")MultipartFile[] files,@RequestParam String name){
        String filenames = saveMultipleFIle(files);
        Tag tag = new Tag();
        tag.setName(name);
        tag.setImages(filenames);
        tagDao.save(tag);
        return "redirect:";
    }

    public String saveMultipleFIle(MultipartFile[] files){
         String filenames = "";
         for(MultipartFile file : files){
             filenames += "," + saveFile(file);
         }
         return filenames.substring(1);
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
