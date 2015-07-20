package by.gsu.epamlab.httprequestparser;

public class ParserConstants {
    public static class Request {
        public static final int HEADER_POSITION = 0;
        public static final String TOKEN_FOOTER = "--";
        public static final String ELEMENT_PREAMBLE = "Content-Disposition: form-data; name=\"";
        public static final String ELEMENT_PREAMBLE_END = "\"";
        public static final int ELEMENT_ATTRIBUTE_POSITION = 2;
        public static final String ELEMENTS_SEPARATOR = "; ";
        public static final String FILENAME = "filename=\"";
        static final String NEXT_LINE = "\r\n";
    }
}
