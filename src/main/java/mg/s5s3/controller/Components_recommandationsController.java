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
public class Components_recommandationsController {

    @GetMapping("/Components_recommandations")
    public String showAll(Model model) {
        Connection con = null;
        try {
            model.addAttribute("year", Integer.toString(0));
            model.addAttribute("month", Integer.toString(0));
            model.addAttribute("component_type_id", Integer.toString(0));
            String[] months = {"Jan","Fev","Mars","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
            model.addAttribute("months", months);
            int[] years = {2023,2024,2025};
            model.addAttribute("years", years);
            con = Database.getConnection();
            model.addAttribute("all", Components_recommandations.getAll());
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Components_recommandations";
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

    @GetMapping("/Components_recommandations/filter/{year}/{month}/{component_type_id}")
    public String showAll(Model model, @PathVariable int year, @PathVariable int month, @PathVariable int component_type_id) {
        Connection con = null;
        try {
            model.addAttribute("year", Integer.toString(year));
            model.addAttribute("month", Integer.toString(month));
            model.addAttribute("component_type_id", Integer.toString(component_type_id));
            String[] months = {"Jan","Fev","Mars","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
            model.addAttribute("months", months);
            int[] years = {2023,2024,2025};
            model.addAttribute("years", years);
            con = Database.getConnection();
            if (month == 0 && component_type_id == 0 && year==0) {
                model.addAttribute("all", Components_recommandations.getAll());
            }
            else{
                model.addAttribute("all", Components_recommandations.getAllByCriteria(year, month, component_type_id));
            }
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Components_recommandations";
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


    @PostMapping("/Components_recommandations")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String date_recommandation, @RequestParam String component, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components_recommandations instance = new Components_recommandations();
            instance.setDate_recommandation(date_recommandation) ; 
            instance.setComponent(component,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Components_recommandations";
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

    @GetMapping("/Components_recommandations/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Components_recommandations.deleteById(id);
            return "redirect:/Components_recommandations";
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

    @GetMapping("/TraitComponents_recommandations/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components_recommandations currentComponents_recommandations = Components_recommandations.getById(id ,con);
            model.addAttribute("currentComponents_recommandations", currentComponents_recommandations);
            model.addAttribute("year", Integer.toString(0));
            model.addAttribute("month", Integer.toString(0));
            model.addAttribute("component_type_id", Integer.toString(0));
            String[] months = {"Jan","Fev","Mars","Apr","May","June","July","Aug","Sept","Oct","Nov","Dec"};
            model.addAttribute("months", months);
            int[] years = {2023,2024,2025};
            model.addAttribute("years", years);
            con = Database.getConnection();
            model.addAttribute("all", Components_recommandations.getAll());
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Components_recommandations";
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
