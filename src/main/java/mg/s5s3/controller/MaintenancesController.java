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
public class MaintenancesController {

    @GetMapping("/Maintenances")
    public String showAll(Model model) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("service_id", "0");
            model.addAttribute("components_type_id", "0");
            model.addAttribute("machines_type_id", "0");
            model.addAttribute("end_date", "-");
            model.addAttribute("all", Maintenances.getAll());
            Technicians[] allTechnician = Technicians.getAll();
            model.addAttribute("allTechnician", allTechnician);
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Status[] allStatus = Status.getAll();
            model.addAttribute("allStatus", allStatus);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Machines_type[] allMachine_type = Machines_type.getAll();
            model.addAttribute("allMachine_type", allMachine_type);
            return "Maintenances";
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
    @GetMapping("/Maintenances/filter/{machines_type_id}/{service_id}/{components_type_id}/{end_date}")
    public String showAll(Model model,@PathVariable int machines_type_id,@PathVariable int service_id,@PathVariable int components_type_id, @PathVariable String end_date) {
        System.out.println(machines_type_id+"-"+service_id+"-"+components_type_id);
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("service_id", Integer.toString(service_id));
            model.addAttribute("components_type_id", Integer.toString(components_type_id));
            model.addAttribute("machines_type_id", Integer.toString(machines_type_id));
            model.addAttribute("end_date", end_date);
            model.addAttribute("all", Maintenances.getAllByCriteria(machines_type_id,service_id,components_type_id,end_date));
            Technicians[] allTechnician = Technicians.getAll();
            model.addAttribute("allTechnician", allTechnician);
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Status[] allStatus = Status.getAll();
            model.addAttribute("allStatus", allStatus);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Machines_type[] allMachine_type = Machines_type.getAll();
            model.addAttribute("allMachine_type", allMachine_type);
            return "Maintenances";
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

    @PostMapping("/Maintenances/filter")
    public String showAllPost(Model model,@RequestParam int machines_type_id,@RequestParam int service_id,@RequestParam int components_type_id, @RequestParam String end_date) {
        System.out.println(machines_type_id+"-"+service_id+"-"+components_type_id);
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("service_id", Integer.toString(service_id));
            model.addAttribute("components_type_id", Integer.toString(components_type_id));
            model.addAttribute("machines_type_id", Integer.toString(machines_type_id));
            model.addAttribute("end_date", end_date);
            model.addAttribute("all", Maintenances.getAllByCriteria(machines_type_id,service_id,components_type_id,end_date));
            Technicians[] allTechnician = Technicians.getAll();
            model.addAttribute("allTechnician", allTechnician);
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Status[] allStatus = Status.getAll();
            model.addAttribute("allStatus", allStatus);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Machines_type[] allMachine_type = Machines_type.getAll();
            model.addAttribute("allMachine_type", allMachine_type);
            return "Maintenances";
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


    @GetMapping("/Maintenances/{id}")
    public String showAll(Model model,@PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            model.addAttribute("all", Maintenances.getAll());
            Technicians[] allTechnician = Technicians.getAll();
            model.addAttribute("allTechnician", allTechnician);
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Status[] allStatus = Status.getAll();
            model.addAttribute("allStatus", allStatus);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            return "Maintenances";
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

    @PostMapping("/Maintenances")
    public String saveOrUpdate(Model model, @RequestParam(required = false) String id,  @RequestParam String price, @RequestParam String start_date, @RequestParam String end_date, @RequestParam String technician, @RequestParam String deposit, @RequestParam String status, @RequestParam(required = false) String mode) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Maintenances instance = new Maintenances();
            instance.setPrice(price) ; 
            instance.setStart_date(start_date) ; 
            instance.setEnd_date(end_date) ; 
            instance.setTechnician(technician,con ) ;
            instance.setDeposit(deposit,con ) ;
            instance.setStatus(status,con ) ;
            if (mode != null && "u".equals(mode)) {
                instance.setId(id);
                instance.update(con);
            } else {
                instance.insert(con);
            }
            return "redirect:/Maintenances";
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

    @GetMapping("/Maintenances/delete/{id}")
    public String delete(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            Maintenances.deleteById(id);
            return "redirect:/Maintenances";
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

    @GetMapping("/TraitMaintenances/{id}")
    public String editForm(Model model, @PathVariable int id) {
        Connection con = null;
        try {
            con = Database.getConnection();
            Maintenances currentMaintenances = Maintenances.getById(id ,con);
            model.addAttribute("currentMaintenances", currentMaintenances);

            model.addAttribute("service_id", "0");
            model.addAttribute("components_type_id", "0");
            model.addAttribute("machines_type_id", "0");
            model.addAttribute("end_date", "-");
            model.addAttribute("all", Maintenances.getAll());
            Technicians[] allTechnician = Technicians.getAll();
            model.addAttribute("allTechnician", allTechnician);
            Machines_clients_deposits[] allDeposit = Machines_clients_deposits.getAll();
            model.addAttribute("allDeposit", allDeposit);
            Status[] allStatus = Status.getAll();
            model.addAttribute("allStatus", allStatus);
            Components_type[] allComponent_type = Components_type.getAll();
            model.addAttribute("allComponent_type", allComponent_type);
            Services[] allService = Services.getAll();
            model.addAttribute("allService", allService);
            Machines_type[] allMachine_type = Machines_type.getAll();
            model.addAttribute("allMachine_type", allMachine_type);
            
            return "Maintenances";
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
