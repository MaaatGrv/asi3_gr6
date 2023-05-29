
function getUserInfoFromServer() {
    var userInfo = null;
    $.ajax({
        url: "http://localhost:8090/auth/user",
        type: "GET",
        async: false,
        success: function(response) {
            userInfo = response;
        },
        error: function(error) {
            console.error("Error while fetching user info: ", error);
        }
    });
    return userInfo;
}

function createRoom(userID) {
    var roomName = $("#room-name").val();
    var bet = parseInt($("#bet-amount").val(), 10);

    // Verify that the account of the user is greater than the bet
    var userInfo = getUserInfoFromServer();
    if (userInfo.account < bet) {
        alert("You don't have enough money to create this room");
        return;
    } else {
        $.ajax({
            url: "http://localhost:8090/room/create",
            type: "POST",
            contentType: "application/json", // Set the content type to JSON
            data: JSON.stringify({           // Convert the data to a JSON string
                "roomName": roomName,
                "bet": bet,
                "userID1": userID
            }),
            success: function(response) {
                // Récupération de l'id de la room créée
                var roomId = response.roomId;
                // Redirection vers la page select_card avec le roomId dans l'URL
                window.location.href = '/wait.html?roomId=' + roomId;
            },
            error: function(error) {
                console.error("Error while creating room: ", error);
            }
        });
    }

}


$(document).on("click", "#create-room-button", function(event) {
    event.preventDefault();
    var userInfo = getUserInfoFromServer();
    createRoom(userInfo.id);
});

