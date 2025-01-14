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
public class Machines_clients_depositsController {

    @GetMapping("/Machines_clients_deposits")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Machines_clients_deposits.getAll());
            Dimensions[] allDimension = Dimensions.getAll();
            model.addAttribute("allDimension", allDimension);
            Models[] allModels = Models.getAll();
            model.addAttribute("allModels", allModels);
            Clients[] allClient = Clients.getAll();
            model.addAttribute("allClient", allClient);
            return "Machines_clients_deposits";
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

    @PostMapping("/Machines_clients_deposits")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String deposit_date, @RequestParam String dimension, @RequestParam String models, @RequestParam String client, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Machines_clients_deposits instance = new Machines_clients_deposits();
            instance.setDeposit_date(deposit_date) ; 
            instance.setDimension(dimension,con ) ;
            instance.setModels(models,con ) ;
            instance.setClient(client,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Machines_clients_deposits";
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

    @GetMapping("/Machines_clients_deposits/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Machines_clients_deposits.deleteById(id);
            return "redirect:/Machines_clients_deposits";
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

    @GetMapping("/TraitMachines_clients_deposits/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Machines_clients_deposits currentMachines_clients_deposits = Machines_clients_deposits.getById(id ,con);
            model.addAttribute("currentMachines_clients_deposits", currentMachines_clients_deposits);
            model.addAttribute("all", Machines_clients_deposits.getAll());
            Dimensions[] allDimension = Dimensions.getAll();
            model.addAttribute("allDimension", allDimension);
            Models[] allModels = Models.getAll();
            model.addAttribute("allModels", allModels);
            Clients[] allClient = Clients.getAll();
            model.addAttribute("allClient", allClient);
            return "Machines_clients_deposits";
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
