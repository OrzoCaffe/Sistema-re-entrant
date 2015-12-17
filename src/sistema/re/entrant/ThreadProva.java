package sistema.re.entrant;

import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ThreadProva implements Runnable {

    @Override
    public void run() {
        stampa();
    }

    public synchronized void stampa() {
        List<PersonaDTO> tabella;
        tabella = leggiTabella();
//        Thread t = Thread.currentThread();

        for (PersonaDTO personaDTO : tabella) {
            System.out.println(personaDTO.getNome());
        }
        System.out.println("THREAD CONCLUSO");

//        catch (InterruptedException | SQLException ex) {
//            Logger.getLogger(ThreadProva.class.getName()).log(Level.SEVERE, null, ex);   
//        }
    }

    public List<PersonaDTO> leggiTabella() {

        List<PersonaDTO> list = new ArrayList<>();
        String JDBC = "com.mysql.jdbc.Driver";
        String DB_URL = "jdbc:mysql://localhost/dbprova";
        try {
            Class.forName(JDBC); //carica la classe del driver
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ThreadProva.class.getName()).log(Level.SEVERE, null, ex);
        }

        try (Connection connessione = DriverManager.getConnection(DB_URL, "root", "xtphere");
                Statement stat = connessione.createStatement();
                ResultSet res = stat.executeQuery("SELECT Nome FROM persona");) {

            while (res.next()) {
                PersonaDTO persona = new PersonaDTO();
                persona.setNome(res.getString("Nome"));
                list.add(persona);
            }
            //stabilisce la connessione
//            connessione = DriverManager.getConnection(DB_URL, "root", "xtphere");
            //Statement per il db
//            stat = connessione.createStatement();
            //interroga il db in base alla query da effettuare
        } catch (SQLException sqlex) {
            sqlex.printStackTrace();
            System.exit(1);

        }
        return list;
    }
}
