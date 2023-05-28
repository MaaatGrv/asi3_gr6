$(document).ready(function() {
    fetchRoomList();
});


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

function fetchRoomList() {
    $.ajax({
        url: "http://localhost:8090/room/list",
        type: "GET",
        success: function(response) {
            populateRoomList(response);
        },
        error: function(error) {
            console.error("Error while fetching rooms: ", error);
        }
    });
}

function populateRoomList(rooms) {
    var roomListContent = $("#roomListContent");
    rooms.forEach(function(room) {
        if (room.open == true) {
            var row = $('<tr>');
            row.append($('<td>').text(room.roomName));
            fetchUser(room.userID1, function(user) {
                row.append($('<td>').text(user.login));
                row.append($('<td>').text(room.bet));
                var goCell = $('<td>');
                var goButton = $('<button>').addClass('ui button').text('Go');
                goButton.click(function() {
                    console.log("Joining room", room.roomId);
                    joinRoom(room.roomId);
                });
                goCell.append(goButton);
                row.append(goCell);
                roomListContent.append(row);
            });
        }
    });
}

function fetchUser(userId, callback) {
    $.ajax({
        url: `http://localhost:8090/user/${userId}`,
        method: 'GET',
        success: function(data) {
            console.log("User data fetched", data);
            callback(data);
        },
        error: function(err) {
            console.error("Failed to fetch user data", err);
        }
    });
}

function joinRoom(roomId) {
    var userId = getUserInfoFromServer().id; // Assurez-vous que cette fonction retourne le bon ID utilisateur
    // Ici, nous n'avons pas encore sélectionné de carte, donc pas besoin de cardId
    $.ajax({
        url: "http://localhost:8090/room/join/" + roomId,
        type: "POST",
        data: {
            userId: userId,
        },
        success: function(response) {
            // Redirection vers la page select_card avec le roomId dans l'URL
            window.location.href = '/select_card.html?roomId=' + roomId;
        },
        error: function(error) {
            console.error("Error while joining room: ", error);
        }
    });
}