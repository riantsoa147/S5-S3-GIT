package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Config {
    private int id;
    private double maximum;
    public Config(){}
    public Config(String maximum,Connection con) throws Exception{
        setMaximum(maximum); 
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

    public double getMaximum() {
        return maximum;
    }

    public void setMaximum(double maximum) throws Exception {
        Util.verifyNumericPostive(maximum, "maximum");
        this.maximum = maximum;
    }

    public void setMaximum(String maximum) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(maximum);

        setMaximum(toSet) ;
    }

    public static Config getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Config instance = null;

        try {
            String query = "SELECT * FROM config WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Config();
                instance.setId(rs.getInt("id"));
                instance.setMaximum(rs.getDouble("maximum"));
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
    public static Config[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Config> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM config order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Config item = new Config();
                item.setId(rs.getInt("id"));
                item.setMaximum(rs.getDouble("maximum"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Config[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO config (maximum) VALUES (?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDouble(1, this.maximum);
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
            String query = "UPDATE config SET maximum = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDouble(1, this.maximum);
            st.setInt(2, this.getId());
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
            String query = "DELETE FROM config WHERE id = ?";
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