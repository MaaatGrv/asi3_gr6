function getRoomIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('roomId');
}

var roomId = getRoomIdFromUrl(); // Cette fonction doit récupérer l'ID de la salle à partir de l'URL
var checkRoomInterval;

function checkRoom() {
    $.ajax({
        url: "http://localhost:8090/room/" + roomId,
        type: "GET",
        success: function(room) {
            if (!(room.open)) {
                clearInterval(checkRoomInterval);
                window.location.href = '/select_card.html?roomId=' + roomId;
            }
        },
        error: function(error) {
            console.error("Error while checking room status: ", error);
        }
    });
}

$(document).ready(function() {
    checkRoomInterval = setInterval(checkRoom, 1000);
});
