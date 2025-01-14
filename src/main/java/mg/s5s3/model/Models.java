package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Models {
    private int id;
    private String name;
    private Brands brand;
    private Machines_type machine_type;
    public Models(){}
    public Models(String name,String brand,String machine_type,Connection con) throws Exception{
        setName(name); 
        setBrand(brand,con); 
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

    public String getName() {
        return name;
    }

    public void setName(String name) throws Exception {
        Util.verifyStringNotNullOrEmpty(name, "name");
        this.name = name;
    }

    public Brands getBrand() {
        return brand;
    }

    public void setBrand(Brands brand) throws Exception {
        this.brand = brand;
    }

    public void setBrand(String brand,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Brands
        Brands toSet = Brands.getById(Integer.parseInt(brand),con );

        setBrand(toSet) ;
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

    public static Models getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Models instance = null;

        try {
            String query = "SELECT * FROM models WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Models();
                instance.setId(rs.getInt("id"));
                instance.setName(rs.getString("name"));
                instance.setBrand(Brands.getById(rs.getInt("brand_id") ,con ));
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
    public static Models[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Models> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM models order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Models item = new Models();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setBrand(Brands.getById(rs.getInt("brand_id")  ,con ));
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

        return items.toArray(new Models[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO models (name, brand_id, machine_type_id) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
            st.setInt(2, this.brand.getId());
            st.setInt(3, this.machine_type.getId());
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
            String query = "UPDATE models SET name = ?, brand_id = ?, machine_type_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
            st.setInt (2, this.brand.getId());
            st.setInt (3, this.machine_type.getId());
            st.setInt(4, this.getId());
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
            String query = "DELETE FROM models WHERE id = ?";
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