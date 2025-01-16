package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Components_recommandations {
    private int id;
    private java.sql.Date date_recommandation;
    private Components component;
    private int recommandations;

    public Components_recommandations(){}
    public Components_recommandations(String date_recommandation,String component,Connection con) throws Exception{
        setDate_recommandation(date_recommandation); 
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

    public java.sql.Date getDate_recommandation() {
        return date_recommandation;
    }

    public void setDate_recommandation(java.sql.Date date_recommandation) throws Exception {
        
        // Util.verifyObjectNotNull(date_recommandation, "date_recommandation");
        this.date_recommandation = date_recommandation;
    }

    public void setDate_recommandation(String date_recommandation) throws Exception {
        java.sql.Date toSet =  Util.convertDateFromHtmlInput(date_recommandation);

        setDate_recommandation(toSet) ;
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

    public int getRecommandations() {
        return recommandations;
    }
    public void setRecommandations(int recommandations) {
        this.recommandations = recommandations;
    }

    public static Components_recommandations getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Components_recommandations instance = null;

        try {
            String query = "SELECT * FROM components_recommandations WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Components_recommandations();
                instance.setId(rs.getInt("id"));
                instance.setDate_recommandation(rs.getDate("date_recommandation"));
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

    public static Components_recommandations[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Components_recommandations> items = new ArrayList<>();

        try {
            String query = "SELECT NULL as id, NULL as date_recommandation, component_id, COUNT(id) AS recommandations FROM components_recommandations WHERE DATE_PART('year', date_recommandation) = 2025 GROUP BY component_id ORDER BY recommandations DESC";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Components_recommandations item = new Components_recommandations();
                item.setId(rs.getInt("id"));
                item.setDate_recommandation(rs.getDate("date_recommandation"));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                item.setRecommandations(rs.getInt("recommandations"));

                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Components_recommandations[0]);
    }

    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO components_recommandations (date_recommandation, component_id) VALUES (?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDate(1, this.date_recommandation);
            st.setInt(2, this.component.getId());
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
            String query = "UPDATE components_recommandations SET date_recommandation = ?, component_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDate(1, this.date_recommandation);
            st.setInt (2, this.component.getId());
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
            String query = "DELETE FROM components_recommandations WHERE id = ?";
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

    public static Components_recommandations[] getAllByCriteria(int year , int month, int component_type_id) throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Components_recommandations> items = new ArrayList<>();

        try {
            String query = "SELECT NULL as id, NULL as date_recommandation, component_id, COUNT(id) AS recommandations FROM components_recommandations WHERE 1=1 ";
            if(year !=0)
            {
                query += "AND DATE_PART('year', date_recommandation) = ? ";
            }
            if(month !=0)
            {
                query += "AND DATE_PART('month', date_recommandation) = ? ";
            }
            if(component_type_id !=0)
            {
                query += "AND component_id IN ( SELECT id FROM components WHERE component_type_id = ? ) ";
            }
            query+="GROUP BY component_id ORDER BY recommandations DESC";
                                
            st = con.prepareStatement(query);
            int i = 1; 
            if(year!=0){
                st.setInt(i, year);
                i++;
            }
            if(month!=0){
                st.setInt(i, month);
                i++;
            }
            if(component_type_id!=0){
                st.setInt(i, component_type_id);
              
            }
            rs = st.executeQuery();


            while (rs.next()) {
                Components_recommandations item = new Components_recommandations();
                item.setId(rs.getInt("id"));
                item.setDate_recommandation(rs.getDate("date_recommandation"));
                item.setComponent(Components.getById(rs.getInt("component_id")  ,con ));
                item.setRecommandations(rs.getInt("recommandations"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Components_recommandations[0]);
    }
}

// Commun'IT app