function getUserInfoFromServer() {
    var userInfo = null;
    $.ajax({
        url: "http://localhost:8090/auth/user",
        type: "GET",
        async: false,
        success: function(response) {
            userInfo = response;
            document.getElementById('user-account').textContent = userInfo.account;
            document.getElementById('user-login').textContent = userInfo.login;
        },
        error: function(error) {
            console.error("Error while fetching user info: ", error);
        }
    });
    return userInfo;
}

function getRoomInfoFromServer(roomId) {
    var roomInfo = null;
    $.ajax({
        url: "http://localhost:8090/room/" + roomId,
        type: "GET",
        async: false,
        success: function(response) {
            roomInfo = response;
        },
        error: function(error) {
            console.error("Error while fetching room info: ", error);
        }
    });
    return roomInfo;
}

function startGame(roomId) {
    $.ajax({
        url: "http://localhost:8090/game/start/" + roomId,
        type: "POST",
        success: function(game) {
            if (!(game.isOver)) {
                console.log(game.currentPlayerId);
                // Don't automatically attack
                updateGame(game);
            } else {
                console.log("Game is over");
                console.log("Winner: ", game.winnerId);
            }
        },
        error: function(error) {
            console.error("Error while starting the game: ", error);
        }
    });
}

function attack(gameId, userId) {
    $.ajax({
        url: "http://localhost:8090/game/attack/" + gameId + "?userId=" + userId,
        type: "POST",
        success: function(game) {
            console.log("Game after attack: ", game);
            updateGame(game);
        },
        error: function(error) {
            console.error("Error while attacking: ", error);
        }
    });
}


function getUserInfoFromRoom(userId) {
    var userInfo = null;
    $.ajax({
        url: "http://localhost:8090/user/" + userId,
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

function getCardFromServer(cardId) {
    var card = null;
    $.ajax({
        url: "http://localhost:8090/card/" + cardId,
        type: "GET",
        async: false,
        success: function(response) {
            card = response;
        },
        error: function(error) {
            console.error("Error while fetching card: ", error);
        }
    });
    return card;
}

function getRoomIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('roomId');
}

function loadRoom(roomId) {
    $.ajax({
        url: "http://localhost:8090/room/" + roomId,
        type: "GET",
        success: function(room) {
            updateRoom(room);
            return room;
        },
        error: function(error) {
            console.error("Error while fetching game details: ", error);
        }
    });
}

function loadGame(gameId) {
    var game = null;
    $.ajax({
        url: "http://localhost:8090/game/" + gameId,
        type: "GET",
        async: false,
        success: function(response) {
            game = response;
        },
        error: function(error) {
            console.error("Error while fetching game details: ", error);
        }
    });
    return game;
}



function updateRoom(room) {
    // Get User Infor from server
    var User1 = getUserInfoFromServer();
    // Get Other User from room
    var userID2 = null;

    if (User1.id == room.userID1) {
        userID2 = room.userID2;
    } else {
        userID2 = room.userID1;
    }

    var User2 = getUserInfoFromRoom(userID2);

    $("#player1-name").text(User1.login);
    $("#player2-name").text(User2.login);

    var Card1 = getCardFromServer(room.cardID1);
    var Card2 = getCardFromServer(room.cardID2);

    updateCard(Card1, "#player1-card");
    updateCard(Card2, "#player2-card");
}

function updateGame(game) {
    $("#player1-card-HP").text(game.cardID1CurrentHP);
    $("#player1-card-Energy").text(game.cardID1Energy);
    $("#player2-card-HP").text(game.cardID2CurrentHP);
    $("#player2-card-Energy").text(game.cardID2Energy);
}

function updateCard(card, cardElementId) {
    $(cardElementId).empty();

    let cardTemplate = `
        <div class="ui special cards" style="max-width: 70%;">
            <div class="card">
                <div class="content">
                    <div class="ui grid">
                        <div class="three column row">
                            <div class="column">
                                <i class="heart outline icon"></i><span id="${cardElementId}-HP">${card.hp}</span> 
                            </div>
                            <div class="column">
                                <h5>${card.name}</h5>
                            </div>
                            <div class="column">
                                <span id="${cardElementId}-Energy">${card.energy}</span> <i class="lightning icon"></i>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="image">
                    <div class="blurring dimmable image">
                        <div class="ui fluid image">
                            <img id="${cardElementId}-Image" class="ui centered image" src="${card.imgUrl}">
                        </div>
                    </div>
                </div>
                <div class="content">
                    <i class="heart outline icon"></i><span id="${cardElementId}-HP2"> HP ${card.hp}</span> 
                    <div class="right floated ">
                        <span id="${cardElementId}-Energy2">Energy ${card.energy}</span>
                        <i class="lightning icon"></i>
                    </div>
                </div>
                <div class="content">
                    <span class="right floated">
                        <span id="${cardElementId}-Attack"> Attack ${card.attack}</span> 
                        <i class=" wizard icon"></i>
                    </span>
                    <i class="protect icon"></i>
                    <span id="${cardElementId}-Defense">Defense ${card.defence}</span> 
                </div>
            </div>
        </div>
    `;

    $(cardElementId).append(cardTemplate);
}

$(document).ready(function() {
    userInfo = getUserInfoFromServer();

    var roomId = getRoomIdFromUrl();
    roomInfo = getRoomInfoFromServer(roomId);
    loadRoom(roomId);

    if (roomInfo.gameId == null) {
        startGame(roomId);
    } 

    // Boucle du jeu (toutes les 15 secondes)
    setInterval(function() {
        gameInfo = loadGame(roomInfo.gameId);
        if (roomInfo.userID1 == userInfo.id) {
            console.log("Current player: ", gameInfo.currentPlayerId);
            attack(gameInfo.gameId, gameInfo.currentPlayerId);
            
        }
        updateGame(gameInfo);
    }, 2000);
});
