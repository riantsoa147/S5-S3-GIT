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
public class Failing_componentsController {

    @GetMapping("/Failing_components")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Failing_components.getAll());
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Diagnostics[] allDiagnostic = Diagnostics.getAll();
            model.addAttribute("allDiagnostic", allDiagnostic);
            return "Failing_components";
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

    @PostMapping("/Failing_components")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String service, @RequestParam String component_type, @RequestParam String diagnostic, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Failing_components instance = new Failing_components();
            instance.setService(service,con ) ;
            instance.setComponent_type(component_type,con ) ;
            instance.setDiagnostic(diagnostic,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Failing_components";
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

    @GetMapping("/Failing_components/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Failing_components.deleteById(id);
            return "redirect:/Failing_components";
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

    @GetMapping("/TraitFailing_components/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Failing_components currentFailing_components = Failing_components.getById(id ,con);
            model.addAttribute("currentFailing_components", currentFailing_components);
            model.addAttribute("all", Failing_components.getAll());
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Diagnostics[] allDiagnostic = Diagnostics.getAll();
            model.addAttribute("allDiagnostic", allDiagnostic);
            return "Failing_components";
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
