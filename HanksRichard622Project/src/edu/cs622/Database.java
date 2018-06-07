package edu.cs622;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;



public class Database {
	
	public static void main(String[] args){
		Database db = new Database();
//		db.dropTable();
//		db.createTable();
//		db.createNewUser("test user2");
		db.incrementScoreForUser("test user2", "\"Compound Scalar Intervals Up\"");
		Map<String, Integer> results = db.getAllScoresForUser("test user2");
//		try {
//			while(results.next()){
//					System.out.println(results.getString(1) + ": " + results.getString(2));
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		
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
//    	try {
//			this.conn = DriverManager.getConnection(protocol + dbName
//			        + ";create=true", props);
//            conn.setAutoCommit(false);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
    }
    
    /**
     * Calls method to create a table using the appropriate SQL for the table we need
     */
    public void createTable(){
    	this.createOrDropTable("create table user_scores(username varchar(40) not null, \"Compound Scalar Intervals Down\" int default 0,"
    			+ "\"Compound Scalar Intervals Up\" int default 0, \"Simple Scale Chords\" int default 0, \"Simple Interval\" int default 0, \"Simple Clef Question\" int default 0,"
    			+ "\"Clef Interval Question\" int default 0, primary key(username))");
        System.out.println("Table successfully created");
    }
    
    /**
     * Calls method to drop a table using the appriate SQL for the table we need
     */
    public void dropTable(){
    	this.createOrDropTable("drop table user_scores");
        System.out.println("Table successfully dropped");
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
                PreparedStatement psQuery = conn.prepareStatement("select count(*) from user_scores where username = ?");
                // Get the column names
                psQuery.setString(1, username);
                rs = psQuery.executeQuery();
                rs.next();
                int countForUser = rs.getInt(1);
                if (countForUser > 0){
                	return true;
                }
                // Need to put a commit here to release the read lock.
//                conn.commit();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		//} finally{
//                    // release all open resources to avoid unnecessary memory usage
//
//                    // Statements and PreparedStatements
//                    int i = 0;
//                    while (!statements.isEmpty()) {
//                        // PreparedStatement extend Statement
//                        Statement st = (Statement)statements.remove(i);
//                        try {
//                            if (st != null) {
//                                st.close();
//                                st = null;
//                            }
//                        } catch (SQLException sqle) {
//                        	System.out.println("212");
//                            System.out.println(sqle);
//                        }
//                    }

                    //Connection
//                    try {
//                        if (conn != null) {
//                            conn.close();
//                            conn = null;
//                        }
//                    } catch (SQLException sqle) {
//                    	System.out.println("224");
//                        System.out.println(sqle);
//                    }
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
    			PreparedStatement psInsert = conn.prepareStatement("insert into user_scores (username) values(?)");
    			statements.add(psInsert);
    			psInsert.setString(1,  username);
//    			psInsert.setInt(2,  0);
    			psInsert.executeUpdate();
    			conn.commit();
//                statements.add(s);
//                s.execute(createTableStatement);
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
    			PreparedStatement psInsert = conn.prepareStatement("update user_scores set " + questionTypeKey + " = " + questionTypeKey + " + 1 where username = ?");
    			statements.add(psInsert);
//    			psInsert.setString(1,  questionTypeKey);
    			psInsert.setString(1,  username);
    			psInsert.executeUpdate();
    			conn.commit();
//                statements.add(s);
//                s.execute(createTableStatement);
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
                PreparedStatement psQuery = conn.prepareStatement("select * from user_scores where username = ?");
                // Get the column names
//                ResultSetMetaData meta = psQuery.getMetaData();
//               int numColumnsInResults = meta.getColumnCount();
                psQuery.setString(1, username);
                rs = psQuery.executeQuery();
                ResultSetMetaData meta = rs.getMetaData();
//                System.out.println(meta);
                int numColumnsInResults = meta.getColumnCount();
//                System.out.println("num columns: " + numColumnsInResults);
//                System.out.println("1: " + meta.getColumnName(1));
//                System.out.println("2: " + meta.getColumnName(2));

                while(rs.next()){
                	for(int i=2; i<=numColumnsInResults; i++){
                		scoresMap.put(meta.getColumnName(i), rs.getInt(i));
                	}
                }
                System.out.println(scoresMap);
                // Need to put a commit here to release the read lock.
//                conn.commit();
    		} catch (SQLException e) {
    			e.printStackTrace();
    		//} finally{
//                    // release all open resources to avoid unnecessary memory usage
//
//                    // Statements and PreparedStatements
//                    int i = 0;
//                    while (!statements.isEmpty()) {
//                        // PreparedStatement extend Statement
//                        Statement st = (Statement)statements.remove(i);
//                        try {
//                            if (st != null) {
//                                st.close();
//                                st = null;
//                            }
//                        } catch (SQLException sqle) {
//                        	System.out.println("212");
//                            System.out.println(sqle);
//                        }
//                    }

                    //Connection
//                    try {
//                        if (conn != null) {
//                            conn.close();
//                            conn = null;
//                        }
//                    } catch (SQLException sqle) {
//                    	System.out.println("224");
//                        System.out.println(sqle);
//                    }
    		}
        System.out.println(scoresMap);
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
