package by.gsu.epamlab.httprequestparser;

import java.util.*;

public class RequestBody {
    public static final int FILE_START_POSITION = 3;
    private String token;
    private Map<String, List<String>> attributes;

    public RequestBody(String token, Map<String, List<String>> attributes) {
        this.token = token;
        this.attributes = attributes;
    }

    public String getAttribute(String name) {
        if (attributes.containsKey(name)) {
            StringBuilder builder = new StringBuilder();
            List<String> list = attributes.get(name);
            for (int i = ParserConstants.Request.ELEMENT_ATTRIBUTE_POSITION; i < list.size(); i++) {
                builder.append(list.get(i)).append(ParserConstants.Request.NEXT_LINE);
            }
            builder.delete(builder.lastIndexOf(ParserConstants.Request.NEXT_LINE), builder.length());
            return builder.toString();
        } else {
            return null;
        }
    }

    public UploadedFile getFile(String name) {
        if (attributes.containsKey(name)) {
            List<String> rawFile = attributes.get(name);
            UploadedFile file = new UploadedFile();
            String filename = rawFile.get(ParserConstants.Request.HEADER_POSITION);
            StringBuilder builder = new StringBuilder();
            builder.append(ParserConstants.Request.ELEMENT_PREAMBLE)
                    .append(name)
                    .append(ParserConstants.Request.ELEMENT_PREAMBLE_END)
                    .append(ParserConstants.Request.ELEMENTS_SEPARATOR)
                    .append(ParserConstants.Request.FILENAME);

            filename = filename.replaceFirst(builder.toString(), "");
            int pos = filename.lastIndexOf(ParserConstants.Request.ELEMENT_PREAMBLE_END);
            filename = filename.substring(0, pos);
            file.setFilename(filename);

            List<String> newList = new ArrayList<String>(rawFile);
            newList.remove(0);
            newList.remove(0);
            newList.remove(0);
            file.setConsist(newList);
            return file;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<String>> entry : attributes.entrySet()) {
            builder.append(token)
                    .append(ParserConstants.Request.NEXT_LINE);
            for (String s : entry.getValue()) {
                builder.append(s).append(ParserConstants.Request.NEXT_LINE);
            }
        }
        builder.append(token).append(ParserConstants.Request.TOKEN_FOOTER);
        return builder.toString();
    }
}