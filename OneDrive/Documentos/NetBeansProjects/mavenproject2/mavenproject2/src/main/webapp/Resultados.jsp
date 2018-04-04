<%-- 
    Document   : Resultados
    Created on : 5/03/2018, 05:30:49 PM
    Author     : jonat
--%>

<%@page import="paquete.GestionarST"%>
<%@page import="paquete.Lugar"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //String ID_LugarTuristico = request.getParameter("id");      //el elegido en la tabla de resultados
    
    //session.setAttribute("ID_lugar", ID_LugarTuristico);
    
    String nombrelugarTur = (String) session.getAttribute("nombreLugarTur");
    String numPers = (String) session.getAttribute("numPersonas");

    //Lugar lgr = GestionarST.getSitioByID(ID_LugarTuristico);
    //double costoTotal = GestionarST.calcularCosto(lgr, numPers);

%>
<HTML>
    <BODY>
        <TITLE>Tu próxima reserva</TITLE>
        <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="estilo.css">

        <section>
            <table id="lugarFinal">
                <tr>
                    <th>Sitio </th>
                    <td><%= lgr.getNombre()%></th>
                </tr>
                <tr>
                    <th>Descripción</th>
                    <td><%= lgr.getDescr()%></td>
                </tr>
                <tr>
                    <th>Ubicación</th>
                    <td><%= lgr.getUbic()%></td>
                </tr>
                <tr>
                    <th>Costo total</th>
                    <td>$ <%=costoTotal%></td>
                </tr>
            </TABLE>
            <img src="<%= lgr.getFoto()%>" alt="">
            <img src="http://www.ciel-voyages.ro/Files/Pictures/Images/mexic-86.jpg" alt="">

            >--<<>>--< 
            <FORM method="get" action="Proxima_Reserva.jsp">
                <input type=number name=ID_cte value="VIENE de login el ID_CLIENTE" /> 
                <input type=submit name=button1 value="Hacer reservación" /> 
            </FORM>
            <form method=get action="Principal.html">
                <input type=submit name=button2 value="Cancelar" /> 
            </FORM>
        </section>
    </BODY>
</HTML>