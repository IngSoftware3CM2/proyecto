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
    format: 'yyyy-dd-mm'
});

function limpiar_campos() {
    $inputTarjeta.val();
    $inputEntrada.val();
    $inputSalida.val();
    $inputFecha.val();
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

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "http://localhost:8080/api/asistencias/consultar",
        data: JSON.stringify(data),
        dataType: "json",
        success: function(resultado) {
            if (resultado.estado === 2) { // Trayectoria A
                console.log("Mostar error de trayectoria A");
                $inputTarjeta.val();
                return;
            } else if (resultado.estado === 3 ){ // Trayectoria B
                console.log("Mostrar error de trayectoria B");
                return;
            } else if (resultado.estado === 4) { // Trayectoria C
                console.log("Mostrar otro error");
                limpiar_campos();
                return;
            }

            // Verificar hora (Trayectoria E)
            var hora_entrada = $inputEntrada.val();
            var hora_salida = $inputSalida.val();

            if (!re_hora.test(hora_entrada)) {
                console.log("LA hora de entrada esta mal");
                return;
            }
            if (!re_hora.test(hora_salida)) {
                console.log("LA hora de salida esta mal");
                return;
            }
            var condicion = Date.parse("01/01/2011 " + hora_entrada + ":00") < Date.parse("01/01/2011 6:00:00");
            condicion = condicion || Date.parse("01/01/2011 " + hora_entrada + ":00") > Date.parse("01/01/2011 24:59:59");
            if (condicion) {
                console.log("LA hora de entrada esta mal");
                return;
            }
            condicion = Date.parse("01/01/2011 " + hora_salida + ":00") < Date.parse("01/01/2011 22:00:00");
            condicion = condicion || Date.parse("01/01/2011 " + hora_entrada + ":00") > Date.parse("01/01/2011 24:59:59");
            if (condicion) {
                console.log("LA hora de salida esta mal");
                return;
            }

            $tdTarjeta.text($inputTarjeta.val());
            $tdEntrada.text($inputEntrada.val());
            $tdSalida.text($inputSalida.val());
            $tdFecha.text($inputFecha.val());

            $fila.append($tdTarjeta);
            $fila.append($tdEntrada);
            $fila.append($tdSalida);
            $fila.append($tdFecha);
            $fila.append($tdEliminar);

            $bodyRegistros.append($fila);
            lista_registros.push(resultado.noTarjeta);
        },
        error: function(e) {
            console.log("ERROR: " + e);
        }
    })
}

function registrar_asistencias(e) {
    e.preventDefault();
    // Trayectoria F
    // For para obtener toda la data
    limpiar_campos();
}

function eliminar_registro_tabla(e) {
    e.preventDefault();
}

$('#btnRegistrarAsistencias').on("click", registrar_asistencias);
$('#btnAgregar').on("click", agregar_asistencia);