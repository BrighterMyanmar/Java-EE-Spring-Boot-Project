package coder.teamplte.controllers;

import coder.teamplte.models.Category;
import coder.teamplte.models.Subcat;
import coder.teamplte.models.daos.CategoryDao;
import coder.teamplte.models.daos.SubcatDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/subcats")
public class SubcatController {

    @Autowired
    SubcatDao subcatDao;
    @Autowired
    CategoryDao categoryDao;

    @GetMapping
    public String index(Model model){
        model.addAttribute("subcats",subcatDao.findAll());
        return "subcats/index";
    }

    @GetMapping(value="/store")
    @ResponseBody
    public String store(){
        Category cat = categoryDao.findById(1).orElse(null);
        Subcat subcat = new Subcat();
        subcat.setName("Computer");
        subcat.setImage("banner2.jpg");
        subcat.setCategory(cat);
        subcatDao.save(subcat);
        return "Save Success";
    }

    @GetMapping(value="/update")
    @ResponseBody
    public String update(){
//        Category cat = categoryDao.findById(5).orElse(null);
        Subcat subcat = subcatDao.findById(7).orElse(null);

        subcat.setName("Phone");
        subcatDao.save(subcat);

        return "Sub Category Updated";
    }

    @GetMapping(value="/delete")
    @ResponseBody
    public String delete(){
        Subcat subcat = subcatDao.findById(3).orElse(null);
        subcatDao.delete(subcat);
        return "Sub Category Deleted!";
    }

}
