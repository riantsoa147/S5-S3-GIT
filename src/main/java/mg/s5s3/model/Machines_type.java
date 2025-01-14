package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Machines_type {
    private int id;
    private String name;
    public Machines_type(){}
    public Machines_type(String name,Connection con) throws Exception{
        setName(name); 
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        Util.verifyStringNotNullOrEmpty(name, "name");
        this.name = name;
    }

    public static Machines_type getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Machines_type instance = null;

        try {
            String query = "SELECT * FROM machines_type WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Machines_type();
                instance.setId(rs.getInt("id"));
                instance.setName(rs.getString("name"));
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
    public static Machines_type[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Machines_type> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM machines_type order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Machines_type item = new Machines_type();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Machines_type[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO machines_type (name) VALUES (?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
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
            String query = "UPDATE machines_type SET name = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
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
            String query = "DELETE FROM machines_type WHERE id = ?";
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