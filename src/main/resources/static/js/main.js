window.onload = function(){
    socketHandler.connect();
}

var bind = function () {
    $("#clientList").hover(function (e) {
        e.preventDefault();
        $(this).css('background-color','cyan');
    },
        function () {
            $(this).css('background-color','');
        });
    $('#clientList li').click(function (e) {
        e.preventDefault();
        var id = $(this).attr('id');
        socketHandler.currentId = id;
        $('#message').html(socketHandler.clients.get(id).message);
    })


    }
