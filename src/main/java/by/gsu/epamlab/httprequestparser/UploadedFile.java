package by.gsu.epamlab.httprequestparser;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

public class UploadedFile {
    private String filename;
    private List<String> consist;

    public UploadedFile() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<String> getConsist() {
        return consist;
    }

    public void setConsist(List<String> consist) {
        this.consist = consist;
    }

    public boolean saveFile(String filePath) {
        StringBuilder path = new StringBuilder();
        path.append(filePath).append(filename);
        try {
            PrintWriter writer = new PrintWriter(new File(path.toString()));
            for (String s : consist) {
                writer.println(s);
            }
            writer.close();
            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // todo
            return false;
        }
    }
}
