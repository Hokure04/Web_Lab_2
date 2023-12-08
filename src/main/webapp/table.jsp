<%@ page import="java.util.regex.Pattern" %>
<%@ page import="java.util.regex.Matcher" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8" />
    <title>Result</title>
    <style>

    </style>
</head>
<body bgcolor="#0095B6">
<table class="table">
    <thead>
    <tr>
        <th colspan="2" class="table">
            <div class="header-text"> Checking results: </div>
        </th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <td class="table">
            <div id="label_x">X = </div>
        </td>
        <td class="table">
            <div id="error1">${param.x}</div>
            <%
                if(request.getParameter("sender").equals("button")){
                    String stringX = request.getParameter("x");
                    Double x = Double.parseDouble(stringX);
                    if(!((x==-4) || (x==-3) || (x==-2) || (x==-1) || (x==0) || (x==1) || (x==2) || (x==3) || (x==4))){
                        response.sendRedirect("errorPage.jsp");
                    }
                }
            %>
        </td>
    </tr>
    <tr>
        <td class="table">
            <div id="label_y">Y = </div>
        </td>
        <td class="table">
            <div id="error2">${param.y}</div>
            <%
                if(request.getParameter("sender").equals("button")) {
                    double scale = Math.pow(10, 4);
                    String stringY = request.getParameter("y");
                    String regex1 = "^[+-]?[3-5]*\\.?0*$";
                    String regex2 = "^[+-]?[0-2]*\\.?[0-9]*$";
                    Pattern pattern = Pattern.compile(regex1);
                    Pattern pattern1 = Pattern.compile(regex2);
                    Matcher matches11 = pattern.matcher(stringY);
                    Matcher matches12 = pattern1.matcher(stringY);
                    boolean matcher11 = matches11.matches();
                    boolean matcher12 = matches12.matches();
                    Double y = Math.ceil(Double.parseDouble(stringY) * scale) / scale;
                    if (!((y <= 3) && (y >= -3) && (matcher11 || matcher12))) {
                        response.sendRedirect("errorPage.jsp");
                    }
                }
            %>
        </td>
    </tr>
    <tr>
        <td class="table">
            <div id="label_r">R = </div>
        </td>
        <td class="table">
            <div id="error3">${param.r}</div>
            <%
                double scale = Math.pow(10, 4);

                String stringR = request.getParameter("r");
                String regex1 = "^[+-]?[3-5]*\\.?0*$";
                String regex3 = "^[+-]?[2-4]*\\.?[0-9]*$";
                Pattern pattern = Pattern.compile(regex1);
                Pattern pattern2 = Pattern.compile(regex3);
                Matcher matches21 = pattern.matcher(stringR);
                Matcher matches22 = pattern2.matcher(stringR);
                boolean matcher21 = matches21.matches();
                boolean matcher22 = matches22.matches();
                Double r = Math.ceil(Double.parseDouble(stringR)*scale)/scale;
                if(!((r<=5) && (r>=2) && (matcher21 || matcher22))){
                    response.sendRedirect("errorPage.jsp");
                }
            %>
        </td>
    </tr>
    <tr>
        <td class="table">
            <div id="label_res">Result: </div>
        </td>
        <td class="table">
            <div id="error4">${param.result}</div>
        </td>
    </tr>
    <tr>
        <td class="table">
            <div id="label_current">currentTime = </div>
        </td>
        <td class="table">
            <div id="error5">${param.currentTime}</div>
        </td>
    </tr>
    <tr>
        <td class="table">
            <div id="label_exec">executionTime = </div>
        </td>
        <td class="table">
            <div id="error6">${param.execTime}</div>
        </td>
    </tr>
    <tr>
        <td class="table">
            <a href="${pageContext.request.contextPath}/Web_Lab_JSP_2">Homepage</a>
        </td>
        <td class="table">

        </td>
    </tr>
    </tbody>
</table>
<br>
<h3>Meet the creator of this site:</h3>
<br>
<div id="special">
    <img src="https://krasivosti.pro/uploads/posts/2021-12/1639671337_6-krasivosti-pro-p-tupik-ptichka-ptitsi-krasivo-foto-6.jpg" width="100%">
</div>
</body>
</html>
