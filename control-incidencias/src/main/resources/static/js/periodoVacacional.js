$(document).ready(function () {
    $("tbody tr").remove();
    llenarPersonal();
    selectAll();
    registrarDia();
    $("#buttonCancelar").on("click",function(){
        window.location.href="/dch/asistencia/periodovacacional/cancelar";
    });

    document.getElementById("fechaDia").oninvalid = function (ev) {
        console.log("input fechaDia");
        if (this.validity.patternMismatch) {
            this.setCustomValidity("Dato no valido");
            $("#errorRN54").removeClass("hidden");
        }else if (!this.validity.valid) {
            this.setCustomValidity("Complete todos los campos obligatorios");
            $("#MSG").removeClass("hidden");
        } else {
            console.log("VALIDO");
        }
    };

    document.getElementById("justificacion").oninvalid = function (ev) {
        this.setCustomValidity("Complete todos los campos obligatorios");
        $("#MSG").removeClass("hidden");
    };
    document.getElementById("fechaDia").oninput = function (ev) {
        this.setCustomValidity('');
        $("#errorRN54").addClass("hidden");
        $("#MSG").addClass("hidden");
    };
    document.getElementById("justificacion").oninput = function (ev) {
        this.setCustomValidity('');
        $("#MSG").addClass("hidden");
    };


})

function selectAll(){
    $('#selectedAll').change(function() {
        var checkboxes = $(this).closest('form').find(':checkbox');
        checkboxes.prop('checked', $(this).is(':checked'));
    });
}


function llenarPersonal() {
    $( "#tiposPersonal .i-checks" ).on( "click", function() {
        var estado = $( "input:checked" ).val();
        console.log(estado);
        if(estado===undefined){
            $("#MSG").removeClass("hidden");
            return -1;
        }
        $("#MSG").addClass("hidden");
        var datos={
            valorTipo: estado
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location.origin + "/dch/asistencias/periodovacacional/personal",
            data: JSON.stringify(datos),
            dataType: "json",
            success : function (resultado) {
                $("tbody tr").remove();
                var specific_tbody=document.getElementById('tBodyPersonal');
                var row;
                var columna;
                var text="<div class='form-group' ><div class='input-group date col-sm-2'><div class='i-checks col-lg-6'><input name='categoria[]' id= 'checkPersonal' type='checkbox' value='{valor}' /></div> </div></div>";
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
    $("#buttonRegistrar").on("click",function(){
        var fechaIni= $("#fechaInicio").val();
        var fechaFin = $("#fechaFin").val();
        var ids = new Array();
        if(!validarFormatoFecha(fechaIni)){
            $("#errorRN54").removeClass("hidden");
        }
        if(!validarFormatoFecha(fechaFin)){
            $("#errorRN54").removeClass("hidden");
        }
        $("#checkPersonal:checked").each(function() {
            ids.push($(this).val());
        });
        console.log(ids);
        console.log(fechaInicio);
        console.log(fechaFin);
        if(ids.length != 0 && validarFormatoFecha(fechaIni) && validarFormatoFecha(fechaFin)){
            console.log("Entre al if");
            console.log(ids.length);
            $("#MSG").addClass("hidden");
            $("#errorRN54").addClass("hidden");
            var datos = {
                fechaInicio: fechaIni,
                fechaFin: fechaFin,
                listId: ids
            }
            $.ajax({
                type: "POST",
                contentType: "application/json",
                url: window.location.origin + "/dch/asistencias/periodovacacional/agregar",
                data: JSON.stringify(datos),
                dataType: "json",
                success : function (resultado) {
                    if(resultado==-1){
                        // Evaluamos formato de fechas
                        $("#errorRN54").removeClass('hidden');
                    }
                    if(resultado==-2){
                        $("#MSG").removeClass("hidden");
                    }
                    if(resultado==1){
                        window.location.href="/dch/asistencia/periodovacacional/exito";
                    }
                },
                error : function (json) {
                    console.log("Algo anda mal.")
                }
            });
        }else{
            $("#MSG").removeClass("hidden");
        }
    });
}
function validarFormatoFecha(campo) {
    var RegExPattern = /^\d{4}\-\d{2}\-\d{2}$/;
    if ((campo.match(RegExPattern)) && (campo!='')) {
        return true;
    } else {
        return false;
    }
}