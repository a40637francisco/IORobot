package app.utils;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created on 16/10/2016.
 */
public class MyFiles {


    public static boolean fileExists(String path) {
        File f = new File(path);
        if (f.exists() && !f.isDirectory()) {
            return true;
        }
        return false;
    }


    public static String getCurrentDir() {
        Path currentRelativePath = Paths.get("");
        return currentRelativePath.toAbsolutePath().toString();
    }

    public static void deleteFile(String path) {
        try {
            File file = new File(path);
            if (file.delete()) {
                //System.out.println(file.getName() + " is deleted!");
            } else {
                System.out.println("Delete operation is failed.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
