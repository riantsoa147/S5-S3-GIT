package mg.s5s3.controller;

import java.sql.Connection;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import mg.s5s3.db.Database;
import mg.s5s3.model.*;

@Controller
public class GenderController {

    @GetMapping("/Gender")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Gender.getAll());
            return "Gender";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") ); 
            return "Error";
        } finally {
            if (con != null) {
                try { con.close(); } catch (Exception ignored) {}
            }
        }

    }

    @PostMapping("/Gender")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String name, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Gender instance = new Gender();
            instance.setName(name) ; 
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Gender";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") ); 
            return "Error";
        } finally {
            if (con != null) {
                try { con.close(); } catch (Exception ignored) {}
            }
        }
    }

    @GetMapping("/Gender/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Gender.deleteById(id);
            return "redirect:/Gender";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") ); 
            return "Error";
        } finally {
            if (con != null) {
                try { con.close(); } catch (Exception ignored) {}
            }
        }
    }

    @GetMapping("/TraitGender/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Gender currentGender = Gender.getById(id ,con);
            model.addAttribute("currentGender", currentGender);
            model.addAttribute("all", Gender.getAll());
            return "Gender";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") ); 
            return "Error";
        } finally {
            if (con != null) {
                try { con.close(); } catch (Exception ignored) {}
            }
        }
    }

}
