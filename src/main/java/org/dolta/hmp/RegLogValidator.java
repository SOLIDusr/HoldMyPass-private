package org.dolta.hmp;

import org.dolta.hmp.utils.Log;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import static org.dolta.hmp.utils.DataBaseConnection.db;


public class RegLogValidator {

    public static boolean emailValid(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }

    public static int passwordValid(String password) {
        if (password == null) {
            return 1;
        }
        if (password.length() > 16 || password.length() < 8) {
            return 2;
        }
        if (!(password.contains("@") || password.contains("#")
                || password.contains("!") || password.contains("~")
                || password.contains("$") || password.contains("%")
                || password.contains("^") || password.contains("&")
                || password.contains("*") || password.contains("(")
                || password.contains(")") || password.contains("-")
                || password.contains("+") || password.contains("/")
                || password.contains(":") || password.contains(".")
                || password.contains(", ") || password.contains("<")
                || password.contains(">") || password.contains("?")
                || password.contains("|"))) {
            return 3;
        }
        if (password.contains(" ")) {
            return 4;
        }
        int count = 0;

        for (int i = 0; i <= 9; i++) {

            // to convert int to string
            String str1 = Integer.toString(i);

            if (password.contains(str1)) {
                count = 1;
                break;
            }
        }
        if (count == 0) {
            return 5;
        }
        {

            for (int i = 65; i <= 90; i++) {

                char c = (char) i;
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    return 0;
                }
            }
        }
        {
            for (int i = 90; i <= 122; i++) {

                char c = (char) i;
                String str1 = Character.toString(c);
                if (password.contains(str1)) {
                    return 0;
                }
            }
        }
        return 0;
    }

    protected static String[] getValidData(String providedLogin, String providedPassword) throws SQLException {
        Statement st = db.createStatement();
        String query = "SELECT * FROM user_data WHERE login = '" + providedLogin + "'";
        ResultSet rs = st.executeQuery(query);
        if (!rs.next()) {
            return new String[]{"None", "None"};
        }
        String login = rs.getString("login");
        String password = rs.getString("password");
        rs.close();
        st.close();
        return new String[]{login, password};
    }

    protected static boolean getValidMail(String providedMail) throws SQLException {
        Statement st = db.createStatement();
        String query = "SELECT email FROM user_data WHERE email ="+providedMail;
        ResultSet rs = st.executeQuery(query);
        return rs.next();
    }

    protected static boolean loginProvider(String loginProvided, String passwordProvided) throws SQLException {
        String[] response = getValidData(loginProvided, passwordProvided);
        String login = response[0];
        String password = response[1];
        return login.equals(loginProvided) & password.equals(passwordProvided);
    }

    protected static boolean regProvider(String loginProvided, String passwordProvided, String mailProvided) throws SQLException {
        if (getValidMail(mailProvided)){
            return true;
        }
        return false;
    }
}

