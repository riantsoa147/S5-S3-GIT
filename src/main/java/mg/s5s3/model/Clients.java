package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Clients {
    private int id;
    private String email;
    private String phone_number;
    private String name;
    private String location;
    public Clients(){}
    public Clients(String email,String phone_number,String name,String location,Connection con) throws Exception{
        setEmail(email); 
        setPhone_number(phone_number); 
        setName(name); 
        setLocation(location); 
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws Exception {
        Util.verifyStringNotNullOrEmpty(email, "email");
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) throws Exception {
        Util.verifyStringNotNullOrEmpty(phone_number, "phone_number");
        this.phone_number = phone_number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        Util.verifyStringNotNullOrEmpty(name, "name");
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) throws Exception {
        Util.verifyStringNotNullOrEmpty(location, "location");
        this.location = location;
    }

    public static Clients getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Clients instance = null;

        try {
            String query = "SELECT * FROM clients WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Clients();
                instance.setId(rs.getInt("id"));
                instance.setEmail(rs.getString("email"));
                instance.setPhone_number(rs.getString("phone_number"));
                instance.setName(rs.getString("name"));
                instance.setLocation(rs.getString("location"));
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
    public static Clients[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Clients> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM clients order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Clients item = new Clients();
                item.setId(rs.getInt("id"));
                item.setEmail(rs.getString("email"));
                item.setPhone_number(rs.getString("phone_number"));
                item.setName(rs.getString("name"));
                item.setLocation(rs.getString("location"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Clients[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO clients (email, phone_number, name, location) VALUES (?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.email);
            st.setString(2, this.phone_number);
            st.setString(3, this.name);
            st.setString(4, this.location);
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
            String query = "UPDATE clients SET email = ?, phone_number = ?, name = ?, location = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.email);
            st.setString(2, this.phone_number);
            st.setString(3, this.name);
            st.setString(4, this.location);
            st.setInt(5, this.getId());
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
            String query = "DELETE FROM clients WHERE id = ?";
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