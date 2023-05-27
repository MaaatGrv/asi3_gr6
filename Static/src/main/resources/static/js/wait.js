$(document).ready(function() {
    var roomId = getQueryVariable('roomId');
    checkPlayers(roomId);
});

function getQueryVariable(variable) {
    var query = window.location.search.substring(1);
    var vars = query.split("&");
    for (var i=0;i<vars.length;i++) {
        var pair = vars[i].split("=");
        if (pair[0] == variable) {
            return pair[1];
        }
    }
    return(false);
}

function checkPlayers(roomId) {
    $.ajax({
        url: "http://localhost:8090/game/check-players/" + roomId,
        type: "GET",
        success: function(response) {
            if (response.allPlayersJoined) {
                window.location.href = '/select_card_to_play.html?roomId=' + roomId;
            } else {
                setTimeout(function() {
                    checkPlayers(roomId);
                }, 3000);
            }
        },
        error: function(error) {
            console.error("Error while checking players: ", error);
        }
    });
}
