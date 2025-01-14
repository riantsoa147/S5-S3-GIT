package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Components_stock {
    private int id;
    private double entrance;
    private double outflow;
    private java.sql.Date stock_date;
    private double unit_price;
    private Providers prover;
    private Components component;
    public Components_stock(){}
    public Components_stock(String entrance,String outflow,String stock_date,String unit_price,String prover,String component,Connection con) throws Exception{
        setEntrance(entrance); 
        setOutflow(outflow); 
        setStock_date(stock_date); 
        setUnit_price(unit_price); 
        setProver(prover,con); 
        setComponent(component,con); 
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

    public double getEntrance() {
        return entrance;
    }

    public void setEntrance(double entrance) throws Exception {
        Util.verifyNumericPostive(entrance, "entrance");
        this.entrance = entrance;
    }

    public void setEntrance(String entrance) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(entrance);

        setEntrance(toSet) ;
    }

    public double getOutflow() {
        return outflow;
    }

    public void setOutflow(double outflow) throws Exception {
        Util.verifyNumericPostive(outflow, "outflow");
        this.outflow = outflow;
    }

    public void setOutflow(String outflow) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(outflow);

        setOutflow(toSet) ;
    }

    public java.sql.Date getStock_date() {
        return stock_date;
    }

    public void setStock_date(java.sql.Date stock_date) throws Exception {
        Util.verifyObjectNotNull(stock_date, "stock_date");
        this.stock_date = stock_date;
    }

    public void setStock_date(String stock_date) throws Exception {
        java.sql.Date toSet =  Util.convertDateFromHtmlInput(stock_date);

        setStock_date(toSet) ;
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

    public Providers getProver() {
        return prover;
    }

    public void setProver(Providers prover) throws Exception {
        this.prover = prover;
    }

    public void setProver(String prover,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Providers
        Providers toSet = Providers.getById(Integer.parseInt(prover),con );

        setProver(toSet) ;
    }

    public Components getComponent() {
        return component;
    }

    public void setComponent(Components component) throws Exception {
        this.component = component;
    }

    public void setComponent(String component,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Components
        Components toSet = Components.getById(Integer.parseInt(component),con );

        setComponent(toSet) ;
    }

    public static Components_stock getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Components_stock instance = null;

        try {
            String query = "SELECT * FROM components_stock WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Components_stock();
                instance.setId(rs.getInt("id"));
                instance.setEntrance(rs.getDouble("entrance"));
                instance.setOutflow(rs.getDouble("outflow"));
                instance.setStock_date(rs.getDate("stock_date"));
                instance.setUnit_price(rs.getDouble("unit_price"));
                instance.setProver(Providers.getById(rs.getInt("provider_id") ,con ));
                instance.setComponent(Components.getById(rs.getInt("component_id") ,con ));
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
    public static Components_stock[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Components_stock> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM components_stock order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Components_stock item = new Components_stock();
                item.setId(rs.getInt("id"));
                item.setEntrance(rs.getDouble("entrance"));
                item.setOutflow(rs.getDouble("outflow"));
                item.setStock_date(rs.getDate("stock_date"));
                item.setUnit_price(rs.getDouble("unit_price"));
                item.setProver(Providers.getById(rs.getInt("provider_id")  ,con ));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Components_stock[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO components_stock (entrance, outflow, stock_date, unit_price, provider_id, component_id) VALUES (?, ?, ?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDouble(1, this.entrance);
            st.setDouble(2, this.outflow);
            st.setDate(3, this.stock_date);
            st.setDouble(4, this.unit_price);
            st.setInt(5, this.prover.getId());
            st.setInt(6, this.component.getId());
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
            String query = "UPDATE components_stock SET entrance = ?, outflow = ?, stock_date = ?, unit_price = ?, provider_id = ?, component_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDouble(1, this.entrance);
            st.setDouble(2, this.outflow);
            st.setDate(3, this.stock_date);
            st.setDouble(4, this.unit_price);
            st.setInt (5, this.prover.getId());
            st.setInt (6, this.component.getId());
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
            String query = "DELETE FROM components_stock WHERE id = ?";
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