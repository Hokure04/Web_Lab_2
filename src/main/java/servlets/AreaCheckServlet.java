package servlets;

import model.Model;
import model.Point;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AreaCheckServlet extends HttpServlet {
    public Model model;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html");

        HttpSession session = req.getSession();
        if(session.getAttribute("model") == null) {
            model = new Model();
        } else {
            model = (Model)session.getAttribute("model");
        }

        try {
            if (!(tryToParse(req.getParameter("x")) && (tryToParse(req.getParameter("Xgr"))))) {
                if (tryToParse(req.getParameter("x"))) {

                    checkButton(req,resp);
                } else if (tryToParse(req.getParameter("Xgr"))) {

                    checkGraphic(req,resp);
                } else {
                    createErrorPage(resp, "Impossible to find X");
                }

            } else {
                createErrorPage(resp, "Something get wrong!");
            }

        } catch (Exception e) {
            PrintWriter writer = resp.getWriter();
            writer.write("Controller operation error: " + e.toString());
            writer.close();
        }
    }


    public void checkButton(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        double scale = Math.pow(10, 4);
        long start = System.nanoTime();
        String res = "";
        Double x = Math.ceil(Double.parseDouble(req.getParameter("x")) * scale) / scale;
        String stringY = req.getParameter("Y");
        String stringR = req.getParameter("R");
        //^[+-]?\d*\.?0*$
        String regex1 = "^[+-]?[3-5]*\\.?0*$";
        String regex2 = "^[+-]?[0-2]*\\.?[0-9]*$";
        String regex3 = "^[+-]?[2-4]*\\.?[0-9]*$";
        Pattern pattern = Pattern.compile(regex1);
        Pattern pattern1 = Pattern.compile(regex2);
        Pattern pattern2 = Pattern.compile(regex3);
        Matcher matches11 = pattern.matcher(stringY);
        Matcher matches12 = pattern1.matcher(stringY);
        boolean matcher11 = matches11.matches();
        boolean matcher12 = matches12.matches();
        Matcher matches21 = pattern.matcher(stringR);
        Matcher matches22 = pattern2.matcher(stringR);
        boolean matcher21 = matches21.matches();
        boolean matcher22 = matches22.matches();

        Double y = Math.ceil(Double.parseDouble(stringY) * scale) / scale;
        Double r = Math.ceil(Double.parseDouble(stringR)*scale) / scale;

        String execTime = String.valueOf(System.nanoTime() - start);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if(((x==-4) || (x==-3) || (x==-2) || (x==-1) || (x==0) || (x==1) || (x==2) || (x==3) || (x==4)) && (y>=-3) &&
                (y<=3) && (r>=2) &&  (r<=5) && (matcher11||matcher12) && (matcher21||matcher22)){
            if(inZone(x,y,r)){
                res = "True";
                model.setPoint(new Point(x,y,r,execTime,true));

            }else {
                res = "False";
                model.setPoint(new Point(x,y,r,execTime,false));
            }
            drawTable(resp,x.toString(),y.toString(),r.toString(),res,currentTime, execTime, "button");
        }else {
            createErrorPage(resp,"Something get wrong!");
        }

        HttpSession session = req.getSession();
        session.setAttribute("model", model);
    }

    public void checkGraphic(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        double scale = Math.pow(10, 4);
        long start = System.nanoTime();
        String res = "";
        Double x = Math.ceil(Double.parseDouble(req.getParameter("Xgr")) * scale) / scale;
        Double y = Math.ceil(Double.parseDouble(req.getParameter("Y")) * scale) / scale;
        Double r = Math.ceil(Double.parseDouble(req.getParameter("R")) * scale) / scale;
        String execTime = String.valueOf(System.nanoTime() - start);
        String currentTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        if((r>=2)&&(r<=5)){
            if(inZone(x,y,r)){
                res = "True";
                model.setPoint(new Point(x,y,r,execTime,true));

            }else {
                res = "False";
                model.setPoint(new Point(x,y,r,execTime,false));
            }
            drawTable(resp,x.toString(),y.toString(),r.toString(),res,currentTime, execTime, "graphic");

        }else {
            createErrorPage(resp,"Something get wrong!");
        }
    }

    public void drawTable(HttpServletResponse resp, String x, String y, String r, String result,String currentTime, String execTime, String sender) throws IOException {

        resp.sendRedirect("table.jsp?x="+x+"&y="+y+"&r="+r+"&result="+result+"&currentTime="+currentTime+"&execTime="+execTime+"&sender="+sender);

        /*PrintWriter writer = resp.getWriter();
        String answer = "<html>\n" +
                "  <head>\n" +
                "   <meta charset=\"utf-8\" /> " +
                "   <title>Result</title>" +
                "   <style>\n" +
                ".header-text{\n" +
                "\t\t\t\tcolor: black;\n" +
                "\t\t\t\tfont-size: 20px;\n" +
                "\t\t\t}" +
                "#special{\n"+
                "\t\t\t\twidth: 70%;\n"+
                "\t\t\t\tfont-size: 14px;\n"+
                "\t\t\t\tmargin: auto;\n"+
                "\t\t\t\ttext-align: center;\n"+
                "\t\t\t\tborder-collapse: collapse;\n"+
                "\t\t\t\tborder-top: 5px ridge #2E8B57;\n"+
                "\t\t\t\tborder-bottom: 5px ridge #2E8B57;\n"+
                "\t\t\t\tborder-right: 5px ridge #2E8B57;\n"+
                "\t\t\t\tborder-left: 5px ridge #2E8B57;\n"+
                "\t\t\t}\n"+
                "h3{\n"+
                "\t\t\t\ttext-align: center;\n"+
                "\t\t\t\tmargin: auto;\n"+
                "\t\t\t}\n"+
                "table {\n" +
                "\t\t\t\tfont-size: 14px;\n" +
                "\t\t\t\tmargin: auto;\n" +
                "\t\t\t\ttext-align: center;\n" +
                "\t\t\t\tborder-collapse: collapse;\n" +
                "\t\t\t\tborder-top: 5px ridge #2E8B57;\n" +
                "\t\t\t\tborder-bottom: 5px ridge #2E8B57;\n" +
                "\t\t\t\tborder-right: 5px ridge #2E8B57;\n" +
                "\t\t\t\tborder-left: 5px ridge #2E8B57;\n" +
                "\t\t\t}" +
                "th {\n" +
                "\t\t\t\tfont-size: 13px;\n" +
                "\t\t\t\tfont-weight: normal;\n" +
                "\t\t\t\tbackground: ghostwhite;\n" +
                "\t\t\t\tborder-right: 1px ridge #2E8B57;\n" +
                "\t\t\t\tborder-left: 1px ridge #2E8B57;\n" +
                "\t\t\t\tcolor: black;\n" +
                "\t\t\t\tpadding: 8px;\n" +
                "\t\t\t}" +
                "td {\n" +
                "\t\t\t\tbackground: ghostwhite;\n" +
                "\t\t\t\tborder-right: 1px ridge #2E8B57;\n" +
                "\t\t\t\tborder-left: 1px ridge #2E8B57;\n" +
                "\t\t\t\tcolor: black;\n" +
                "\t\t\t\tpadding: 8px;\n" +
                "\t\t\t}" +
                "\t\t</style>" +
                "\t</head>" +
                "\t<body bgcolor=\"#0095B6\">\n" +
                "        <table class=\"table\">\n" +
                "            <thead>\n" +
                "                <tr>\n" +
                "                    <th colspan=\"2\" class=\"table\">\n" +
                "                        <div class=\"header-text\"> Checking results: </div>\n" +
                "                    </th>\n" +
                "                </tr>\n" +
                "            </thead>\n" +
                "            <tbody>\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_x\">X = </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id = \\\"error1\\\">" + x + "</div>\t\t\t\t\t\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_y\">Y = </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                       <div id = \\\"error2\\\">" + y + "</div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_r\">R = </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id = \\\"error3\\\">" + r + "</div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_r\">Result: </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id = \\\"error4\\\">" + result + "</div>\n" +
                "                    </td>\n" +
                "                </tr>" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_current\">executionTime = </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id = \\\"error5\\\">" + currentTime + "</div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id=\"label_exec\">executionTime = </div>\n" +
                "                    </td>\n" +
                "                    <td class=\"table\">\n" +
                "                        <div id = \\\"error6\\\">" + execTime + "</div>\n" +
                "                    </td>\n" +
                "                </tr>\n" +
                "\n" +
                "                <tr>\n" +
                "                    <td class=\"table\">\n" +
                "                        <a href = \"http://localhost:8080/Web_Lab_JSP_2_war_exploded/\"> Homepage </a>\n" +
                "                    </td>\n" +
                "\t\t\t\t\t<td class=\"table\">                       \n" +
                "                    </td>\n" +
                "                </tr>              \n" +
                "            </tbody>\n" +
                "        </table>\n" +
                "       <br>\n"+
                "       <h3>Meet the creator of this site:</h3>\n"+
                "       <br>\n"+
                "       <div id=\"special\">\n"+
                "           <img src=\"https://krasivosti.pro/uploads/posts/2021-12/1639671337_6-krasivosti-pro-p-tupik-ptichka-ptitsi-krasivo-foto-6.jpg\" width=\"100%\">\n"+
                "       </div>\n"+
                "    </body>\n" +
                "</html>";
        writer.write(answer);
        writer.close();*/
    }


    public void createErrorPage(HttpServletResponse resp, String text) throws IOException {


        PrintWriter writer = resp.getWriter();
        String answer =
                "<!DOCTYPE html>\n"+
                        "<html lang =\"en\">\n"+
                        "<head>\n"+
                        "   <meta charset=\"UTF-8\">\n"+
                        "   <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n"+
                        "   <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"+
                        "   <title>Error 400 page</title>\n"+
                        "   <style>\n"+
                        "       body {\n"+
                        "           margin: 0;\n"+
                        "           box-sizing: border-box;\n"+
                        "       }\n"+
                        "\n"+
                        "       .container {\n"+
                        "           height: 100vh;\n"+
                        "           display: flex;\n"+
                        "           justify-content: center;\n"+
                        "           align-items: center;\n"+
                        "           flex-direction: column;\n"+
                        "           background: linear-gradient(to right, rgba(0, 0, 0, .5), rgba(0, 0, 0, .1)), url('https://images.unsplash.com/photo-1595624871930-6e8537998592?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8&auto=format&fit=crop&w=871&q=80');\n"+
                        "           background-size: cover;\n"+
                        "           background-position: center;\n"+
                        "           background-repeat: no-repeat;\n"+
                        "       }\n"+
                        "\n"+
                        "       h1 {\n"+
                        "           font-size: 10rem;\n"+
                        "           color: #fff;\n"+
                        "           text-transform: uppercase;\n"+
                        "           font-weight: 700;\n"+
                        "           margin-bottom: 1rem;\n"+
                        "       }\n"+
                        "\n"+
                        "       h2 {\n"+
                        "           font-size: 2rem;\n"+
                        "           color: #fff;\n"+
                        "           text-transform: uppercase;\n"+
                        "           font-weight: 700;\n"+
                        "           margin-bottom: 1rem;\n"+
                        "       }\n"+
                        "\n"+
                        "       p {\n"+
                        "           font-size: 1.5rem;\n"+
                        "           color: #fff;\n"+
                        "           font-weight: 700;\n"+
                        "           margin-bottom: 1rem;\n"+
                        "       }\n"+
                        "\n"+
                        "       a {\n"+
                        "           padding: 15px 20px;\n"+
                        "           background: #52caee;\n"+
                        "           font-size: 1rem;\n"+
                        "           text-decoration: none;\n"+
                        "           color: #333333;\n"+
                        "           border-radius: .25rem;\n"+
                        "           box-shadow: 0 0 20px rgba(255, 255, 255, 0.808)\n"+
                        "       }\n"+
                        "   </style>\n"+
                        "</head>\n"+
                        "\n"+
                        "<body>\n"+
                        "   <div class=\"container\">\n"+
                        "       <h1>400</h1>\n"+
                        "       <br>\n"+
                        "       <h2>Something get wrong</h2><br>\n"+
                        "       <p>Sorry, but something in your actions caused the site to crash.</p><br>\n"+
                        "       <p><a href = \"http://localhost:8080/Web_Lab_JSP_2_war_exploded/\">Homepage</a></p><br>\n"+
                        "   </div>\n"+
                        "</body>\n"+
                        "</html>";
        writer.write(answer);
        writer.close();
    }

    private boolean tryToParse(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException | NullPointerException ex) {
            return false;
        }
    }


    public boolean inZone(Double x, Double y, Double r){
        boolean res = false;
        if ((x<=0) && (y>=0) && (x*x + y*y <= (r/2)*(r/2))||
                ((x>=0) && (y <= 0) && (y >= -r) && (x<= (r/2)))||
                ((x >= 0) && (y>=0) && (x*x + y*y <= (r*r + (r/2)*(r/2))))){
            res = true;
        }
        return res;
    }
}