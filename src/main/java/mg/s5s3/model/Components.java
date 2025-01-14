package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Components {
    private int id;
    private String name;
    private double sell_price;
    private double stock;
    private Components_type component_type;
    private Models models;
    private Dimensions dimension;
    public Components(){}
    public Components(String name,String sell_price,String stock,String component_type,String models,String dimension,Connection con) throws Exception{
        setName(name); 
        setSell_price(sell_price); 
        setStock(stock); 
        setComponent_type(component_type,con); 
        setModels(models,con); 
        setDimension(dimension,con); 
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

    public double getSell_price() {
        return sell_price;
    }

    public void setSell_price(double sell_price) throws Exception {
        Util.verifyNumericPostive(sell_price, "sell_price");
        this.sell_price = sell_price;
    }

    public void setSell_price(String sell_price) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(sell_price);

        setSell_price(toSet) ;
    }

    public double getStock() {
        return stock;
    }

    public void setStock(double stock) throws Exception {
        Util.verifyNumericPostive(stock, "stock");
        this.stock = stock;
    }

    public void setStock(String stock) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(stock);

        setStock(toSet) ;
    }

    public Components_type getComponent_type() {
        return component_type;
    }

    public void setComponent_type(Components_type component_type) throws Exception {
        this.component_type = component_type;
    }

    public void setComponent_type(String component_type,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Components_type
        Components_type toSet = Components_type.getById(Integer.parseInt(component_type),con );

        setComponent_type(toSet) ;
    }

    public Models getModels() {
        return models;
    }

    public void setModels(Models models) throws Exception {
        this.models = models;
    }

    public void setModels(String models,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Models
        Models toSet = Models.getById(Integer.parseInt(models),con );

        setModels(toSet) ;
    }

    public Dimensions getDimension() {
        return dimension;
    }

    public void setDimension(Dimensions dimension) throws Exception {
        this.dimension = dimension;
    }

    public void setDimension(String dimension,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Dimensions
        Dimensions toSet = Dimensions.getById(Integer.parseInt(dimension),con );

        setDimension(toSet) ;
    }

    public static Components getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Components instance = null;

        try {
            String query = "SELECT * FROM components WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Components();
                instance.setId(rs.getInt("id"));
                instance.setName(rs.getString("name"));
                instance.setSell_price(rs.getDouble("sell_price"));
                instance.setStock(rs.getDouble("stock"));
                instance.setComponent_type(Components_type.getById(rs.getInt("component_type_id") ,con ));
                instance.setModels(Models.getById(rs.getInt("models_id") ,con ));
                instance.setDimension(Dimensions.getById(rs.getInt("dimension_id") ,con ));
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
    public static Components[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Components> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM components order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Components item = new Components();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setSell_price(rs.getDouble("sell_price"));
                item.setStock(rs.getDouble("stock"));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setModels(Models.getById(rs.getInt("models_id")  ,con ));
                item.setDimension(Dimensions.getById(rs.getInt("dimension_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Components[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            if (this.dimension.getId()!=0) {
                String query = "INSERT INTO components (name, sell_price, stock, component_type_id, models_id, dimension_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
                st = con.prepareStatement(query);
                st.setString(1, this.name);
                st.setDouble(2, this.sell_price);
                st.setDouble(3, this.stock);
                st.setInt(4, this.component_type.getId());
                st.setInt(5, this.models.getId());
                st.setInt(6, this.dimension.getId());
            } else {
                String query = "INSERT INTO components (name, sell_price, stock, component_type_id, models_id) VALUES (?, ?, ?, ?, ?) RETURNING id";
                st = con.prepareStatement(query);
                st.setString(1, this.name);
                st.setDouble(2, this.sell_price);
                st.setDouble(3, this.stock);
                st.setInt(4, this.component_type.getId());
                st.setInt(5, this.models.getId());
            }
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
            String query = "UPDATE components SET name = ?, sell_price = ?, stock = ?, component_type_id = ?, models_id = ?, dimension_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
            st.setDouble(2, this.sell_price);
            st.setDouble(3, this.stock);
            st.setInt (4, this.component_type.getId());
            st.setInt (5, this.models.getId());
            st.setInt (6, this.dimension.getId());
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
            String query = "DELETE FROM components WHERE id = ?";
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

    public static Components[] getAllByMachines(Machines_clients_deposits machines, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Components> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM components where models_id = ? and (dimension_id is NULL or dimension_id = ?) order by id asc ";
            st = con.prepareStatement(query);
            st.setInt(1, machines.getModels().getId());
            st.setInt(1, machines.getDimension().getId());
            rs = st.executeQuery();

            while (rs.next()) {
                Components item = new Components();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setSell_price(rs.getDouble("sell_price"));
                item.setStock(rs.getDouble("stock"));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setModels(Models.getById(rs.getInt("models_id")  ,con ));
                item.setDimension(Dimensions.getById(rs.getInt("dimension_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Components[0]);
    }
}

// Commun'IT app