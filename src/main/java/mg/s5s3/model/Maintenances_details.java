package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Maintenances_details {
    private int id;
    private double quantity;
    private double unit_price;
    private double sell_price;
    private Components_type component_type;
    private Services service;
    private Components component;
    private Maintenances maintenance;
    public Maintenances_details(){}
    public Maintenances_details(String quantity,String unit_price,String sell_price,String component_type,String service,String component,String maintenance,Connection con) throws Exception{
        setQuantity(quantity); 
        setUnit_price(unit_price); 
        setSell_price(sell_price); 
        setComponent_type(component_type,con); 
        setService(service,con); 
        setComponent(component,con); 
        setMaintenance(maintenance,con); 
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

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) throws Exception {
        Util.verifyNumericPostive(quantity, "quantity");
        this.quantity = quantity;
    }

    public void setQuantity(String quantity) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(quantity);

        setQuantity(toSet) ;
    }

    public double getUnit_price() {
        return unit_price;
    }

    public void setUnit_price(double unit_price) throws Exception {
        Util.verifyNumericPostive(unit_price, "unit_price");
        this.unit_price = unit_price;
    }

    public void setUnit_price(String unit_price) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(unit_price);

        setUnit_price(toSet) ;
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

    public Services getService() {
        return service;
    }

    public void setService(Services service) throws Exception {
        this.service = service;
    }

    public void setService(String service,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Services
        Services toSet = Services.getById(Integer.parseInt(service),con );

        setService(toSet) ;
    }

    public Components getComponent() {
        return component;
    }

    public void setComponent(Components component) throws Exception {
        this.component = component;
    }

    public void setComponent(String component,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Components
         Components toSet = new Components();
         try {
            toSet = Components.getById(Integer.parseInt(component),con );
            
         } catch (Exception e) {}

        setComponent(toSet) ;
    }

    public Maintenances getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(Maintenances maintenance) throws Exception {
        this.maintenance = maintenance;
    }

    public void setMaintenance(String maintenance,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Maintenances
        Maintenances toSet = Maintenances.getById(Integer.parseInt(maintenance),con );

        setMaintenance(toSet) ;
    }

    public static Maintenances_details getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Maintenances_details instance = null;

        try {
            String query = "SELECT * FROM maintenances_details WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Maintenances_details();
                instance.setId(rs.getInt("id"));
                instance.setQuantity(rs.getDouble("quantity"));
                instance.setUnit_price(rs.getDouble("unit_price"));
                instance.setSell_price(rs.getDouble("sell_price"));
                instance.setComponent_type(Components_type.getById(rs.getInt("component_type_id") ,con ));
                instance.setService(Services.getById(rs.getInt("service_id") ,con ));
                instance.setComponent(Components.getById(rs.getInt("component_id") ,con ));
                instance.setMaintenance(Maintenances.getById(rs.getInt("maintenance_id") ,con ));
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
    public static Maintenances_details[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Maintenances_details> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM maintenances_details order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Maintenances_details item = new Maintenances_details();
                item.setId(rs.getInt("id"));
                item.setQuantity(rs.getDouble("quantity"));
                item.setUnit_price(rs.getDouble("unit_price"));
                item.setSell_price(rs.getDouble("sell_price"));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setService(Services.getById(rs.getInt("service_id")  ,con ));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                item.setMaintenance(Maintenances.getById(rs.getInt("maintenance_id") ,con ));

                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Maintenances_details[0]);
    }
    public void insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "select insert_maintenances_details (?,?,?,?,?)";
            st = con.prepareStatement(query);
            st.setInt(1, this.component.getId());
            st.setInt(2, this.component_type.getId());
            st.setInt(3, this.service.getId());
            st.setDouble(4, this.quantity);
            st.setInt(5, this.maintenance.getId());
            try {
                rs = st.executeQuery();
                con.commit();
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
            String query = "UPDATE maintenances_details SET quantity = ?, unit_price = ?, sell_price = ?, component_type_id = ?, service_id = ?, component_id = ?, maintenance_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDouble(1, this.quantity);
            st.setDouble(2, this.unit_price);
            st.setDouble(3, this.sell_price);
            st.setInt (4, this.component_type.getId());
            st.setInt (5, this.service.getId());
            st.setInt (6, this.component.getId());
            st.setInt (7, this.maintenance.getId());
            st.setInt(8, this.getId());
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
            String query = "DELETE FROM maintenances_details WHERE id = ?";
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

    public static Maintenances_details[] getAllByMaintenances(Maintenances maintenances, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Maintenances_details> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM maintenances_details where maintenance_id = ? order by id asc ";
            st = con.prepareStatement(query);
            st.setInt(1, maintenances.getId());
            rs = st.executeQuery();

            while (rs.next()) {
                Maintenances_details item = new Maintenances_details();
                item.setId(rs.getInt("id"));
                item.setQuantity(rs.getDouble("quantity"));
                item.setUnit_price(rs.getDouble("unit_price"));
                item.setSell_price(rs.getDouble("sell_price"));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setService(Services.getById(rs.getInt("service_id")  ,con ));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Maintenances_details[0]);
    }
    
    public static Maintenances_details[] getAllByCriteria(Maintenances maintenances, int service_id, int components_type_id,  Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Maintenances_details> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM maintenances_details where maintenance_id = ? ";
            if(service_id !=0 ){
                query += "and service_id = ? ";
            }
            if(components_type_id !=0 ){
                query += "and component_type_id = ? ";
            }
            query+="order by id asc";
            st = con.prepareStatement(query);
            st.setInt(1, maintenances.getId());
            int i = 2;
            if(service_id !=0 ){
                st.setInt(i, service_id);
                i++;
            }
            if(components_type_id !=0 ){
                st.setInt(i, components_type_id);
            }
            rs = st.executeQuery();

            while (rs.next()) {
                Maintenances_details item = new Maintenances_details();
                item.setId(rs.getInt("id"));
                item.setQuantity(rs.getDouble("quantity"));
                item.setUnit_price(rs.getDouble("unit_price"));
                item.setSell_price(rs.getDouble("sell_price"));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setService(Services.getById(rs.getInt("service_id")  ,con ));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Maintenances_details[0]);
    }
    
}

// Commun'IT app