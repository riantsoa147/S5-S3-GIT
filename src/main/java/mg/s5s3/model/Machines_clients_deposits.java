package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Machines_clients_deposits {
    private int id;
    private java.sql.Date deposit_date;
    private Dimensions dimension;
    private Models models;
    private Clients client;
    public Machines_clients_deposits(){}
    public Machines_clients_deposits(String deposit_date,String dimension,String models,String client,Connection con) throws Exception{
        setDeposit_date(deposit_date); 
        setDimension(dimension,con); 
        setModels(models,con); 
        setClient(client,con); 
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

    public java.sql.Date getDeposit_date() {
        return deposit_date;
    }

    public void setDeposit_date(java.sql.Date deposit_date) throws Exception {
        Util.verifyObjectNotNull(deposit_date, "deposit_date");
        this.deposit_date = deposit_date;
    }

    public void setDeposit_date(String deposit_date) throws Exception {
        java.sql.Date toSet =  Util.convertDateFromHtmlInput(deposit_date);

        setDeposit_date(toSet) ;
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

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) throws Exception {
        this.client = client;
    }

    public void setClient(String client,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Clients
        Clients toSet = Clients.getById(Integer.parseInt(client),con );

        setClient(toSet) ;
    }

    public static Machines_clients_deposits getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Machines_clients_deposits instance = null;

        try {
            String query = "SELECT * FROM machines_clients_deposits WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Machines_clients_deposits();
                instance.setId(rs.getInt("id"));
                instance.setDeposit_date(rs.getDate("deposit_date"));
                instance.setDimension(Dimensions.getById(rs.getInt("dimension_id") ,con ));
                instance.setModels(Models.getById(rs.getInt("models_id") ,con ));
                instance.setClient(Clients.getById(rs.getInt("client_id") ,con ));
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
    public static Machines_clients_deposits[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Machines_clients_deposits> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM machines_clients_deposits order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Machines_clients_deposits item = new Machines_clients_deposits();
                item.setId(rs.getInt("id"));
                item.setDeposit_date(rs.getDate("deposit_date"));
                item.setDimension(Dimensions.getById(rs.getInt("dimension_id")  ,con ));
                item.setModels(Models.getById(rs.getInt("models_id")  ,con ));
                item.setClient(Clients.getById(rs.getInt("client_id")  ,con ));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Machines_clients_deposits[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO machines_clients_deposits (deposit_date, dimension_id, models_id, client_id) VALUES (?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setDate(1, this.deposit_date);
            st.setInt(2, this.dimension.getId());
            st.setInt(3, this.models.getId());
            st.setInt(4, this.client.getId());
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
            String query = "UPDATE machines_clients_deposits SET deposit_date = ?, dimension_id = ?, models_id = ?, client_id = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setDate(1, this.deposit_date);
            st.setInt (2, this.dimension.getId());
            st.setInt (3, this.models.getId());
            st.setInt (4, this.client.getId());
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
            String query = "DELETE FROM machines_clients_deposits WHERE id = ?";
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