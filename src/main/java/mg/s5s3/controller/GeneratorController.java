package mg.s5s3.controller;
import mg.s5s3.model.*;

import java.sql.Connection;
import java.sql.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class GeneratorController {

    @GetMapping("/")
    public String sayHello() {
        return "index";
    }
    @GetMapping("/Technician_commissions")
    public String getTechnician_commissions(Model model) {
        try {
            Connection con = mg.s5s3.db.Database.getConnection();
            model.addAttribute("start_date", "");
            model.addAttribute("end_date", "");
            model.addAttribute("Technician_commissions", Technician_commissions.getAll(con));
            model.addAttribute("allTechnician", Technicians.getAll());
            return "Technician_commissions";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") );
            return "Error";
        }
    }

    @PostMapping("/Technician_commissions")
    public String getTechnician_commissions(Model model, @RequestParam String start_date, @RequestParam String end_date, @RequestParam int technician_id) {
        try {
            System.out.println("technician_id = "+ technician_id);
            Connection con = mg.s5s3.db.Database.getConnection();
            model.addAttribute("start_date", start_date);
            model.addAttribute("end_date", end_date);
            model.addAttribute("Technician_commissions", Technician_commissions.getAllByCriteria(con, start_date, end_date, technician_id));
            model.addAttribute("allTechnician", Technicians.getAll());
            return "Technician_commissions";
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("eMessage", e.getMessage() + (e.getCause() != null ? "<br> <hr>" + e.getCause().getMessage() : "") );
            return "Error";
        }
    }

}
