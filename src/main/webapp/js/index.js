
var ws = null;
function connect() {
    var target ='ws://' + window.location.host+"/APP_TWAP/websocket/EnvoieDonnes";
    if ('WebSocket' in window) {
        ws = new WebSocket(target);
    } else if ('MozWebSocket' in window) {
        ws = new MozWebSocket(target);
    } else {
        alert('WebSocket is not supported by this browser.');
        return;
    }
    ws.onopen = function () {
    };

    ws.onmessage = function (event) {

        var test = document.getElementById("testAffichage");
        var testJson = JSON.parse(event.data);
        var string='';
        string += "<div class=\"col-md-12 col-sm-12\">";
        string += "<table>";
        string += " <tr><th>id</th><th>passager</th><th>arret</th><th>date</th><th>direction</th></tr>";
        for(var i =0;i< testJson.length;i++) {
            string += " <tr><td>"+testJson[i].id+"</td>";
            string +="<td>"+testJson[i].passager+"</td>";
            string +="<td>"+testJson[i].arret+"</td>";
            string +="<td>"+testJson[i].date+"</td>";
            string +="<td>"+testJson[i].direction+"</td>";
            string +="</tr>";
        }
        string+= "</table>";
        string+= "</div>";
        test.innerHTML= string;


    };
    ws.onclose = function (event) {
    };
}



$(function() {
    connect()
});



