var socketHandler = new function(){

    var socket;
    this.currentId;
    this.clients = new Map();


    this.connect = function(){
        socket = new SockJS("http://localhost:8080/chat");
        socket.onopen = function(){
            // alert("Connected to websockket server");
        }
        socket.onmessage = function (e) {
            console.log(e);
          handleMessage(e);

        }
    }

    var handleMessage = function (e) {
        var msg = JSON.parse(e.data);
        switch (msg.header.type) {
            case 0: handleNewMessage(msg); break;
            case 1: handleNewClient(msg); break;
            case 2: handleRemoveClient(msg); break;
            case 3: handleClientList(msg); break;
        }

    }

    var handleNewMessage = function (msg) {
        var map = socketHandler.clients;
        var senderId = msg.header.senderId;
        var s = map.get(senderId);
        if (s&&s) {
            s.messages = s.messages.concat('<br />' +msg.body.message);
            map.set(senderId,s);
            if (socketHandler.currentId == senderId)  $('#message').html(s.messages);
        }
    }

    var handleNewClient = function (msg) {
        var map = socketHandler.clients;
        var id = msg.body.message;
        $('#clientList').append('<li class="list-group-item" id="'+id+'">'+id+'</li>');
        var obj = {messages:'',draft: ''};
        map.set(id,obj);
        bind();
    }

    var handleRemoveClient = function (msg) {
        var map = socketHandler.clients;
        var id = msg.body.message;
        map.delete(id);
        $('#'+id).remove();

    }

    var handleClientList = function (msg) {
        var map = socketHandler.clients;
        var list = JSON.parse(msg.body.message);
        list.forEach(function (id) {
            $('#clientList').append('<li class="list-group-item" id="'+id+'">'+id+'</li>');
            var obj = {messages:'',draft: ''};
            map.set(id,obj);
        });
        bind();

    }

    this.sendFromMessageInput = function () {
        var msg = {};
        var header = {};
        var body = {};
        header.receiverId = socketHandler.currentId;
        msg.header = header;
        body.message = $("#minput").val();
        msg.body = body;
        this.sendMessage(JSON.stringify(msg));
    }

    this.sendMessage = function(message){
        socket.send(message);
    }

    this.close = function(){
        socket.disconnect();
    }
}