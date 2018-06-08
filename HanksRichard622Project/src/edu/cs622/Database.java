package edu.cs622;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;



public class Database {
	
	public static void main(String[] args){
		Database db = new Database();
		db.incrementScoreForUser("test user2", "Compound Scalar Intervals Up");
		Map<String, Integer> results = db.getAllScoresForUser("test user2");
		System.out.println(results);
	}
    private String framework = "embedded";
    private String protocol = "jdbc:derby:";
    Connection conn = null;
    ArrayList<Statement> statements = new ArrayList<Statement>(); // list of Statements, PreparedStatements
    PreparedStatement psInsert;
    PreparedStatement psUpdate;
    Statement s;
    ResultSet rs = null;
    private Properties props;
    private String dbName;
    
    public Database(){
    	this.props = new Properties();
    	props.put("user",  "user1");
    	props.put("password",  "user1");
    	this.dbName = "derbyDB";
    }
    
    /**
     * Calls method to create a table using the appropriate SQL for the table we need
     */
    public void createTable(){
    	this.createOrDropTable("create table user_score (username varchar(40) not null, primary key(username))");
    	this.createOrDropTable("create table score (question_key varchar(40) not null, total int default 0,"
    			+ " username varchar(40) not null, primary key(question_key, username),"
    			+ "foreign key (username) references user_score(username))") ;
        System.out.println("Table successfully created");
    }
    
    /**
     * Calls method to drop a table using the appriate SQL for the table we need
     */
    public void dropTable(){
    	this.createOrDropTable("drop table user_score");
    	this.createOrDropTable("drop table score");
        System.out.println("Tables successfully dropped");
    }
    
    /**
     * Called to create a new table using DDL SQL found in createTableStatement
     * @param createTableStatement the DDL
     */
    
    
    public boolean checkIfUserExists(String username){
    	ResultSet rs = null;
        try{
        		this.conn = DriverManager.getConnection(protocol + dbName
    		        + ";create=true", props);
                conn.setAutoCommit(false);
                PreparedStatement psQuery = conn.prepareStatement("select count(*) from user_score where username = ?");
                // Get the column names
                psQuery.setString(1, username);
                rs = psQuery.executeQuery();
                rs.next();
                int countForUser = rs.getInt(1);
                if (countForUser > 0){
                	return true;
                }
                // Need to put a commit here to release the read lock.
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        return false;

    }
    /**
     * Create a new user and set scores to 0 using the given username as the primary key
     */
    public void createNewUser(String username){
        try {
    			this.conn = DriverManager.getConnection(protocol + dbName
    			        + ";create=true", props);
                conn.setAutoCommit(false);
    			Statement s = conn.createStatement();
    			PreparedStatement psInsert = conn.prepareStatement("insert into user_score (username) values(?)");
    			statements.add(psInsert);
    			psInsert.setString(1,  username);
    			psInsert.executeUpdate();
    			// List of all available question keys for use in populating the relation tables
    			List<String> questionKeyNames = new ArrayList<String>(Arrays.asList("Compound Scalar Intervals Down", "Compound Scalar Intervals Up",
    					"Simple Scale Chords", "Simple Interval", "Simple Clef Question", "Clef Interval Question"));
    			PreparedStatement scoresPsInsert;
    			for(String questionName:questionKeyNames){
        			scoresPsInsert = conn.prepareStatement("insert into score(question_key, username) values (?,?)");
        			scoresPsInsert.setString(1,  questionName);
        			scoresPsInsert.setString(2,  username);
        			scoresPsInsert.executeUpdate();
    			}
    			conn.commit();
    			System.out.println("User inserted successfully");
                conn.commit();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}finally {
                // release all open resources to avoid unnecessary memory usage



                // Statements and PreparedStatements
                int i = 0;
                while (!statements.isEmpty()) {
                    // PreparedStatement extend Statement
                    Statement st = (Statement)statements.remove(i);
                    try {
                        if (st != null) {
                            st.close();
                            st = null;
                        }
                    } catch (SQLException sqle) {
                        System.out.println(sqle);
                    }
                }

                //Connection
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (SQLException sqle) {
                    System.out.println(sqle);
                }
            }
    }
    
    /**
     * Increments a score for a user
     * @param username the primary identifier for a user
     * @param questionTypeKey the key associated with the question
     */
    public void incrementScoreForUser(String username, String questionTypeKey){
        try {
    			this.conn = DriverManager.getConnection(protocol + dbName
    			        + ";create=true", props);
                conn.setAutoCommit(false);
    			Statement s = conn.createStatement();
    			PreparedStatement psInsert = conn.prepareStatement("update score set total = total + 1 where username = ? and question_key = ?");
    			statements.add(psInsert);
    			psInsert.setString(1,  username);
    			psInsert.setString(2,  questionTypeKey);
    			psInsert.executeUpdate();
    			conn.commit();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}finally {
                // release all open resources to avoid unnecessary memory usage

                // Statements and PreparedStatements
                int i = 0;
                while (!statements.isEmpty()) {
                    // PreparedStatement extend Statement
                    Statement st = (Statement)statements.remove(i);
                    try {
                        if (st != null) {
                            st.close();
                            st = null;
                        }
                    } catch (SQLException sqle) {
                        System.out.println(sqle);
                    }
                }

                //Connection
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (SQLException sqle) {
                    System.out.println(sqle);
                }
            }
    }
    /**
     * Get all the scores for a given username
     * @return a Map of the user's scores where the key is the type of question and the value is the current score
     * @param username the identifier used 
     */
    public Map<String, Integer> getAllScoresForUser(String username){
    	ResultSet rs = null;
    	Map<String, Integer> scoresMap = new HashMap<>();
        try{
        		this.conn = DriverManager.getConnection(protocol + dbName
    		        + ";create=true", props);
                conn.setAutoCommit(false);
                PreparedStatement psQuery = conn.prepareStatement("select score.question_key, score.total from score left outer join user_score on score.username = user_score.username where user_score.username = ?");
                // Get the column names
                psQuery.setString(1, username);
                rs = psQuery.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
                int numColumnsInResults = meta.getColumnCount();
                while(rs.next()){
                	for(int i=2; i<=numColumnsInResults; i++){
                		scoresMap.put(rs.getString(1), rs.getInt(2));
                	}
                }
                // Need to put a commit here to release the read lock.
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
		return scoresMap;
        
    }
    
    public void createOrDropTable(String createTableStatement){
        try {
    			this.conn = DriverManager.getConnection(protocol + dbName
    			        + ";create=true", props);
                conn.setAutoCommit(false);
    			Statement s = conn.createStatement();
                statements.add(s);
                s.execute(createTableStatement);
                conn.commit();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}finally {
                // release all open resources to avoid unnecessary memory usage


                // Statements and PreparedStatements
                int i = 0;
                while (!statements.isEmpty()) {
                    // PreparedStatement extend Statement
                    Statement st = (Statement)statements.remove(i);
                    try {
                        if (st != null) {
                            st.close();
                            st = null;
                        }
                    } catch (SQLException sqle) {
                        System.out.println(sqle);
                    }
                }

                //Connection
                try {
                    if (conn != null) {
                        conn.close();
                        conn = null;
                    }
                } catch (SQLException sqle) {
                    System.out.println(sqle);
                }
            }
    }
}
