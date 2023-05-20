function loadCardsForSale() {
    $.ajax({
        url: "/cards_to_sell",
        type: "GET",
        success: function(cards) {
            displayCards(cards);
            if (cards.length > 0) {
                loadCardDetails(cards[0].id);
            }
        },
        error: function(error) {
            console.error("Error while fetching cards for sale: ", error);
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

    // Select the first row by default.
    if (cards.length > 0) {
        $("#tableContent tr:first").addClass("selected");
    }
}

$(document).on("click", ".buy-button", function() {
    console.log("Buy button clicked");
    var cardId = $("#tableContent tr.selected").data("card-id");
    buyCard(cardId);
});

function buyCard(cardId) {
    $.ajax({
        url: "/auth/user",
        type: "GET",
        success: function(user) {
            if (user && user.id) {
                $.ajax({
                    url: "/buy-card",
                    type: "POST",
                    data: {
                        userId: user.id,
                        cardId: cardId
                    },
                    success: function() {
                        // Supprimez la ligne de la carte vendue
                        $("#tableContent tr[data-card-id='" + cardId + "']").remove();
            
                        // Sélectionnez la première carte restante et mettez à jour les détails de la carte
                        var firstCardId = $("#tableContent tr:first").addClass("selected").data("card-id");
                        if (firstCardId) {
                            loadCardDetails(firstCardId);
                        } else {
                            // Si aucune carte n'est disponible, réinitialisez les détails de la carte
                            updateCard({
                                imgUrl: "",
                                name: "",
                                description: "",
                                family: "",
                                hp: "",
                                energy: "",
                                defence: "",
                                attack: "",
                                price: ""
                            });
                        }
                        var soldCardPrice = parseFloat($("#cardPriceId").text());
                        user.account -= soldCardPrice;
                        // Mettez à jour le solde de l'utilisateur dans l'interface utilisateur (par exemple, dans un élément avec l'ID "userBalance")
                        $("#user-account").text(user.account.toFixed(2) + "$");
                    },
                    error: function(error) {
                        console.error("Error while buying the card: ", error);
                    }
                });
            }
        },
        error: function(error) {
            console.error("Error while fetching logged in user: ", error);
        }
    });
}


$(document).on("click", "#tableContent tr", function() {
    $("#tableContent tr").removeClass("selected");
    $(this).addClass("selected");
    var cardId = $(this).data("card-id");
    loadCardDetails(cardId);
});

function loadCardDetails(cardId) {
    $.ajax({
        url: "/card/" + cardId,
        type: "GET",
        success: function(card) {
            updateCard(card);
        },
        error: function(error) {
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
        loadCardsForSale();
    });
});