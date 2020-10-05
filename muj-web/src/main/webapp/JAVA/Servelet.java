package JAVA;

import java.io.*;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/timezone")
public class MyServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        res.setContentType("text/html;charset=utf-8");
        PrintWriter out = res.getWriter();

        // init
        String input = req.getParameter("zone");
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();

        // check validation
        boolean validni = false;
        for (String str : zoneIds) {
            if (str != null && str.equals(input)) {
                validni = true;
                break;
            }
        }

        // adequate response
        String info = "input is not valid";
        if (validni) {
            ZoneId zone = ZoneId.of(input);
            ZonedDateTime now = ZonedDateTime.now(zone);
            info = "time: " + now;
        }

        out.print("<html><body>"+ info+"<a href='/'>, zkus znovu</a></body></html>");
    }
}