package by.gsu.epamlab.httprequestparser;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RequestParser {

    public static RequestBody parse (HttpServletRequest req) throws IOException {
        Map<String, List<byte[]>> attributes = new LinkedHashMap<String, List<byte[]>>();
        ServletInputStream stream = req.getInputStream();
        int ARRAY_SIZE = 1024;
        int bytesCount;
        byte[] result = new byte[ARRAY_SIZE];

        bytesCount = stream.readLine(result, 0, ARRAY_SIZE);
        byte[] tokenBin = Arrays.copyOf(result, bytesCount - ParserConstants.Request.NEXT_LINE.length());

        List<byte[]> element = new ArrayList<byte[]>();
        while ((bytesCount = stream.readLine(result, 0, ARRAY_SIZE)) != -1) {
            if (Arrays.equals(tokenBin, Arrays.copyOf(result, tokenBin.length))) {
                String header = getElementName(element);
                // remove NEXT_LINE symbols from last element
                byte[] last = element.remove(element.size() - 1);
                element.add(Arrays.copyOf(last, last.length - ParserConstants.Request.NEXT_LINE.length()));
                attributes.put(header, element);
                element = new ArrayList<byte[]>();
            } else {
                element.add(Arrays.copyOf(result, bytesCount));
            }
        }
        return new RequestBody(new String(tokenBin), attributes);
    }

    private static String getElementName(List<byte[]> element) {
        String name = new String(element.get(0));
        name = name.replaceFirst(ParserConstants.Request.ELEMENT_PREAMBLE, "");
        int pos = name.indexOf(ParserConstants.Request.QUOTE);
        name = name.substring(0, pos).replace(ParserConstants.Request.NEXT_LINE, "");
        return name;
    }
}