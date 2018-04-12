package webapp;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class RecursiveBypass {
    private static StringBuilder indent = new StringBuilder("\t");
    private static void listAll(String path, ArrayList<String> res) throws IOException {
        File dir = new File(path);
        File[] list = dir.listFiles();
        if (list != null) {
            for (File f : list) {
                if (f.isDirectory()) {
                    res.add(indent + "Directory: " + f.getName());
                    indent.append("\t");
                    listAll(f.getCanonicalPath(), res);
                } else if (f.isFile()) {
                    res.add(indent + "File: " + f.getName());
                }
            }indent.deleteCharAt(indent.indexOf("\t"));
        }
    }

    public static void main(String[] args) {
        ArrayList<String> res = new ArrayList<>();
        final String path = "/home/timur/IdeaProjects/basejava";
        try {
            listAll(path, res);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s : res)
            System.out.println(s);

    }
}
