<%@ page import="model.Model" %>
<%@ page import="model.Point" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    Model model = (Model) request.getServletContext().getAttribute("model");
    if (model != null && !model.getPoints().isEmpty()) {
        Point spot = model.getPoints().get(model.getPoints().size() - 1);
%>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#8B8970 stroke="white" visibility="visible"></circle>
<% } %>

<%
    if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 1) {
        Point spot = model.getPoints().get(model.getPoints().size() - 2);
%>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#FFD700 stroke="white" visibility="visible"></circle>
<% } %>

<%
    if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 2) {
        Point spot = model.getPoints().get(model.getPoints().size() - 3);
%>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#B22222 stroke="white" visibility="visible"></circle>
<% } %>

<% if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 3) {
    Point spot = model.getPoints().get(model.getPoints().size()-4); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#FF00FF stroke="white" visibility="visible"></circle>
<% } %>

<% if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 4) {
    Point spot = model.getPoints().get(model.getPoints().size()-5); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#836FFF stroke="white" visibility="visible"></circle>
<% } %>

<% if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 5) {
    Point spot = model.getPoints().get(model.getPoints().size()-6); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#FF1493 stroke="white" visibility="visible"></circle>
<% } %>

<% if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 6) {
    Point spot = model.getPoints().get(model.getPoints().size()-7); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#ff1d58 stroke="white" visibility="visible"></circle>
<% } %>

<% if (model != null && !model.getPoints().isEmpty() && model.getPoints().size() > 7) {
    Point spot = model.getPoints().get(model.getPoints().size()-8); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#e1b382 stroke="white" visibility="visible"></circle>
<% } %>
