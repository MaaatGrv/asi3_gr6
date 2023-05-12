const API_URL = 'https://example.com/api/cards';

fetch(API_URL)
    .then(response => response.json())
    .then(data => {
        const cardTemplate = document.getElementById('cardTemplate');
        const cardContainer = document.getElementById('cardContainer');

        for (const card of data) {
            let clone = document.importNode(cardTemplate.content, true);

            let newContent = clone.firstElementChild.innerHTML
                .replace(/{{img_src}}/g, card.img_src)
                .replace(/{{name}}/g, card.name)
                .replace(/{{description}}/g, card.description)
                .replace(/{{hp}}/g, card.hp)
                .replace(/{{energy}}/g, card.energy)
                .replace(/{{attack}}/g, card.attack)
                .replace(/{{defense}}/g, card.defense);
            
            clone.firstElementChild.innerHTML = newContent;
            cardContainer.appendChild(clone);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    });
