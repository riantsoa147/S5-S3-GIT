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
public class Components_stockController {

    @GetMapping("/Components_stock")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Components_stock.getAll());
            Providers[] allProver = Providers.getAll();
            model.addAttribute("allProver", allProver);
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            return "Components_stock";
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

    @PostMapping("/Components_stock")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String entrance, @RequestParam String outflow, @RequestParam String stock_date, @RequestParam String unit_price, @RequestParam String prover, @RequestParam String component, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components_stock instance = new Components_stock();
            instance.setEntrance(entrance) ; 
            instance.setOutflow(outflow) ; 
            instance.setStock_date(stock_date) ; 
            instance.setUnit_price(unit_price) ; 
            instance.setProver(prover,con ) ;
            instance.setComponent(component,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Components_stock";
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

    @GetMapping("/Components_stock/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Components_stock.deleteById(id);
            return "redirect:/Components_stock";
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

    @GetMapping("/TraitComponents_stock/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Components_stock currentComponents_stock = Components_stock.getById(id ,con);
            model.addAttribute("currentComponents_stock", currentComponents_stock);
            model.addAttribute("all", Components_stock.getAll());
            Providers[] allProver = Providers.getAll();
            model.addAttribute("allProver", allProver);
            Components[] allComponent = Components.getAll();
            model.addAttribute("allComponent", allComponent);
            return "Components_stock";
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
