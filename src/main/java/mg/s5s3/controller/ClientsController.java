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
public class ClientsController {

    @GetMapping("/Clients")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Clients.getAll());
            return "Clients";
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

    @PostMapping("/Clients")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String email, @RequestParam String phone_number, @RequestParam String name, @RequestParam String location, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Clients instance = new Clients();
            instance.setEmail(email) ; 
            instance.setPhone_number(phone_number) ; 
            instance.setName(name) ; 
            instance.setLocation(location) ; 
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Clients";
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

    @GetMapping("/Clients/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Clients.deleteById(id);
            return "redirect:/Clients";
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

    @GetMapping("/TraitClients/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Clients currentClients = Clients.getById(id ,con);
            model.addAttribute("currentClients", currentClients);
            model.addAttribute("all", Clients.getAll());
            return "Clients";
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
