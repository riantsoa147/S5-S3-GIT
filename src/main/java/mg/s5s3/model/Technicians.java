package mg.s5s3.model;
import java.sql.*;
import java.util.*;
import mg.s5s3.db.Database;
import mg.s5s3.util.*;
public class Technicians {
    private int id;
    private String name;
    private double salary;
    private Gender gender;
    private int commission;
    public Technicians(){}
    public Technicians(String name,String salary,String gender,String commission,Connection con) throws Exception{
        setName(name); 
        setSalary(salary); 
        setGender(gender,con); 
        setCommission(commission); 
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

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws Exception {
        Util.verifyNumericPostive(salary, "salary");
        this.salary = salary;
    }

    public void setSalary(String salary) throws Exception {
        double toSet =  Util.convertDoubleFromHtmlInput(salary);

        setSalary(toSet) ;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) throws Exception {
        this.gender = gender;
    }

    public void setGender(String gender,Connection con) throws Exception {
         //define how this type should be conterted from String ... type : Gender
        Gender toSet = Gender.getById(Integer.parseInt(gender),con );

        setGender(toSet) ;
    }

    public int getCommission() {
        return commission;
    }

    public void setCommission(int commission) throws Exception {
        Util.verifyNumericPostive(commission, "commission");
        this.commission = commission;
    }

    public void setCommission(String commission) throws Exception {
        int toSet =  Util.convertIntFromHtmlInput(commission);

        setCommission(toSet) ;
    }

    public static Technicians getById(int id, Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        Technicians instance = null;

        try {
            String query = "SELECT * FROM technicians WHERE id = ?";
            st = con.prepareStatement(query);
            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                instance = new Technicians();
                instance.setId(rs.getInt("id"));
                instance.setName(rs.getString("name"));
                instance.setSalary(rs.getDouble("salary"));
                instance.setGender(Gender.getById(rs.getInt("gender_id") ,con ));
                instance.setCommission(rs.getInt("commission"));
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
    public static Technicians[] getAll() throws Exception {
        Connection con = Database.getConnection();
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Technicians> items = new ArrayList<>();

        try {
            String query = "SELECT * FROM technicians order by id asc ";
            st = con.prepareStatement(query);
            rs = st.executeQuery();

            while (rs.next()) {
                Technicians item = new Technicians();
                item.setId(rs.getInt("id"));
                item.setName(rs.getString("name"));
                item.setSalary(rs.getDouble("salary"));
                item.setGender(Gender.getById(rs.getInt("gender_id")  ,con ));
                item.setCommission(rs.getInt("commission"));
                items.add(item);
            }
        } catch (Exception e) {
            throw e ;
        } finally {
            if (rs != null) rs.close();
            if (st != null) st.close();
            if (con != null && !false) con.close();
        }

        return items.toArray(new Technicians[0]);
    }
    public int insert(Connection con) throws Exception {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            String query = "INSERT INTO technicians (name, salary, gender_id, commission) VALUES (?, ?, ?, ?) RETURNING id";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
            st.setDouble(2, this.salary);
            st.setInt(3, this.gender.getId());
            st.setInt(4, this.commission);
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
            String query = "UPDATE technicians SET name = ?, salary = ?, gender_id = ?, commission = ? WHERE id = ?";
            st = con.prepareStatement(query);
            st.setString(1, this.name);
            st.setDouble(2, this.salary);
            st.setInt (3, this.gender.getId());
            st.setInt(4, this.commission);
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
            String query = "DELETE FROM technicians WHERE id = ?";
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