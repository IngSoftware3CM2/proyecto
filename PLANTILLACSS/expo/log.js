var xhttpg = new XMLHttpRequest();
var comp;
function valUser() {
    var xhttp = new XMLHttpRequest();
    xhttp.onreadystatechange = function () {
        if (this.readyState === 4 && this.status === 200) {
            checkForm(this);
        }
    };
    xhttp.open("GET", "dataBase.xml", true);
    xhttp.send();
}

function checkForm(xml) {
    var xmlDoc = xml.responseXML;
    var dataName;
    var dataPass;
    var type;
    var classroom;
    var i;
    var flag = 0;
    var name = document.getElementById("user").value;
    var password = document.getElementById("pass").value;
    if (name === '' || password === '') {
        alert("Fill all fields");
    } else {
        var x = xmlDoc.getElementsByTagName("user");
        for (i = 0; i < x.length; i++) {
            dataName = name.localeCompare(xmlDoc.getElementsByTagName("username")[i].childNodes[0].nodeValue);
            if (dataName === 0) {
                dataPass = password.localeCompare(xmlDoc.getElementsByTagName("password")[i].childNodes[0].nodeValue);
                if (dataPass === 0) {
                    document.getElementById("message").innerHTML = "Please wait...";
                    window.location = "expo.html";
                } else {
                    document.getElementById("message").innerHTML = "Incorrect Password";
                    break;
                }
            } else {
                flag++;
            }
        }
        if (flag === x.length) {
            document.getElementById("message").innerHTML = "Username or Password Incorrect";
        }
    }
}