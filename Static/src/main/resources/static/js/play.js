$(document).ready(function() {
    fetchRoomList();
});

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
        var row = $('<tr>');
        row.append($('<td>').text(room.roomName));
        fetchUser(room.userID1, function(user) {
            row.append($('<td>').text(user.login));
            row.append($('<td>').text(room.bet));
            var goCell = $('<td>');
            var goButton = $('<button>').addClass('ui button').text('Go');
            goButton.click(function() {
                joinRoom(room.roomId);
            });
            goCell.append(goButton);
            row.append(goCell);
            roomListContent.append(row);
        });
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
    var userId = getUserInfoFromServer().id;
    var cardId = getSelectedCardId(); // Vous devez écrire cette fonction pour récupérer l'ID de la carte sélectionnée par l'utilisateur
    $.ajax({
        url: "http://localhost:8090/room/join/" + roomId,
        type: "POST",
        data: {
            userId: userId,
            cardId: cardId
        },
        success: function(response) {
            window.location.href = '/wait.html?roomId=' + roomId;
        },
        error: function(error) {
            console.error("Error while joining room: ", error);
        }
    });
}