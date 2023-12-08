<%@ page import="model.Model" %>
<%@ page import="model.Point" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<% if (!Model.points.isEmpty()) { Point spot = Model.points.get(Model.points.size()-1);%>
<circle id ="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#8B8970 stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 1){ Point spot = Model.points.get(Model.points.size()-2); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill = #FFD700 stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 2) { Point spot = Model.points.get(Model.points.size()-3); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#B22222 stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 3) { Point spot = Model.points.get(Model.points.size()-4); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#FF00FF stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 4) { Point spot = Model.points.get(Model.points.size()-5); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#836FFF stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 5) { Point spot = Model.points.get(Model.points.size()-6); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#FF1493 stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 6) { Point spot = Model.points.get(Model.points.size()-7); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#ff1d58 stroke="white" visibility="visible"></circle>
<% } %>

<% if (!Model.points.isEmpty() && Model.points.size() > 7) { Point spot = Model.points.get(Model.points.size()-8); %>
<circle id="point1" r="3" cx="<%= spot.getX() %>" cy="<%= spot.getY() %>" fill=#e1b382 stroke="white" visibility="visible"></circle>
<% } %>>
