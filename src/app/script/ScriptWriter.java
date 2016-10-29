package app.script;

import app.utils.MyFiles;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created on 25/11/2015.
 */
public class ScriptWriter {

    public static final String baseDir = "/scripts/";

    public void writeFile(String name, String data) {
        try {
            Path file = Paths.get(MyFiles.getCurrentDir() + baseDir + name + ".txt");
            List<String> list = new ArrayList<>();
            String[] arr = data.split("\n");
            for (int i = 0; i < arr.length; i++) {
                list.add(arr[i]);
            }

            Files.write(file, list, Charset.forName("UTF-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
