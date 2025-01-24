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
public class ConfigController {

    @GetMapping("/Config")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Config.getAll());
            return "Config";
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

    @PostMapping("/Config")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String maximum, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Config instance = new Config();
            instance.setMaximum(maximum) ; 
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Config";
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

    @GetMapping("/Config/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Config.deleteById(id);
            return "redirect:/Config";
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

    @GetMapping("/TraitConfig/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Config currentConfig = Config.getById(id ,con);
            model.addAttribute("currentConfig", currentConfig);
            model.addAttribute("all", Config.getAll());
            return "Config";
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
