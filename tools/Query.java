package tools;

//import tools.com.mysql.cj.*;
import java.sql.*;
import java.util.ArrayList;

/**
 * 
 * This class is made of useful complex SQL queries for the program 
 * @author Dejan PARIS
 * @author Thomas LEPERCQ 
 * 
 */
public class Query{

    private static String url       = "jdbc:mysql://localhost:3306/PPDBDD";
    private static String user      = "test";
    private static String password  = "azerty"; 

    /**
    * Returns the user's ID given its login.
    * @author Dejan PARIS 
    * @param String login User's login
    * @return User's ID
    */
    public static int getUserID(String login){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idUtilisateur FROM Utilisateur WHERE login = "+login+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idUtilisateur");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    /**
    * Returns the student's ID given its login.
    * @author Thomas LEPERCQ 
    * @param String loginEtu Student's login
    * @return Student's ID
    */
    public static int getStudentID(String loginEtu){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idEtudiant FROM Etudiant JOIN Utilisateur ON Etudiant.idUtilisateur = Utilisateur.idUtilisateur WHERE login = "+loginEtu+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idEtudiant");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    /**
    * Returns the teacher's ID given its login.
    * @author Dejan PARIS 
    * @param String loginEns Teacher's login
    * @return Teacher's ID
    */
    public static int getTeacherID(String loginEns){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idEnseignant FROM Enseignant JOIN Utilisateur ON Enseignant.idUtilisateur = Utilisateur.idUtilisateur WHERE login = "+loginEns+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idEnseignant");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    /**
    * Returns the TU's ID given its name.
    * @author Thomas LEPERCQ 
    * @param String tu TU's name
    * @return TU's ID
    */
    public static int getTUID(String tu){
        Connection conn = null;
        int id = -1;
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT idUE FROM UE WHERE nomUE = "+tu+";";
            ResultSet res = statement.executeQuery(query);
            id = res.getInt("idUE");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return id;
    }

