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
public class Maintenances_detailsController {

    @GetMapping("/Maintenances_details")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Maintenances_details.getAll());
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            Maintenances[] allMaintenance = Maintenances.getAll();
            model.addAttribute("allMaintenance", allMaintenance);
            return "Maintenances_details";
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

    @PostMapping("/Maintenances_details")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String quantity, @RequestParam String unit_price, @RequestParam String sell_price, @RequestParam String component_type, @RequestParam String service, @RequestParam String component, @RequestParam String maintenance, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Maintenances_details instance = new Maintenances_details();
            instance.setQuantity(quantity) ; 
            instance.setUnit_price(unit_price) ; 
            instance.setSell_price(sell_price) ; 
            instance.setComponent_type(component_type,con ) ;
            instance.setService(service,con ) ;
            instance.setComponent(component,con ) ;
            instance.setMaintenance(maintenance,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Maintenances_details";
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

    @GetMapping("/Maintenances_details/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Maintenances_details.deleteById(id);
            return "redirect:/Maintenances_details";
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

    @GetMapping("/TraitMaintenances_details/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Maintenances_details currentMaintenances_details = Maintenances_details.getById(id ,con);
            model.addAttribute("currentMaintenances_details", currentMaintenances_details);
            model.addAttribute("all", Maintenances_details.getAll());
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            Maintenances[] allMaintenance = Maintenances.getAll();
            model.addAttribute("allMaintenance", allMaintenance);
            return "Maintenances_details";
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
