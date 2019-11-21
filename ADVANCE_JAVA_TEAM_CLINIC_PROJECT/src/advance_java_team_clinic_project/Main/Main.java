/*
 *  Project for TEI OF CRETE lesson
 *  Plan Driven and Agile Programming
 *  TP4129 - TP4187 - TP4145
 */
package advance_java_team_clinic_project.Main;

import advance_java_team_clinic_project.Controller;
import java.sql.SQLException;


/**
 *
 * @author Beast
 */

public class Main {
 private static Controller ak;
 
    public static void main(String[] args) throws SQLException {
        ak = new Controller();
        ak.getObject();
        ak.Query();
        ak.getData();
    }

    }

