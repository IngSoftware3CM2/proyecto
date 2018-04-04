<%-- 
    Document   : Reserva
    Created on : 5/03/2018, 05:30:25 PM
    Author     : jonat
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="paquete.DocentePAAE"%>
<%@page import="paquete.GestionarST"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<HTML>
    <BODY>
        <TITLE>Hacer reserva</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="estilo.css">

        


        <%
            
            ArrayList<DocentePAAE> arr = new ArrayList<>();

            if (true) {
                arr = GestionarST.getAllDocentes();
                if (arr.size()==0) {
        %><tr>No hubo resultado(s).</TR><%
        } else {
        %>

    <hr>
    <form method="get" action="Resultados.jsp">
        <table border=1 cellpadding=5 id="busquedaLugares">
            <tr>
                <th>Nombre</th>
                <th>Apellido paterno</th>
                <th>RFC</th>
                <th>Núm. trab.</th>
                <th>Foto</th>
            </tr>
            <%
                for (int i = 0; i < arr.size(); i++) {
            %>
            <tr>
                <td><%= arr.get(i).getNombre()%></td>
                <td><%= arr.get(i).getapPat()%> </td>
                <td><%= arr.get(i).getrfc()%>  </td>
                <td><%= arr.get(i).getNumTrab()%></td>
            </tr>

            <%
                }
            %>
        </table>   
        <br>
        <input type="submit" value="Hacer reserva" action="Resultados.jsp">
    </form>
    <%
                
            }

        }

    %>

    <br><br><br>
    <form method=get action="Principal.html"> 
        <input type=submit name=button1 value="Regresar" /> 
    </form>
</body>
</html>
