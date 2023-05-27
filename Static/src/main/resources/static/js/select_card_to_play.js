$(document).ready(function() {
    var roomId = getQueryVariable('roomId');
    fetchCards(roomId);
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

function fetchCards(roomId) {
    $.ajax({
        url: "http://localhost:8090/game/get-cards/" + roomId,
        type: "GET",
        success: function(response) {
            populateCards(response.cards);
        },
        error: function(error) {
            console.error("Error while fetching cards: ", error);
        }
    });
}

function populateCards(cards) {
    var cardsContainer = $('#cardsContainer');
    cards.forEach(function(card) {
        var cardElement = $('<div>').addClass('ui card').text(card.name);
        cardElement.click(function() {
            playCard(card);
        });
        cardsContainer.append(cardElement);
    });
}

function playCard(card) {
    var roomId = getQueryVariable('roomId');
    $.ajax({
        url: "http://localhost:8090/game/play-card/" + roomId,
        type: "POST",
        data: JSON.stringify(card),
        contentType: "application/json; charset=utf-8",
        success: function(response) {
            window.location.href = '/wait_for_others.html?roomId=' + roomId;
        },
        error: function(error) {
            console.error("Error while playing card: ", error);
        }
    });
}
