package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Failing_components {
    private int id;
    private Services service;
    private Components_type component_type;
    private Diagnostics diagnostic;
    public Failing_components(){}
    public Failing_components(String service,String component_type,String diagnostic,Connection con) throws Exception{
        setService(service,con); 
        setComponent_type(component_type,con); 
        setDiagnostic(diagnostic,con); 
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

    public Diagnostics getDiagnostic() {
        return diagnostic;
    }

    public void setDiagnostic(Diagnostics diagnostic) throws Exception {
        this.diagnostic = diagnostic;
    }

    public void setDiagnostic(String diagnostic,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Diagnostics
        Diagnostics toSet = Diagnostics.getById(Integer.parseInt(diagnostic),con );

        setDiagnostic(toSet) ;
    }

    public static Failing_components getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Failing_components instance = null;

        try {
            String query = "SELECT * FROM failing_components WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Failing_components();
                instance.setId(rs.getInt("id"));
                instance.setService(Services.getById(rs.getInt("service_id") ,con ));
                instance.setComponent_type(Components_type.getById(rs.getInt("component_type_id") ,con ));
                instance.setDiagnostic(Diagnostics.getById(rs.getInt("diagnostic_id") ,con ));
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
    public static Failing_components[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Failing_components> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM failing_components order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Failing_components item = new Failing_components();
                item.setId(rs.getInt("id"));
                item.setService(Services.getById(rs.getInt("service_id")  ,con ));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setDiagnostic(Diagnostics.getById(rs.getInt("diagnostic_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Failing_components[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO failing_components (service_id, component_type_id, diagnostic_id) VALUES (?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setInt(1, this.service.getId());
            st.setInt(2, this.component_type.getId());
            st.setInt(3, this.diagnostic.getId());
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
            String query = "UPDATE failing_components SET service_id = ?, component_type_id = ?, diagnostic_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt (1, this.service.getId());
            st.setInt (2, this.component_type.getId());
            st.setInt (3, this.diagnostic.getId());
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
            String query = "DELETE FROM failing_components WHERE id = ?";
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

    public static Failing_components[] getAllByDiagnostic(Diagnostics diagnostics, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Failing_components> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM failing_components where diagnostic_id = ? order by id asc ";
            st = con.prepareStatement(query);
            st.setInt(1, diagnostics.getId());
            rs = st.executeQuery();

            while (rs.next()) {
                Failing_components item = new Failing_components();
                item.setId(rs.getInt("id"));
                item.setService(Services.getById(rs.getInt("service_id")  ,con ));
                item.setComponent_type(Components_type.getById(rs.getInt("component_type_id")  ,con ));
                item.setDiagnostic(Diagnostics.getById(rs.getInt("diagnostic_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
        }

        return items.toArray(new Failing_components[0]);
    }
}

// Commun'IT app