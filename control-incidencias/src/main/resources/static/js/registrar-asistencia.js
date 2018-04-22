var re_hora = /^\d{1,2}:\d\d$/;
var re_fecha =/^\d{4}-\d{2}-\d{2}$/;
var re_tarjeta =/^\d{6,8}$/;
var $inputTarjeta = $("#inputTarjeta");
var $inputEntrada = $("#inputEntrada");
var $inputSalida = $("#inputSalida");
var $inputFecha = $("#inputFecha");
var $bodyRegistros = $("#bodyRegistros");
var lista_registros = [];

$inputFecha.datepicker({
    uiLibrary: 'bootstrap4',
    format: 'yyyy-mm-dd'
});

function limpiar_campos() {
    $inputTarjeta.val("");
    $inputEntrada.val("");
    $inputSalida.val("");
    $inputFecha.val("");
}

function agregar_asistencia(e) {
    e.preventDefault();
    console.log("agregar_asistencia");

    var $fila = $(document.createElement("tr"));
    var $tdTarjeta = $(document.createElement("td"));
    var $tdEntrada = $(document.createElement("td"));
    var $tdSalida = $(document.createElement("td"));
    var $tdFecha = $(document.createElement("td"));
    var $tdEliminar = $(document.createElement("td"));
    var $spanEliminar = $(document.createElement("span"));

    var data = {
        estado: 0,
        noTarjeta: parseInt($inputTarjeta.val(), 10),
        fechaRegistro: $inputFecha.val()
    };

    // Trayectoria D
    if (lista_registros.indexOf(data.noTarjeta) >= 0) {
        limpiar_campos();
        return;
    }

    /*
    if (!re_tarjeta.test($inputTarjeta.val())) {
        $("#errorB").css("display", "block");
        return;
    }
     */

    if (!re_fecha.test($inputFecha.val())) {
        $("#error6").css("display", "block");
        return;
    }

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/asistencias/consultar",
        data: JSON.stringify(data),
        dataType: "json",
        success: function(resultado) {
            if (resultado.estado === 2) { // Trayectoria A
                console.log("Mostar error de trayectoria A");
                $("#error1").css("display", "block");
                $inputTarjeta.val("");
                return;
            } else if (resultado.estado === 3 ){ // Trayectoria B
                console.log("Mostrar error de trayectoria B");
                $("#errorB").css("display", "block");
                return;
            } else if (resultado.estado === 4) { // Trayectoria C
                console.log("Mostrar otro de Trayectoria C");
                limpiar_campos();
                $("#errorC").css("display", "block");
                return;
            }

            // Verificar hora (Trayectoria E)
            var hora_entrada = $inputEntrada.val();
            var hora_salida = $inputSalida.val();

            if (!re_hora.test(hora_entrada)) {
                console.log("LA hora de entrada esta mal");
                $("#error3").css("display", "block");
                return;
            }
            if (!re_hora.test(hora_salida)) {
                console.log("LA hora de salida esta mal");
                $("#error4").css("display", "block");
                return;
            }
            var condicion = Date.parse("01/01/2011 " + hora_entrada + ":00") < Date.parse("01/01/2011 6:00:00");
            condicion = condicion || Date.parse("01/01/2011 " + hora_entrada + ":00") > Date.parse("01/01/2011 24:59:59");
            if (condicion) {
                console.log("LA hora de entrada esta mal");
                $("#error3").css("display", "block");
                return;
            }
            condicion = Date.parse("01/01/2011 " + hora_salida + ":00") < Date.parse("01/01/2011 22:00:00");
            condicion = condicion || Date.parse("01/01/2011 " + hora_salida + ":00") > Date.parse("01/01/2011 24:59:59");
            if (condicion) {
                console.log("LA hora de salida esta mal");
                $("#error4").css("display", "block");
                return;
            }

            $tdTarjeta.text($inputTarjeta.val());
            $tdEntrada.text($inputEntrada.val());
            $tdSalida.text($inputSalida.val());
            $tdFecha.text($inputFecha.val());

            $spanEliminar.addClass("oi oi-minus custom-remove-icon");
            $spanEliminar.on("click", eliminar_registro_tabla);
            $tdEliminar.append($spanEliminar);

            $fila.append($tdTarjeta);
            $fila.append($tdEntrada);
            $fila.append($tdSalida);
            $fila.append($tdFecha);
            $fila.append($tdEliminar);

            $bodyRegistros.append($fila);
            lista_registros.push(resultado.noTarjeta);
            $(".invalid-feedback").css("display", "none");
            $(".valid-feedback").css("display", "none");
        },
        error: function(e) {
            console.log("ERROR: " + e);
        }
    });
}

function registrar_asistencias(e) {
    e.preventDefault();
    // Trayectoria F
    // For para obtener toda la data
    var lista = [];
    var hijos = $bodyRegistros.children();

    for (var i = 0; i < hijos.length; i++) {
        var tempNoTarjeta = hijos[i].children[0].textContent;
        tempNoTarjeta = parseInt(tempNoTarjeta, 10);
        var tempHoraEntrada = hijos[i].children[1].textContent;
        var tempHoraSalida = hijos[i].children[2].textContent;
        var tempFecha = hijos[i].children[3].textContent;
        if (tempHoraEntrada.length < 5)
            tempHoraEntrada = "0" + tempHoraEntrada;
        if (tempHoraSalida.length < 5)
            tempHoraSalida = "0" + tempHoraSalida;
        lista.push({
            noTarjeta: tempNoTarjeta,
            horaEntrada: tempHoraEntrada,
            horaSalida: tempHoraSalida,
            fechaRegistro: tempFecha
        });
    }
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/asistencias/agregar",
        data: JSON.stringify(lista),
        dataType: "json",
        success: function(resultado) {
            console.log(resultado);
            limpiar_campos();
            $bodyRegistros.empty();
            lista_registros = [];
            $(".valid-feedback").css("display", "block");
        },
        error: function(e) {
            console.log("ERROR: " + e);
            $("#errorF").css("display", "block");
        }
    });
}

function eliminar_registro_tabla(e) {
    e.preventDefault();
    var $elemento = $(this);
    var temp_tarjeta = $elemento.parent().parent().children()[0];
    var index = lista_registros.indexOf(parseInt(temp_tarjeta));
    if (index > -1)
        lista_registros.splice(index, 1);
    $(this).parent().parent().remove();

}

$('#btnRegistrarAsistencias').on("click", registrar_asistencias);
$('#btnAgregar').on("click", agregar_asistencia);