package notebook.util;

import notebook.Colors;

import java.io.File;

public class DBConnector {
    public String dbPath;
    public DBConnector(String dbPath) {
        this.dbPath = dbPath;
    }
    public DBConnector() { this("db.txt"); }
    public void createDB() {
        try {
            File db = new File(dbPath);
            if (db.createNewFile()) {
                System.out.println(Colors.yellow + Colors.bold + "Файл базы данных создан!"+ Colors.reset);
            }
            else {
                System.out.println(Colors.green + Colors.bold + "Файл базы данных уже существует!" + Colors.reset);
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
