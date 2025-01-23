package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.util.*;

public class Technician_commissions {
    private java.sql.Date maintenance_date;
    private Maintenances maintenance;
    private Technicians technician;
    private double total_maintenance_earnings;
    private double technician_commission;

    public java.sql.Date getMaintenance_date() {
        return maintenance_date;
    }

    public Maintenances getMaintenance() {
        return maintenance;
    }

    public Technicians getTechnician() {
        return technician;
    }

    public double getTotal_maintenance_earnings() {
        return total_maintenance_earnings;
    }

    public double getTechnician_commission() {
        return technician_commission;
    }

    public static List<Technician_commissions> getAll(Connection connection) throws Exception {
        List<Technician_commissions> results = new ArrayList<>();
        String query = "SELECT * FROM technician_commissions";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Technician_commissions item = new Technician_commissions();
                item.maintenance_date = rs.getObject("maintenance_date", java.sql.Date.class);
                item.maintenance = Maintenances.getById(rs.getInt("maintenance_id"),connection);
                item.technician = Technicians.getById(rs.getInt("technician_id"),connection);
                item.total_maintenance_earnings = rs.getDouble("total_maintenance_earnings");
                item.technician_commission = rs.getDouble("technician_commission");
                results.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<Technician_commissions> getAllByCriteria(Connection connection, String start_date, String end_date, Integer technician_id) throws Exception {
        List<Technician_commissions> results = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM technician_commissions WHERE 1=1");

        if (start_date != null && !start_date.isEmpty()) {
            query.append(" AND maintenance_date >= ?");
        }
        if (end_date != null && !end_date.isEmpty()) {
            query.append(" AND maintenance_date <= ?");
        }
        if (technician_id != 0) {
            query.append(" AND technician_id = ?");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (start_date != null && !start_date.isEmpty()) {
                pstmt.setDate(index++, Util.convertDateFromHtmlInput((start_date)));
            }
            if (end_date != null && !end_date.isEmpty()) {
                pstmt.setDate(index++, Util.convertDateFromHtmlInput((end_date)));
            }
            if (technician_id != 0) {
                pstmt.setInt(index++, technician_id);
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Technician_commissions item = new Technician_commissions();
                    item.maintenance_date = rs.getObject("maintenance_date", java.sql.Date.class);
                    item.maintenance = Maintenances.getById(rs.getInt("maintenance_id"), connection);
                    item.technician = Technicians.getById(rs.getInt("technician_id"), connection);
                    item.total_maintenance_earnings = rs.getDouble("total_maintenance_earnings");
                    item.technician_commission = rs.getDouble("technician_commission");
                    results.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}