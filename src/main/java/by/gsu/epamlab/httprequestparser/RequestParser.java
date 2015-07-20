package by.gsu.epamlab.httprequestparser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.*;

public class RequestParser {

    public static RequestBody parse (HttpServletRequest req) throws IOException {
        Scanner scanner = new Scanner(req.getInputStream());
        if (scanner.hasNextLine()) {
            Map<String, List<String>> attributes = new LinkedHashMap<String, List<String>>();
            String token = scanner.nextLine();
            List<String> element = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (token.equals(line) || token.concat(ParserConstants.Request.TOKEN_FOOTER).equals(line)) {
                    String header = getElementName(element.get(ParserConstants.Request.HEADER_POSITION));
                    attributes.put(header, element);
                    element = new ArrayList<String>();
                } else {
                    element.add(line);
                }
            }
            return new RequestBody(token, attributes);
        } else {
            // TODO
            throw new RuntimeException();
        }
    }

    private static String getElementName(String name) {
        name = name.replaceFirst(ParserConstants.Request.ELEMENT_PREAMBLE, "");
        int pos = name.indexOf(ParserConstants.Request.ELEMENT_PREAMBLE_END);
        name = name.substring(0, pos);
        return name;
    }
}