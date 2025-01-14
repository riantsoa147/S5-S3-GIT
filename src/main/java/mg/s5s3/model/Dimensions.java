package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Dimensions {
    private int id;
    private double inch;
    private Machines_type machine_type;
    public Dimensions(){}
    public Dimensions(String inch,String machine_type,Connection con) throws Exception{
        setInch(inch); 
        setMachine_type(machine_type,con); 
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

    public double getInch() {
        return inch;
    }

    public void setInch(double inch) throws Exception {
        Util.verifyNumericPostive(inch, "inch");
        this.inch = inch;
    }

    public void setInch(String inch) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(inch);

        setInch(toSet) ;
    }

    public Machines_type getMachine_type() {
        return machine_type;
    }

    public void setMachine_type(Machines_type machine_type) throws Exception {
        this.machine_type = machine_type;
    }

    public void setMachine_type(String machine_type,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Machines_type
        Machines_type toSet = Machines_type.getById(Integer.parseInt(machine_type),con );

        setMachine_type(toSet) ;
    }

    public static Dimensions getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Dimensions instance = null;

        try {
            String query = "SELECT * FROM dimensions WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Dimensions();
                instance.setId(rs.getInt("id"));
                instance.setInch(rs.getDouble("inch"));
                instance.setMachine_type(Machines_type.getById(rs.getInt("machine_type_id") ,con ));
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
    public static Dimensions[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Dimensions> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM dimensions order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Dimensions item = new Dimensions();
                item.setId(rs.getInt("id"));
                item.setInch(rs.getDouble("inch"));
                item.setMachine_type(Machines_type.getById(rs.getInt("machine_type_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Dimensions[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO dimensions (inch, machine_type_id) VALUES (?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDouble(1, this.inch);
            st.setInt(2, this.machine_type.getId());
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
            String query = "UPDATE dimensions SET inch = ?, machine_type_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDouble(1, this.inch);
            st.setInt (2, this.machine_type.getId());
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
            String query = "DELETE FROM dimensions WHERE id = ?";
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
}

// Commun'IT app