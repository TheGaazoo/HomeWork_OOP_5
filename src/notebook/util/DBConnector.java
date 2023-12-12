package notebook.util;

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
                System.out.println("Файл базы данных создан!");
            }
            else {
                System.out.println("Файл базы данных уже существует!");
            }
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}
