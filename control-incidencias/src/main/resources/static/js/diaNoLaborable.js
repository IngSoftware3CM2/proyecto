$(document).ready(function () {
    $("tbody tr").remove();
    llenarPersonal();
    selectAll();
    $("#buttonRegistrar").on("click", registrarDia());
    $("#buttonCancelar").on("click",function(){
        window.location.href="/dch";
    });



})

function selectAll(){
    $('#selectedAll').change(function() {
        var checkboxes = $(this).closest('form').find(':checkbox');
        checkboxes.prop('checked', $(this).is(':checked'));
    });
}
function ValidateSingleInput(oInput) {
    if (oInput.type == "file") {
        var sFileName = oInput.value;
        if (sFileName.length > 0) {
            var blnValid = false;
            for (var j = 0; j < _validFileExtensions.length; j++) {
                var sCurExtension = _validFileExtensions[j];
                if (sFileName.substr(sFileName.length - sCurExtension.length, sCurExtension.length).toLowerCase() == sCurExtension.toLowerCase()) {
                    blnValid = true;
                    break;
                }
            }
            if (!blnValid) {
                $("#mensajeArchivoE").removeClass("hidden");
                $("#mensajeArchivoS").addClass("hidden");
                oInput.value = "";
                return false;
            }
            $("#mensajeArchivoE").addClass("hidden");
            $("#mensajeArchivoS").removeClass("hidden");
        }
        else{
            $("#mensajeArchivoE").addClass("hidden");
            $("#mensajeArchivoS").addClass("hidden");
        }
    }
    return true;
}


function llenarPersonal() {
    $( "#tiposPersonal .i-checks" ).on( "click", function() {
        var estado = $( "input:checked" ).val();
        console.log(estado);

        var datos={
            valorTipo: estado
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location.origin + "/dch/asistencias/dianolaborable/personal",
            data: JSON.stringify(datos),
            dataType: "json",
            success : function (resultado) {
                $("tbody tr").remove();
                var specific_tbody=document.getElementById('tBodyPersonal');
                var row;
                var columna;
                var text="<div class='form-group' ><div class='input-group date col-sm-2'><div class='i-checks col-lg-6'><label><input name='operacion[]' id= 'checkPersonal' type='checkbox' value='{valor}' /></label></div> </div></div>";
                for(var i=0; i<resultado.length;i++){
                    row=specific_tbody.insertRow();
                    columna= row.insertCell();
                    columna.innerHTML=resultado[i].nombre;
                    columna= row.insertCell();
                    columna.innerHTML=resultado[i].noTarjeta;
                    columna= row.insertCell();
                    var text1 =text.replace("{valor}",resultado[i].idPersonal);
                    columna.innerHTML=text1;
                }
                console.log(resultado);
            },
            error : function (json) {
                console.log("Algo anda mal.")
            }
        });
    });
}
function registrarDia(){
    $("input:checkbox:checked").each(function() {
        alert($(this).val());
    });

    /*$.ajax({
        type: "POST",
        contentType: "application/json",
        url: window.location.origin + "/dch/asistencias/dianolaborable/personal/agregar",
        data: JSON.stringify(datos),
        dataType: "json",
        success : function (resultado) {
            $("tbody tr").remove();
            var specific_tbody=document.getElementById('tBodyPersonal');
            var row;
            var columna;
            var text="<div class='form-group' ><div class='input-group date col-sm-2'><div class='i-checks col-lg-6'><input type='checkbox' value='{valor}'/></div> </div></div>";
            for(var i=0; i<resultado.length;i++){
                row=specific_tbody.insertRow();
                columna= row.insertCell();
                columna.innerHTML=resultado[i].nombre;
                columna= row.insertCell();
                columna.innerHTML=resultado[i].noTarjeta;
                columna= row.insertCell();
                var text1 =text.replace("{valor}",resultado[i].idPersonal);
                columna.innerHTML=text1;
            }
            console.log(resultado);
        },
        error : function (json) {
            console.log("Algo anda mal.")
        }
    });
*/
}