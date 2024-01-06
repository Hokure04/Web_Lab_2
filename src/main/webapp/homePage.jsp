<%@ page import="model.Model" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="utf-8">
    <style>
        <%@include file='style.css' %>
    </style>
    <link rel="icon" href="images/free-icon-brain.png">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <title>Лабораторная №2</title>
</head>
<body>
<header class="shaded animated">
    <div id="credit">
        <h2>
            ФИО:Каргин Александр Максимович<br>
            Группа: P3206<br>
            Вариант: 1618
        </h2>
    </div>
</header>
<form id="form" method="GET">
    <table id="table" class="shaded">
        <tr>
            <td colspan="5" rowspan="10"><h3>Введите значения переменных:</h3></td>
        </tr>
        <tr>
            <thead><td colspan="5" rowspan="10"><h3>Немного текста:</h3></td></thead>
        </tr>
        <tbody>
        <tr class="plot">
            <td class="plot" colspan="5">
                <div class="graphic card">
                    <p style="visibility: hidden">graphic</p>
                    <%@ include file="svg.html" %>
                    <circle id="point" r="3" cx="125" cy="125" fill="white" stroke="#641AD5" visibility="visible"></circle>
                    <jsp:include page="points.jsp" />
                    </svg>
                </div>
            </td>
        </tr>
        <tr>
            <td class="table">
                <div id="label_x">Введите X</div>
            </td>
            <td class="table">
                <section class="check-values x">
                    <input type="checkbox" id="q" name="x" value="-4" class="check-box">-4<br>
                    <input type="checkbox" id="w" name="x" value="-3" class="check-box">-3<br>
                    <input type="checkbox" id="e" name="x" value="-2" class="check-box">-2<br>
                    <input type="checkbox" id="r" name="x" value="-1" class="check-box">-1<br>
                    <input type="checkbox" id="t" name="x" value="0" class="check-box">0<br>
                    <input type="checkbox" id="y" name="x" value="1" class="check-box">1<br>
                    <input type="checkbox" id="u" name="x" value="2" class="check-box">2<br>
                    <input type="checkbox" id="i" name="x" value="3" class="check-box">3<br>
                    <input type="checkbox" id="o" name="x" value="4" class="check-box">4<br>
                </section>
            </td>
        </tr>
        <tr>
            <td class="table">
                <div id="label_y">Y:</div>
            </td>
            <td class="table">
                <input type="text" name="Y" id="Y_field" placeholder=" Значение в промежутке [-3..3] " class="Y_text">
            </td>
        </tr>
        <tr>
            <td class="table">
                <div id="label_r">R:</div>
            </td>
            <td class="table">
                <input type="text" name="R" id="R_field" placeholder="Значение в промежутке [2..5] " class="R_text">
            </td>
        </tr>
        <div class="X_coordinate">
            <input name="Xgr" id="X_field" type="hidden">
        </div>
        <tr>
            <td class="table">
            </td>
            <td>
                <button type="submit" class="button" id="submit"> Отправить</button>
                <button type="button" id="clearButton" name="clear" onclick="clearTable()">Очистить таблицу</button>
                <button type="submit" id="submit2" style="display: none; "></button>
            </td>
        </tr>
        <tr><td colspan="5"><hr></td></tr>
        <tr>
            <td class="table" colspan="5">
                <h3 id="final">Результаты</h3>
            </td>
        </tr>
        <tr><td colspan="5"><hr></td></tr>
        <td class="table" colspan="5">
            <table class="result-table">
                <thead>
                <tr>
                    <th id="tbX"> X</th>
                    <th id="longY"> Y</th>
                    <th id="tbR"> R</th>
                    <th>Статус</th>
                    <th>Текущее время</th>
                    <th>Время скрипта</th>
                </tr>
                </thead>
                <tbody id="table_out">
                <ul>
                    <%
                        System.out.println("Hi");
                        Model model = (Model) request.getServletContext().getAttribute("model");
                        System.out.println("Sasha");
                        if (model != null && !model.getPoints().isEmpty()) {
                            for (int i = model.getPoints().size()-1; i >= 0; i--) {
                                System.out.println("How are you?");
                                out.println(model.getPoints().get(i).toString());
                            }
                        }

                    %>
                </ul>
                </tbody>
            </table>
        </td>
        </tbody>
    </table>
</form>
<script type="text/javascript">
    <%@include file="/valid/Validation.js"%>
</script>
<script type="text/javascript">
    <%@include file="/valid/GetMethod.js"%>
</script>
<script type="text/javascript">
    <%@include file="/valid/OnClick.js"%>
</script>
</body>
</html>