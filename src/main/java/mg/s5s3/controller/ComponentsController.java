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
public class ComponentsController {

    @GetMapping("/Components")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Components.getAll());
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Models[] allModels = Models.getAll();
            model.addAttribute("allModels", allModels);
            Dimensions[] allDimension = Dimensions.getAll();
            model.addAttribute("allDimension", allDimension);
            return "Components";
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

    @PostMapping("/Components")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String name, @RequestParam String sell_price, @RequestParam String stock, @RequestParam String component_type, @RequestParam String models, @RequestParam String dimension, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components instance = new Components();
            instance.setName(name) ; 
            instance.setSell_price(sell_price) ; 
            instance.setStock(stock) ; 
            instance.setComponent_type(component_type,con ) ;
            instance.setModels(models,con ) ;
            if (!dimension.isEmpty() && dimension!=null) {
                instance.setDimension(dimension,con ) ;
            }
            else{
                instance.setDimension(new Dimensions());
            }
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Components";
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

    @GetMapping("/Components/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Components.deleteById(id);
            return "redirect:/Components";
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

    @GetMapping("/TraitComponents/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components currentComponents = Components.getById(id ,con);
            model.addAttribute("currentComponents", currentComponents);
            model.addAttribute("all", Components.getAll());
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Models[] allModels = Models.getAll();
            model.addAttribute("allModels", allModels);
            Dimensions[] allDimension = Dimensions.getAll();
            model.addAttribute("allDimension", allDimension);
            return "Components";
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
