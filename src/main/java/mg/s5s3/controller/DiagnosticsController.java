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
public class DiagnosticsController {

    @GetMapping("/Diagnostics")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Diagnostics.getAll());
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Diagnostics";
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

    @GetMapping("/Diagnostics/{id}")
    public String showAll(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Diagnostics.getAllByComponent(id));
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Diagnostics";
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

    @PostMapping("/Diagnostics")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String diagnostics_date, @RequestParam String deposit, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Diagnostics instance = new Diagnostics();
            instance.setDiagnostics_date(diagnostics_date) ; 
            instance.setDeposit(deposit,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Diagnostics";
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

    @GetMapping("/Diagnostics/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Diagnostics.deleteById(id);
            return "redirect:/Diagnostics";
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

    @GetMapping("/TraitDiagnostics/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Diagnostics currentDiagnostics = Diagnostics.getById(id ,con);
            model.addAttribute("currentDiagnostics", currentDiagnostics);
            model.addAttribute("all", Diagnostics.getAll());
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            return "Diagnostics";
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
