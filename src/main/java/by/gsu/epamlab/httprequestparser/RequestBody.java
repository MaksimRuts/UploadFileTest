package by.gsu.epamlab.httprequestparser;

import java.util.*;

public class RequestBody {
    public static final int FILE_START_POSITION = 3;
    private String token;
    private Map<String, List<byte[]>> attributes;

    public RequestBody(String token, Map<String, List<byte[]>> attributes) {
        this.token = token;
        this.attributes = attributes;
    }

    public String getAttribute(String name) {
        if (attributes.containsKey(name)) {
            List<byte[]> element = attributes.get(name);
            StringBuilder builder = new StringBuilder();

            for (int i = ParserConstants.Request.ELEMENT_ATTRIBUTE_POSITION; i < element.size(); i++) {
                builder.append(new String(element.get(i)));
            }
            return builder.toString();
        } else {
            return null;
        }
    }

    public UploadedFile getFile(String name) {
        if (attributes.containsKey(name)) {
            List<byte[]> rawFile = attributes.get(name);
            UploadedFile file = new UploadedFile();
            byte[] rawFilename = rawFile.get(ParserConstants.Request.HEADER_POSITION);
            int filenameStartPosition = ParserConstants.Request.ELEMENT_PREAMBLE.length()
                    + name.length()
                    + ParserConstants.Request.QUOTE.length()
                    + ParserConstants.Request.ELEMENTS_SEPARATOR.length()
                    + ParserConstants.Request.FILENAME.length();
            int filenameEndPosition = filenameStartPosition;
            while (ParserConstants.Request.QUOTE.charAt(0) != rawFilename[filenameEndPosition++]);
            String filename = new String(rawFilename, filenameStartPosition, filenameEndPosition - filenameStartPosition - 1);

            file.setFilename(filename);

            List<byte[]> newList = new ArrayList<byte[]>(rawFile);
            newList.remove(0);
            newList.remove(0);
            newList.remove(0);
            file.setContent(newList);
            return file;
        } else {
            return null;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        for (Map.Entry<String, List<byte[]>> entry : attributes.entrySet()) {
            builder.append(token).append(ParserConstants.Request.NEXT_LINE);
            for (byte[] s : entry.getValue()) {
                builder.append(new String(s));
            }
        }
        builder.append(token).append(ParserConstants.Request.TOKEN_FOOTER);
        return builder.toString();
    }
}