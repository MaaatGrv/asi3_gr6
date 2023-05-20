document.addEventListener('DOMContentLoaded', () => {
    console.log("Running card.js");

    const apiUrl = '/cards';

    function displayCards(cards) {
        let template = document.querySelector("#selectedCard");
        let cardContainer = document.querySelector("#gridContainer");
        cardContainer.innerHTML = '';

        let rowCount = 0;
        let row;

        cards.forEach((card, index) => {
            if (index % 3 === 0) {
                rowCount++;
                row = document.createElement('div');
                row.classList.add('row');
                cardContainer.appendChild(row);
            }

            let column = document.createElement('div');
            column.classList.add('column');
            column.dataset.cardId = card.id;
            row.appendChild(column);

            let clone = document.importNode(template.content, true);

            newContent = clone.firstElementChild.innerHTML
                .replace(/{{family_src}}/g, card.smallImgUrl)
                .replace(/{{family_name}}/g, card.family)
                .replace(/{{image_src}}/g, card.imgUrl)
                .replace(/{{date}}/g, card.energy)
                .replace(/{{comment}}/g, `${card.hp.toFixed(2)} HP`)
                .replace(/{{like}}/g, `${card.attack.toFixed(2)} Attack`)
                .replace(/{{button}}/g, `Buy for ${card.price.toFixed(2)}€`);
            clone.firstElementChild.innerHTML = newContent;

            column.appendChild(clone);
        });
    }

    function fetchCards() {
        fetch(apiUrl)
            .then(response => response.json())
            .then(data => {
                displayCards(data);
            })
            .catch(error => console.error(error));
    }

    function searchCards() {
        const searchValue = document.getElementById('searchInput').value.trim();

        if (searchValue) {
            fetch(`http://vps.cpe-sn.fr:8083/card/${searchValue}`)
                .then(response => response.json())
                .then(data => {
                    displayCards([data]);
                })
                .catch(error => console.error(error));
        } else {
            fetchCards();
        }
    }

    // Charge et affiche les cartes au chargement de la page
    fetchCards();

    // Ajoute l'événement d'écoute pour la recherche
    document.getElementById('searchIcon').addEventListener('click', searchCards);
});
