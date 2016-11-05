<?xml version="1.0" encoding="UTF-8"?>
<!--
Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
-->
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en">
<head>
    <title>Apache Tomcat WebSocket Examples: Echo</title>
    <style type="text/css">
        #connect-container {
        float: left;
        width: 400px
    }

    #connect-container div {
        padding: 5px;
    }

    #console-container {
        float: left;
        margin-left: 15px;
        width: 400px;
    }

    #console {
        border: 1px solid #CCCCCC;
        border-right-color: #999999;
        border-bottom-color: #999999;
        height: 170px;
        overflow-y: scroll;
        padding: 5px;
        width: 100%;
    }

    #console p {
        padding: 0;
        margin: 0;
    }
    </style>
    <script type="application/javascript">
    var ws = null;

    function setConnected(connected) {
        document.getElementById('connect').disabled = connected;
        document.getElementById('disconnect').disabled = !connected;
        document.getElementById('echo').disabled = !connected;
    }

    function connect() {
        var target = document.getElementById('target').value;
        if (target == '') {
            alert('Please select server side connection implementation.');
            return;
        }
        if ('WebSocket' in window) {
            ws = new WebSocket(target);
        } else if ('MozWebSocket' in window) {
            ws = new MozWebSocket(target);
        } else {
            alert('WebSocket is not supported by this browser.');
            return;
        }
        ws.onopen = function () {
            setConnected(true);
            log('Info: WebSocket connection opened.');
        };
        ws.onmessage = function (event) {
            log('Received: ' + event.data);
        };
        ws.onclose = function (event) {
            setConnected(false);
            log('Info: WebSocket connection closed, Code: ' + event.code + (event.reason == "" ? "" : ", Reason: " + event.reason));
        };
    }

    function disconnect() {
        if (ws != null) {
            ws.close();
            ws = null;
        }
        setConnected(false);
    }

    function echo() {
        if (ws != null) {
            var message = document.getElementById('message').value;
            log('Sent: ' + message);
            ws.send(message);
        } else {
            alert('WebSocket connection not established, please connect.');
        }
    }

    function updateTarget(target) {
        if (window.location.protocol == 'http:') {
            document.getElementById('target').value = 'ws://' + window.location.host + target;
        } else {
            document.getElementById('target').value = 'wss://' + window.location.host + target;
        }
    }

    function log(message) {
        var console = document.getElementById('console');
        var p = document.createElement('p');
        p.style.wordWrap = 'break-word';
        p.appendChild(document.createTextNode(message));
        console.appendChild(p);
        while (console.childNodes.length > 25) {
            console.removeChild(console.firstChild);
        }
        console.scrollTop = console.scrollHeight;
    }


    document.addEventListener("DOMContentLoaded", function() {
        // Remove elements with "noscript" class - <noscript> is not allowed in XHTML
        var noscripts = document.getElementsByClassName("noscript");
        for (var i = 0; i < noscripts.length; i++) {
            noscripts[i].parentNode.removeChild(noscripts[i]);
        }
    }, false);
    </script>
</head>
<body>
<div class="noscript"><h2 style="color: #ff0000">Seems your browser doesn't support Javascript! Websockets rely on Javascript being enabled. Please enable
    Javascript and reload this page!</h2></div>
<div>
    <div id="connect-container">
        <div>
            <span>Connect to service implemented using:</span>
            <!-- echo example using new programmatic API on the server side -->
            <input id="radio1" type="radio" name="group1" value="/CompteurWeb/websocket/EchoEndpoint"
                   onclick="updateTarget(this.value);"/> <label for="radio1">programmatic API</label>
            <!-- echo example using new annotation API on the server side -->
            <input id="radio2" type="radio" name="group1" value="/CompteurWeb/websocket/EchoAnnotation"
                   onclick="updateTarget(this.value);"/> <label for="radio2">annotation API</label>
        </div>
        <div>
            <input id="target" type="text" size="40" style="width: 350px"/>
        </div>
        <div>
            <button id="connect" onclick="connect();">Connect</button>
            <button id="disconnect" disabled="disabled" onclick="disconnect();">Disconnect</button>
        </div>
        <div>
            <textarea id="message" style="width: 350px">Here is a message!</textarea>
        </div>
        <div>
            <button id="echo" onclick="echo();" disabled="disabled">Echo message</button>
        </div>
    </div>
    <div id="console-container">
        <div id="console"/>
    </div>
</div>
</body>
</html>