package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Diagnostics {
    private int id;
    private java.sql.Date diagnostics_date;
    private Machines_clients_deposits deposit;
    private Failing_components[] components;

    public Diagnostics(){}
    public Diagnostics(String diagnostics_date,String deposit,Connection con) throws Exception{
        setDiagnostics_date(diagnostics_date); 
        setDeposit(deposit,con); 
        setComponents(con);

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

    public java.sql.Date getDiagnostics_date() {
        return diagnostics_date;
    }

    public void setDiagnostics_date(java.sql.Date diagnostics_date) throws Exception {
        Util.verifyObjectNotNull(diagnostics_date, "diagnostics_date");
        this.diagnostics_date = diagnostics_date;
    }

    public void setDiagnostics_date(String diagnostics_date) throws Exception {
        java.sql.Date toSet =  Util.convertDateFromHtmlInput(diagnostics_date);

        setDiagnostics_date(toSet) ;
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

    public Failing_components[] getComponents() {
        return components;
    }
    public void setComponents(Failing_components[] components) {
        this.components = components;
    }

    public void setComponents(Connection con) throws Exception {
        Failing_components [] toSet = Failing_components.getAllByDiagnostic(this, con);

        setComponents(toSet);
    }

    public void setComponents(int component_type_id, Connection con) throws Exception {
        Failing_components [] toSet = Failing_components.getAllByDiagnostic(this,component_type_id, con);

        setComponents(toSet);
    }

    public static Diagnostics getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Diagnostics instance = null;

        try {
            String query = "SELECT * FROM diagnostics WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Diagnostics();
                instance.setId(rs.getInt("id"));
                instance.setDiagnostics_date(rs.getDate("diagnostics_date"));
                instance.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id") ,con ));
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
    public static Diagnostics[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Diagnostics> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM diagnostics order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Diagnostics item = new Diagnostics();
                item.setId(rs.getInt("id"));
                item.setDiagnostics_date(rs.getDate("diagnostics_date"));
                item.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id")  ,con ));
                item.setComponents(con);
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Diagnostics[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO diagnostics (diagnostics_date, deposit_id) VALUES (?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDate(1, this.diagnostics_date);
            st.setInt(2, this.deposit.getId());
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
            String query = "UPDATE diagnostics SET diagnostics_date = ?, deposit_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDate(1, this.diagnostics_date);
            st.setInt (2, this.deposit.getId());
            st.setInt(3, this.getId());
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
            String query = "DELETE FROM diagnostics WHERE id = ?";
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
    public static Diagnostics[] getAllByComponent(int id) throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Diagnostics> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM diagnostics where id in (select diagnostic_id from failing_components where component_type_id = ? ) order by id asc ";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            while (rs.next()) {
                Diagnostics item = new Diagnostics();
                item.setId(rs.getInt("id"));
                item.setDiagnostics_date(rs.getDate("diagnostics_date"));
                item.setDeposit(Machines_clients_deposits.getById(rs.getInt("deposit_id")  ,con ));
                item.setComponents(id,con);
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Diagnostics[0]);
    }
}

// Commun'IT app