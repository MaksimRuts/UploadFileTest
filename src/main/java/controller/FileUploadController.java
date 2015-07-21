package controller;

import by.gsu.epamlab.httprequestparser.RequestBody;
import by.gsu.epamlab.httprequestparser.RequestParser;
import by.gsu.epamlab.httprequestparser.UploadedFile;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class FileUploadController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        perform(req, resp);
    }

    protected void perform(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestBody body = RequestParser.parse(req);
        resp.getWriter().println("Attributes:");
        resp.getWriter().println("Destination: " + body.getAttribute("destination"));
        resp.getWriter().println("Upload: " + body.getAttribute("upload"));
        UploadedFile file = body.getFile("file");
        resp.getWriter().println("Filename: " + file.getFilename());
        file.saveFile(body.getAttribute("destination"));
    }
}
