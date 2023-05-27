
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
    console.log("Creating room with name: ", roomName, " and bet: ", bet, " and userID: ", userID);
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
            console.log("Room created: ", response);
        },
        error: function(error) {
            console.error("Error while creating room: ", error);
        }
    });
}


$(document).on("click", "#create-room-button", function(event) {
    event.preventDefault();
    var userInfo = getUserInfoFromServer();
    createRoom(userInfo.id);
});

