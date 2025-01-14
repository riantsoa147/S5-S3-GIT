package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Maintenances {
    private int id;
    private double price;
    private java.sql.Timestamp start_date;
    private java.sql.Timestamp end_date;
    private Technicians technician;
    private Machines_clients_deposits deposit;
    private Status status;
    private Maintenances_details[] details;
    
    public Maintenances(){}
    public Maintenances(String price,String start_date,String end_date,String technician,String deposit,String status,Connection con) throws Exception{
        setPrice(price); 
        setStart_date(start_date); 
        setEnd_date(end_date); 
        setTechnician(technician,con); 
        setDeposit(deposit,con); 
        setStatus(status,con); 
        setDetails(con);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) throws Exception {
        Util.verifyNumericPostive(id, "id");
        this.id = id;
    }

    public void setId(String id) throws Exception {
        int toSet =  Util.convertIntFromHtmlInput(id);

        setId(toSet) ;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws Exception {
        Util.verifyNumericPostive(price, "price");
        this.price = price;
    }

    public void setPrice(String price) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(price);

        setPrice(toSet) ;
    }

    public java.sql.Timestamp getStart_date() {
        return start_date;
    }

    public void setStart_date(java.sql.Timestamp start_date) throws Exception {
        Util.verifyObjectNotNull(start_date, "start_date");
        this.start_date = start_date;
    }

    public void setStart_date(String start_date) throws Exception {
        java.sql.Timestamp toSet =  Util.convertTimestampFromHtmlInput(start_date);

        setStart_date(toSet) ;
    }

    public java.sql.Timestamp getEnd_date() {
        return end_date;
    }

    public void setEnd_date(java.sql.Timestamp end_date) throws Exception {
        Util.verifyObjectNotNull(end_date, "end_date");
        this.end_date = end_date;
    }

    public void setEnd_date(String end_date) throws Exception {
        java.sql.Timestamp toSet =  Util.convertTimestampFromHtmlInput(end_date);

        setEnd_date(toSet) ;
    }

    public Technicians getTechnician() {
        return technician;
    }

    public void setTechnician(Technicians technician) throws Exception {
        this.technician = technician;
    }

    public void setTechnician(String technician,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Technicians
        Technicians toSet = Technicians.getById(Integer.parseInt(technician),con );

        setTechnician(toSet) ;
    }

    public Machines_clients_deposits getDeposit() {
        return deposit;
    }

    public void setDeposit(Machines_clients_deposits deposit) throws Exception {
        this.deposit = deposit;
    }

    public void setDeposit(String deposit,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Machines_clients_deposits
        Machines_clients_deposits toSet = Machines_clients_deposits.getById(Integer.parseInt(deposit),con );

        setDeposit(toSet) ;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) throws Exception {
        this.status = status;
    }

    public void setStatus(String status,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Status
        Status toSet = Status.getById(Integer.parseInt(status),con );

        setStatus(toSet) ;
    }

    public Maintenances_details[] getDetails() {
        return details;
    }
    public void setDetails(Maintenances_details[] details) {
        this.details = details;
    }

    public void setDetails(Connection con) throws Exception {
        Maintenances_details[] toSet = Maintenances_details.getAllByMaintenances(this,con);

        setDetails(toSet);
    }
    public void setDetails(int service_id , int component_type_id,Connection con) throws Exception {
        Maintenances_details[] toSet = Maintenances_details.getAllByCriteria(this,service_id,component_type_id,con);

        setDetails(toSet);
    }

    public static Maintenances getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Maintenances instance = null;

        try {
            String query = "SELECT * FROM maintenances WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Maintenances();
                instance.setId(rs.getInt("id"));
                instance.setPrice(rs.getDouble("price"));
                instance.setStart_date(rs.getTimestamp("start_date"));
                instance.setEnd_date(rs.getTimestamp("end_date"));
                instance.setTechnician(Technicians.getById(rs.getInt("technician_id") ,con ));
                instance.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id") ,con ));
                instance.setStatus(Status.getById(rs.getInt("status_id") ,con ));
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !true) con.close();
        }

        return instance;
    }
    public static Maintenances[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Maintenances> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM maintenances order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Maintenances item = new Maintenances();
                item.setId(rs.getInt("id"));
                item.setPrice(rs.getDouble("price"));
                item.setStart_date(rs.getTimestamp("start_date"));
                item.setEnd_date(rs.getTimestamp("end_date"));
                item.setTechnician(Technicians.getById(rs.getInt("technician_id")  ,con ));
                item.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id")  ,con ));
                item.setStatus(Status.getById(rs.getInt("status_id")  ,con ));
                item.setDetails(con);
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Maintenances[0]);
    }
    
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO maintenances (price, start_date, end_date, technician_id, deposit_id, status_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDouble(1, this.price);
            st.setTimestamp(2, this.start_date);
            st.setTimestamp(3, this.end_date);
            st.setInt(4, this.technician.getId());
            st.setInt(5, this.deposit.getId());
            st.setInt(6, this.status.getId());
            try {
                rs = st.executeQuery();
                if (rs.next()) {
                    int generatedId = rs.getInt("id");
                    this.setId(generatedId); 
                    con.commit();
                    return generatedId;
                } else {
                    con.rollback();
                    throw new Exception("Failed to retrieve generated ID");
                }
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to insert record", e);
            }
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }
    }
    public void update(Connection con) throws Exception {
        PreparedStatement st = null;
        try {
            String query = "UPDATE maintenances SET price = ?, start_date = ?, end_date = ?, technician_id = ?, deposit_id = ?, status_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDouble(1, this.price);
            st.setTimestamp(2, this.start_date);
            st.setTimestamp(3, this.end_date);
            st.setInt (4, this.technician.getId());
            st.setInt (5, this.deposit.getId());
            st.setInt (6, this.status.getId());
            st.setInt(7, this.getId());
            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to update record", e);
            }
        } finally {
            if (st != null) st.close();
        }
    }
    public static void deleteById(int id) throws Exception {
            Connection con = Database.getConnection();
        PreparedStatement st = null;
        try {
            String query = "DELETE FROM maintenances WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            try {
                st.executeUpdate();
                con.commit();
            } catch (Exception e) {
                con.rollback();
                throw new Exception("Failed to delete record", e);
            }
        } finally {
            if (st != null) st.close();
           if (con != null) con.close(); 
        }
    }

    public static Maintenances[] getAllByCriteria(int machines_type_id, int service_id, int components_type_id) throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Maintenances> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM maintenances where 1=1 ";
            if(machines_type_id !=0){
                query+= "and deposit_id in (select id from machines_clients_deposits where models_id in (select id from models where machine_type_id = ?) ) ";
            }
            if (service_id!=0){
                query+="and id in (select maintenance_id from maintenances_details where service_id = ? ";
                if(components_type_id !=0 ){
                    query += "and component_type_id = ? ";
                }
                query+= ")";
            }
            else if (components_type_id!=0){
                query+="and id in (select maintenance_id from maintenances_details where component_type_id = ? )";
            }
            query+="order by id asc";
            st = con.prepareStatement(query);
            int i = 1;
            if(machines_type_id !=0 ){
                st.setInt(i, machines_type_id);
                i++;
            }
            if(service_id !=0 ){
                st.setInt(i, service_id);
                i++;
            }
            if(components_type_id !=0 ){
                st.setInt(i, components_type_id);
            }
            rs = st.executeQuery();
            while (rs.next()) {
                Maintenances item = new Maintenances();
                item.setId(rs.getInt("id"));
                item.setPrice(rs.getDouble("price"));
                item.setStart_date(rs.getTimestamp("start_date"));
                item.setEnd_date(rs.getTimestamp("end_date"));
                item.setTechnician(Technicians.getById(rs.getInt("technician_id")  ,con ));
                item.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id")  ,con ));
                item.setStatus(Status.getById(rs.getInt("status_id")  ,con ));
                item.setDetails(service_id,components_type_id,con);
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Maintenances[0]);
    }
    
}

// Commun'IT app