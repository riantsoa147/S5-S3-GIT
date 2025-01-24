package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.util.*;


public class Technician_gender_commission_states {
    private java.sql.Date maintenance_date;
    private double sum;
    private Gender gender;

    public java.sql.Date getMaintenance_date() {
        return maintenance_date;
    }

    public double getSum() {
        return sum;
    }

    public Gender getGender() {
        return gender;
    }

    public static List<Technician_gender_commission_states> getAll(Connection connection) throws Exception {
        List<Technician_gender_commission_states> results = new ArrayList<>();
        String query = "SELECT * FROM technician_gender_commission_states";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Technician_gender_commission_states item = new Technician_gender_commission_states();
                item.maintenance_date = rs.getObject("maintenance_date", java.sql.Date.class);
                item.sum = rs.getDouble("sum");
                item.gender = Gender.getById(rs.getInt("gender_id"), connection);
                results.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }

    public static List<Technician_gender_commission_states> getAll(Connection connection, String start_date, String end_date) throws Exception {
        List<Technician_gender_commission_states> results = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM technician_gender_commission_states WHERE 1=1");

        if (start_date != null && !start_date.isEmpty()) {
            query.append(" AND maintenance_date >= ?");
        }
        if (end_date != null && !end_date.isEmpty()) {
            query.append(" AND maintenance_date <= ?");
        }

        try (PreparedStatement pstmt = connection.prepareStatement(query.toString())) {
            int index = 1;
            if (start_date != null && !start_date.isEmpty()) {
                pstmt.setDate(index++, Util.convertDateFromHtmlInput((start_date)));
            }
            if (end_date != null && !end_date.isEmpty()) {
                pstmt.setDate(index++, Util.convertDateFromHtmlInput((end_date)));
            }

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    Technician_gender_commission_states item = new Technician_gender_commission_states();
                    item.maintenance_date = rs.getObject("maintenance_date", java.sql.Date.class);
                    item.sum = rs.getDouble("sum");
                    item.gender = Gender.getById(rs.getInt("gender_id"), connection);
                    results.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return results;
    }
}