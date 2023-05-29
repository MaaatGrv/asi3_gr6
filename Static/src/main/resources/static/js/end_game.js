$(document).ready(function() {
    var gameId = getGameIdFromUrl();
    var bet = getBetFromUrl();
    var user = getUserInfoFromServer();
    var userId = user.id;
    console.log("Game ID: ", gameId);
    console.log("User ID: ", userId);
    var game = getGameFromServer(gameId);

    if (game.winnerId === userId) {
        $("#endGameMessage").text("YOU WIN");
        $("#betMessage").text("You won " + bet + " coins");
    } else {
        $("#endGameMessage").text("YOU LOSE");
        $("#betMessage").text("You lost " + bet + " coins");
    }    
});

function getGameIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('gameId') == null) {
        console.error("Game ID is null");
    }
    return urlParams.get('gameId');
}

function getBetFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    if (urlParams.get('bet') == null) {
        console.error("Bet is null");
    }
    return urlParams.get('bet');
}

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

function getGameFromServer(gameId) {
    var game = null;
    $.ajax({
        url: "http://localhost:8090/game/" + gameId,
        type: "GET",
        async: false,
        success: function(response) {
            game = response;
        },
        error: function(error) {
            console.error("Error while fetching game: ", error);
        }
    });
    return game;
}

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