package org.hekmat;
import org.hekmat.table.Block;
import org.hekmat.table.Board;
import org.hekmat.table.Table;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Student {
    private final String DB_URL = "jdbc:mysql://199.80.55.99:3306/admin_hekmat?useLegacyDatetimeCode=false&serverTimezone=America/Los_Angeles";
    private final String DB_USER = "admin_hekmat";
    private final String DB_PASSWORD = "8d8120df";
    private Connection con;
    private Statement stmt;
    public  Student(){
        try {
            this.con = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            this.stmt = con.createStatement();
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public int AddStudent(String FirstName, String LastName){
        int student_id = 0;
        ResultSet rs;
        try {
            rs = this.stmt.executeQuery("SELECT * FROM `tblStudents` WHERE `first_name`=\""+ this.escapeString(FirstName, true) + "\" AND `last_name`=\""+ this.escapeString(LastName, true) + "\"");
            if(rs.next()) return -1;
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }

        PreparedStatement istmt = null;
        try {
            istmt = this.con.prepareStatement("INSERT INTO `tblStudents` (`first_name`, `last_name`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            istmt.setString(1, FirstName);
            istmt.setString(2, LastName);
            istmt.executeUpdate();
            rs = istmt.getGeneratedKeys();
            if(rs.next())  student_id = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (istmt != null) { istmt.close(); }
            }
            catch (Exception e) {
            }
        }

        return student_id;
    }

    public boolean DelStudent(int student_id){
        try {
            this.stmt.executeUpdate("DELETE FROM `tblStudents` WHERE `student_id`=" + String.valueOf(student_id) + ";");
            this.stmt.executeUpdate("DELETE FROM `tblScores` WHERE `student_id`=" + String.valueOf(student_id) + ";");
            this.stmt.executeUpdate("DELETE FROM `tblFeedback` WHERE `student_id`=" + String.valueOf(student_id) + ";");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean EditStudent(int student_id){
        return false;
    }
    public void AddTestResult(String FirstName, String LastName, String Score, String Phone, String FeedBack){
        int student_id = 0;
        ResultSet rs;
        PreparedStatement istmt = null;
        try {
            istmt = this.con.prepareStatement("INSERT INTO `tblStudents` (`first_name`, `last_name`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS);
            istmt.setString(1, FirstName);
            istmt.setString(2, LastName);
            istmt.executeUpdate();
            rs = istmt.getGeneratedKeys();
            if(rs.next())  student_id = rs.getInt(1);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                if (istmt != null) { istmt.close(); }
            }
            catch (Exception e) {
            }
        }

        try {
            this.stmt.executeUpdate("INSERT INTO `tblScores` VALUES (\"" + String.valueOf(student_id) + "\", \"" + Score + "\", \"" + Phone + "\");");
            this.stmt.executeUpdate("INSERT INTO  `tblFeedback` VALUES (\"" + String.valueOf(student_id) + "\", \"" + this.escapeString(FeedBack, true) + "\");");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }
    public void GetStudent(int student_id){
        ResultSet rs;
        try {
            rs = this.stmt.executeQuery("SELECT st.*, sc.`score`, sf.`note` FROM `tblStudents` st LEFT JOIN `tblScores` sc ON sc.`student_id` = st.`student_id` LEFT JOIN `tblFeedback` sf ON sf.`student_id` = st.`student_id` WHERE st.`student_id`=" + String.valueOf(student_id) + ";");
            System.out.println("\n------------------------------------------------");
            System.out.println("id\t|\tStudent\t\t|\tScore\t|\tFeedBack");
            System.out.println("------------------------------------------------");
            while (rs.next()) {
                System.out.println(rs.getInt(1) + "\t|\t" + rs.getString(2) + " " + rs.getString(3) + "\t|\t" + rs.getString(4) + "\t|\t" + rs.getString(5));
            }
            System.out.println("------------------------------------------------");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public void GetStudents(){
        List<String> headersList = Arrays.asList("id", "Student", "Score", "FeedBack");
        List<List<String>> rowsList = new ArrayList<>();
        ResultSet rs;
        try {
            rs = this.stmt.executeQuery("SELECT st.*, sc.`score`, sf.`note` FROM `tblStudents` st LEFT JOIN `tblScores` sc ON sc.`student_id` = st.`student_id` LEFT JOIN `tblFeedback` sf ON sf.`student_id` = st.`student_id`;");
            while (rs.next()) {
                rowsList.add(Arrays.asList(rs.getString(1), rs.getString(2) + " " + rs.getString(3), rs.getString(4), rs.getString(5)));
            }
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
        Board board = new Board(95);
        Table table = new Table(board, 85, headersList, rowsList);
        List<Integer> colWidthsListEdited = Arrays.asList(3, 22, 7, 58);
        table.setGridMode(Table.GRID_FULL).setColWidthsList(colWidthsListEdited);
        List<Integer> colAlignList = Arrays.asList(
                Block.DATA_CENTER,
                Block.DATA_MIDDLE_LEFT,
                Block.DATA_CENTER,
                Block.DATA_MIDDLE_LEFT);
        table.setColAlignsList(colAlignList);
        String tableString = board.setInitialBlock(table.tableToBlocks()).build().getPreview();
        System.out.println(tableString);
    }

    public boolean SetScore(int student_id, int score){
        return false;
    }
    public void ClearHistory(){
        try {
            this.stmt.executeUpdate("TRUNCATE TABLE `tblStudents`;");
            this.stmt.executeUpdate("TRUNCATE TABLE `tblScores`;");
            this.stmt.executeUpdate("TRUNCATE TABLE `tblFeedback`;");
        } catch (SQLException sqlEx) {
            sqlEx.printStackTrace();
        }
    }

    public String escapeString(String x, boolean escapeDoubleQuotes) {
        StringBuilder sBuilder = new StringBuilder(x.length() * 11/10);

        int stringLength = x.length();

        for (int i = 0; i < stringLength; ++i) {
            char c = x.charAt(i);

            switch (c) {
                case 0: /* Must be escaped for 'mysql' */
                    sBuilder.append('\\');
                    sBuilder.append('0');

                    break;

                case '\n': /* Must be escaped for logs */
                    sBuilder.append('\\');
                    sBuilder.append('n');

                    break;

                case '\r':
                    sBuilder.append('\\');
                    sBuilder.append('r');

                    break;

                case '\\':
                    sBuilder.append('\\');
                    sBuilder.append('\\');

                    break;

                case '\'':
                    sBuilder.append('\\');
                    sBuilder.append('\'');

                    break;

                case '"': /* Better safe than sorry */
                    if (escapeDoubleQuotes) {
                        sBuilder.append('\\');
                    }

                    sBuilder.append('"');

                    break;

                case '\032': /* This gives problems on Win32 */
                    sBuilder.append('\\');
                    sBuilder.append('Z');

                    break;

                case '\u00a5':
                case '\u20a9':
                    // escape characters interpreted as backslash by mysql
                    // fall through

                default:
                    sBuilder.append(c);
            }
        }

        return sBuilder.toString();
    }

}
