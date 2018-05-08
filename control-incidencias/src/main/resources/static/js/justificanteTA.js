$(document).ready(function () {
    llenarEstado();
    $(document).on('change', '#getEstado', function(event) {
        llenarEstado();
    });

    $("#buttonCancelar").on("click",function(){
        window.location.href="/personal/justificantes/tipoa/cancelar";
    });
});

function llenarEstado() {
        var estado=$("#getEstado option:selected").html();
        var datos = {
            nombre: estado
        };
        $.ajax({
            type: "POST",
            contentType: "application/json",
            url: window.location.origin + "/personal/justificantes/tipoa/unidadMedica",
            data: JSON.stringify(datos),
            dataType: "json",
            success : function (resultado) {
                $("#getUnidadMedica").empty();
                for(var i=0; i < resultado.length; i++){
                    var option = document.createElement("option"); //Creas el elemento opción
                    $(option).val(resultado[i].idUnidadMedica);
                    $(option).html(resultado[i].nameUnidadMedica); //Escribes en él el nombre de la provincia
                    $(option).appendTo("#getUnidadMedica"); //Lo metes en el select con id provincias
                }
            },
            error : function (json) {
                console.log("Algo anda mal.")
            }
        });
}