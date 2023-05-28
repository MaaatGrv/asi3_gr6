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

function getRoomIdFromUrl() {
    const urlParams = new URLSearchParams(window.location.search);
    return urlParams.get('roomId');
}

function loadUserCards() {
    $.ajax({
        url: "http://localhost:8090/auth/user",
        type: "GET",
        success: function(user) {
            if (user && user.id) {
                $.ajax({
                    url: "http://localhost:8090/user/" + user.id + "/cards",
                    type: "GET",
                    success: function(cards) {
                        displayCards(cards);
                        if (cards.length > 0) {
                            loadCardDetails(cards[0].id);
                        }
                    },
                    error: function(error) {
                        console.error("Error while fetching user cards: ", error);
                    }
                });
            }
        },
        error: function(error) {
            console.error("Error while fetching logged in user: ", error);
        }
    });
}

function displayCards(cards) {
    var tableContent = $("#tableContent");
    var rowTemplate = $("#row");

    cards.forEach(function(card) {
        var row = rowTemplate.html()
            .replace(/{{img_src}}/g, card.imgUrl)
            .replace(/{{name}}/g, card.name)
            .replace(/{{description}}/g, card.description)
            .replace(/{{family_name}}/g, card.family)
            .replace(/{{hp}}/g, card.hp)
            .replace(/{{energy}}/g, card.energy)
            .replace(/{{defense}}/g, card.defence)
            .replace(/{{attack}}/g, card.attack)
            .replace(/{{price}}/g, card.price)
            .replace(/{{card_id}}/g, card.id);
        tableContent.append(row);
    });

    if (cards.length > 0) {
        $("#tableContent tr:first").addClass("selected");
    }
}

function loadCardDetails(cardId) {
    $.ajax({
        url: "http://localhost:8090/card/" + cardId,
        type: "GET",       
        success: function(card) {
            updateCard(card);
        },
        error: function(error) {
            console.log(cardId);
            console.error("Error while fetching card details: ", error);
        }
    });
}

function updateCard(card) {
    $("#cardHPId").text(card.hp);
    $("h5").text(card.family);
    $("#energyId").text(card.energy);
    $("#cardImgId").attr("src", card.imgUrl);
    $("#cardNameId").text(card.name);
    $("#cardDescriptionId").text(card.description);
    $("#cardHPId").text(card.hp);
    $("#cardEnergyId").text("Energy " + card.energy);
    $("#cardAttackId").text("Attack " + card.attack);
    $("#cardDefenceId").text("Defense " + card.defence);
    $("#cardPriceId").text(card.price + "$");
}

$(document).ready(function() {
    $("#card").load("/part/card-full.html", function() {
        loadUserCards();
    });
});

$(document).on("click", "#tableContent tr", function() {
    $("#tableContent tr").removeClass("selected");
    $(this).addClass("selected");
    var cardId = $(this).data("card-id");
    loadCardDetails(cardId);
});

$(document).ready(function() {
    let countdownNumber = 60;
    const countdownElement = document.getElementById('countdown-number');
    let selectedCard = null;
  
    $('.play-button, #play-button').click(function() {
        if ($(this).closest('tr').length) {
            selectedCard = $(this).closest('tr').data('card-id');
        } else {
            selectedCard = $("#tableContent tr.selected").data("card-id");
        }
        sendSelectedCard();
    });

    $(document).on("click", ".play-button", function() {
        selectedCard = $("#tableContent tr.selected").data("card-id");
        sendSelectedCard();
    });
    
    function sendSelectedCard() {
        if (selectedCard !== null) {
            const roomId = getRoomIdFromUrl();
            const userId = getUserInfoFromServer().id;
            $.ajax({
                url: "http://localhost:8090/room/start/" + roomId,
                type: "POST",
                data: {
                    cardId: selectedCard,
                    userId: userId
                },
                success: function(response) {
                    console.log("Selected card sent");
                    window.location.href = '/waitCard.html?roomId=' + roomId;
                },
                error: function(error) {
                    console.error("Error while sending selected card: ", error);
                }
            });
        }
    }
  
    function countdown() {
        countdownNumber--;
        countdownElement.textContent = countdownNumber;
        if (countdownNumber <= 0) {
            selectRandomCard();
            sendSelectedCard();
        } else {
            setTimeout(countdown, 1000);
        }
    }
  
    function selectRandomCard() {
        const cards = $('tr[data-card-id]');
        const randomIndex = Math.floor(Math.random() * cards.length);
        selectedCard = $(cards[randomIndex]).data('card-id');
    }
  
    // countdown();
});