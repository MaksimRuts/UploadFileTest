package by.gsu.epamlab.httprequestparser;

import java.io.*;
import java.util.List;

public class UploadedFile {
    private String filename;
    private List<byte[]> content;

    public UploadedFile() {
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public List<byte[]> getContent() {
        return content;
    }

    public void setContent(List<byte[]> content) {
        this.content = content;
    }

    public boolean saveFile(String filePath) {
        StringBuilder path = new StringBuilder();
        path.append(filePath).append(filename);
        FileOutputStream fileOutputStream = null;
        BufferedWriter writer = null;
        try {
            fileOutputStream = new FileOutputStream(new File(path.toString()));
            writer = new BufferedWriter(new OutputStreamWriter(fileOutputStream));
            for (byte[] arr : content) {
                fileOutputStream.write(arr);
            }

            return true;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            // todo
            return false;
        } catch (IOException e) {
            // todo
            e.printStackTrace();
            return false;
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