    /**
    * Returns user's information given its login.
    * @author Dejan PARIS
    * @param String login User's login
    * @return an Array with user's information (login, password, name, firstname and role)
    */
    public static ArrayList<Object> userData(String login){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Utilisateur WHERE idUtilisateur = "+getUserID(login)+";";
            ResultSet res = statement.executeQuery(query);
            queryResult.add(res.getString("login"));
            queryResult.add(res.getString("mdp"));
            queryResult.add(res.getString("nom"));
            queryResult.add(res.getString("prenom"));
            queryResult.add(res.getString("role"));

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns student's information given its login.
    * @author Dejan PARIS
    * @param String loginEtu Student's login
    * @return an Array with student's information
    */
    public static ArrayList<Object> studentData(String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM Etudiant WHERE idEtudiant = "+getStudentID(loginEtu)+";";
            ResultSet res = statement.executeQuery(query);
            queryResult.add(res.getString("aideAuJury"));
            queryResult.add(res.getInt("TP"));
            queryResult.add(res.getInt("TD"));
            queryResult.add(res.getString("filiere"));

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns module's information given its name.
    * @author Dejan PARIS
    * @param String loginEtu Student's login
    * @return an Array with the name of TU and the coefficient of the module in this TU.
    */
    public static ArrayList<Object> coeffInTU(String moduleName){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomUE, coeff FROM Module JOIN Constitue ON Module.idModule = Constitue.idModule JOIN UE ON UE.idUE = Constitue.idUE WHERE nomModule = "+moduleName+";";
            ResultSet res = statement.executeQuery(query);
            queryResult.add(res.getString("nomUE"));
            queryResult.add(res.getInt("coeff"));

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns each evaluations' name, mark and coefficient for a given module and student.
    * @author Thomas LEPERCQ 
    * @param String moduleName Module's name
    * @param String loginEtu Student's login
    * @return an Array of evaluations with Arrays for their names, marks and coefficient 
    */
    public static ArrayList<Object> exams(String moduleName, String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        int id = getStudentID(loginEtu); 
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomNote, note, coefficient FROM Note WHERE nomModule ="+moduleName+" AND idEtudiant = "+id+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<String> nomNote = new ArrayList<String>();
            ArrayList<Integer> note = new ArrayList<Integer>();
            ArrayList<Integer> coefficient = new ArrayList<Integer>();
            while(res.next()){
                nomNote.add(res.getString("nomNote"));
                note.add(res.getInt("note"));
                coefficient.add(res.getInt("coefficient"));
            }
            queryResult.add(nomNote);
            queryResult.add(note);
            queryResult.add(coefficient);

        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns average mark for a given module and student.
    * @author Thomas LEPERCQ 
    * @param String moduleName Module's name
    * @param String loginEtu Student's login
    * @return an Array with the average mark
    */
    public static ArrayList<Float> studentAverage(String moduleName, String loginEtu){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        int id = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE nomModule = "+moduleName+" AND idEtudiant = "+id+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }  
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    /**
    * Returns mark for a given evaluation and student.
    * @author Thomas LEPERCQ 
    * @param String nomNote Evaluation's name
    * @param String loginEtu Student's login
    * @return an Array with the mark.
    */
    public static ArrayList<Integer> mark(String markName, String loginEtu){
        Connection conn = null;
        ArrayList<Integer> mark = new ArrayList<Integer>();
        int id = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT note FROM Note WHERE nomNote = "+markName+" AND idEtudiant = "+id+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                mark.add(res.getInt("note"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }           
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return mark;
    }

    /**
    * Returns all attended modules for a given student.
    * @author Thomas LEPERCQ
    * @param String loginEtu Student's login
    * @return an Array with the courses names.
    */
    public static ArrayList<String> courses(String loginEtu){
        Connection conn = null;
        ArrayList<String> courses = new ArrayList<String>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT DISTINCT nomModule FROM Module WHERE idModule IN (SELECT idModule FROM Assiste WHERE idEtudiant = "+idEtudiant+");";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                courses.add(res.getString("note"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }   
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return courses;
    }

    /**
    * Returns all attended TUs for a given student.
    * @author Thomas LEPERCQ
    * @param String loginEtu Student's login
    * @return an Array with the TUs names.
    */
    public static ArrayList<String> attendedTUs(String loginEtu){
        Connection conn = null;
        ArrayList<String> tu = new ArrayList<String>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT DISTINCT (nomUE) FROM UE WHERE idUE IN ( SELECT idUE FROM Constitue WHERE nomModule IN ( SELECT nomModule FROM Assiste WHERE idEtudiant = "+idEtudiant+" ) );";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                tu.add(res.getString("UE"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return tu;
    }

    /**
    * Returns all missed classes for a given student.
    * @author Thomas LEPERCQ 
    * @param String loginEtu Student's login
    * @return an Array of absences with Arrays for their dates, hours and justification state.
    */
    public static ArrayList<Object> absence(String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        int idEtudiant = getStudentID(loginEtu); 
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT dateDebut, heureDebut, dateFin, heureFin, estJustifiee FROM absences WHERE idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<Date> dateDebut = new ArrayList<Date>();
            ArrayList<Time> heureDebut = new ArrayList<Time>();
            ArrayList<Date> dateFin = new ArrayList<Date>();
            ArrayList<Time> heureFin = new ArrayList<Time>();
            ArrayList<Boolean> estJustifiee = new ArrayList<Boolean>();
            while(res.next()){
                dateDebut.add(res.getDate("dateDebut"));
                heureDebut.add(res.getTime("heureDebut"));
                dateFin.add(res.getDate("dateFin"));
                heureFin.add(res.getTime("heureFin"));
                estJustifiee.add(res.getBoolean("estJustifiee"));
            }
            queryResult.add(dateDebut);
            queryResult.add(heureDebut);
            queryResult.add(dateFin);
            queryResult.add(heureFin);
            queryResult.add(estJustifiee);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }  
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns mark of each student for a given evaluation.
    * @author Thomas LEPERCQ 
    * @param String nomNote Evaluation's name
    * @return an Array of results with Arrays for the name and firstname of the student
    * and Arrays for the mark and coefficient associated with the student.
    */
    public static ArrayList<Object> results(String nomNote){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nom, prenom, note, coefficient FROM Note, Utilisateur JOIN Etudiant ON Etudiant.idEtudiant=Note.idEtudiant AND Etudiant.idUtilisateur=Utilisateur.idUtilisateur WHERE nomNote = "+nomNote+";";
            ResultSet res = statement.executeQuery(query);
            ArrayList<String> nom = new ArrayList<String>();
            ArrayList<String> prenom = new ArrayList<String>();
            ArrayList<Integer> note = new ArrayList<Integer>();
            ArrayList<Integer> coefficient = new ArrayList<Integer>();
            while(res.next()){
                nom.add(res.getString("nom"));
                prenom.add(res.getString("prenom"));
                note.add(res.getInt("note"));
                coefficient.add(res.getInt("coefficient"));
            }
            queryResult.add(nom);
            queryResult.add(prenom);
            queryResult.add(note);
            queryResult.add(coefficient);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }    
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns average mark for a given module.
    * @author Thomas LEPERCQ 
    * @param String moduleName Module's name
    * @return an Array with the average mark.
    */
    public static ArrayList<Float> courseAverage(String moduleName){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE nomModule = "+moduleName+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
                
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    /**
    * Returns average mark for a given evaluation.
    * @author Thomas LEPERCQ 
    * @param String markName Evaluation's name
    * @param String moduleName Module's name
    * @return an Array with the average mark.
    */
    public static ArrayList<Float> examAverage(String markName, String moduleName){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT SUM(note*coefficient)/SUM(coefficient) AS average FROM Note WHERE nomNote = "+markName+" AND nomModule = "+moduleName+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    /**
    * Returns average mark of satisfaction for a given module.
    * @author Thomas LEPERCQ 
    * @param String moduleName Module's name
    * @return an Array with the average mark.
    */
    public static ArrayList<Float> satisfactionAverage(String moduleName){
        Connection conn = null;
        ArrayList<Float> average = new ArrayList<Float>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT AVG(noteSatisfaction) as average FROM Satisfaction WHERE nomModule = "+moduleName+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                average.add(res.getFloat("average"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                } 
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return average;
    }

    /**
    * Returns all courses taught by a given teacher.
    * @author Dejan PARIS 
    * @param String loginEns Teacher's login
    * @return an Array the courses names.
    */
    public static ArrayList<Object> coursesTaught(String loginEns){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomModule FROM Enseigne JOIN Module ON Enseigne.idModule = Module.idModule WHERE idEnseignant = "+getTeacherID(loginEns)+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                queryResult.add(res.getString("nomModule"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }  
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns all students attending a given course.
    * @author Dejan PARIS 
    * @param String moduleName Module's name
    * @return an Array with the login of the students.
    */
    public static ArrayList<Object> attendees(String moduleName){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT login FROM Etudiant JOIN Utilisateur ON Etudiant.idUtilisateur = Utilisateur.idUtilisateur JOIN Assiste ON Assiste.idEtudiant = Etudiant.idEtudiant WHERE nomModule = "+moduleName+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                queryResult.add(res.getString("login"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }   
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }
    
    /**
    * Returns average mark for each course attended by a given student.
    * @author Dejan PARIS 
    * @param String loginEtu Student's login
    * @return an Array with Arrays for the modules' names and the average mark associated with it.
    */
    public static ArrayList<Object> studentModulesAverage(String loginEtu){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<Object>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nomModule, SUM(note*coefficient)/SUM(coefficient) AS s FROM Note GROUP BY idModule HAVING idEtudiant = "+idEtudiant+";";
            ArrayList<String> nomModule = new ArrayList<String>();
            ArrayList<Float> average = new ArrayList<Float>();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                nomModule.add(res.getString("nomModule"));
                average.add(res.getFloat("s"));
            }
            queryResult.add(nomModule);
            queryResult.add(average);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }
    
    /**
    * Returns name and average mark for all student attending a given module.
    * @author Dejan PARIS
    * @param String moduleName Module's name
    * @return an Array with Arrays for the students' name and firstname
    * and the average mark associated with him.
    */
    public static ArrayList<Object> moduleStudentsAverage(String moduleName){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<Object>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nom, prenom, SUM(note*coefficient)/SUM(coefficient) AS s FROM Note JOIN Etudiant ON Note.idEtudiant = Etudiant.idEtudiant GROUP BY Etudiant.idEtudiant HAVING nomModule = "+moduleName+";";
            ArrayList<String> nom = new ArrayList<String>();
            ArrayList<String> prenom = new ArrayList<String>();
            ArrayList<Float> average = new ArrayList<Float>();
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                nom.add(res.getString("nom"));
                prenom.add(res.getString("prenom"));
                average.add(res.getFloat("s"));
            }
            queryResult.add(nom);
            queryResult.add(prenom);
            queryResult.add(average);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                }   
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns all unjustified absences for everyone.
    * @author Dejan PARIS
    * @return an Array of absences with Arrays for the name and firstname of the student and
    * dates and hours of the absence associated with him.
    */
    public static ArrayList<Object> unjustified(){
        Connection conn = null;
        ArrayList<Object> queryResult = new ArrayList<>();
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT nom, prenom, dateDebut, heureDebut, dateFin, heureFin FROM Absence JOIN Etudiant ON Absence.idEtudiant = Etudiant.idEtudiant WHERE estJustifiee = false;";
            ResultSet res = statement.executeQuery(query);
            ArrayList<String> nom = new ArrayList<String>();
            ArrayList<String> prenom = new ArrayList<String>();
            ArrayList<Date> dateDebut = new ArrayList<Date>();
            ArrayList<Integer> heureDebut = new ArrayList<Integer>();
            ArrayList<Date> dateFin = new ArrayList<Date>();
            ArrayList<Integer> heureFin = new ArrayList<Integer>();
            while(res.next()){
                nom.add(res.getString("nom"));
                prenom.add(res.getString("prenom"));
                dateDebut.add(res.getDate("dateDebut"));
                heureDebut.add(res.getInt("heureDebut"));
                dateFin.add(res.getDate("dateFin"));
                heureFin.add(res.getInt("heureFin"));
            }
            queryResult.add(nom);
            queryResult.add(prenom);
            queryResult.add(dateDebut);
            queryResult.add(heureDebut);
            queryResult.add(dateFin);
            queryResult.add(heureFin);
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                } 
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return queryResult;
    }

    /**
    * Returns automatically generated answer wether a given student should double this year, invalidate it or pass it.
    * @author Thomas LEPERCQ
    * @param String loginEtu Student's login
    * @return an Array with the automatically generated answer.
    */
    public static ArrayList<String> juryHelper(String loginEtu){
        Connection conn = null;
        ArrayList<String> juryHelper = new ArrayList<String>();
        int idEtudiant = getStudentID(loginEtu);
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT aideAuJury FROM Etudiant WHERE idEtudiant = "+idEtudiant+";";
            ResultSet res = statement.executeQuery(query);
            while(res.next()){
                juryHelper.add(res.getString("aideAuJury"));
            }
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                } 
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return juryHelper;
    }

    /**
    * Returns the course concerned by the TU.
    * @author Dejan PARIS
    * @return an Array with the course.
    */
    public static String courseOfTU(String tu){
        Connection conn = null;
        String course = "";
        try {
            // create a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(url, user, password);
            Statement statement = conn.createStatement();
            String query = "SELECT filiere FROM UE WHERE idUE = "+getTUID(tu)+";";
            ResultSet res = statement.executeQuery(query);
            course = res.getString("filiere");
        } catch(SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try{
                if(conn != null){
                    conn.close();
                } 
            }catch(SQLException ex){
                ex.printStackTrace();
            }
        }
        return course;
    }

}