// Récupère l'élément input et ajoute un événement au bouton de recherche
const searchInput = document.getElementById('searchInput');
const searchButton = document.querySelector('.ui.icon.input i.search.link.icon');
searchButton.addEventListener('click', searchCards);

// Fonction pour rechercher les cartes
function searchCards() {
  // Récupère l'ID de la carte à afficher depuis l'input
  const cardId = searchInput.value;

  // Envoie une requête GET pour récupérer les données de la carte
  fetch(`http://vps.cpe-sn.fr:8083/card/${cardId}`)
    .then(response => response.json())
    .then(data => {
      // Crée un élément HTML pour afficher la carte
      const cardElement = document.createElement('div');
      cardElement.classList.add('column');
      cardElement.innerHTML = `
        <div class="ui card">
          <div class="image">
            <img src="${data.imgUrl}">
          </div>
          <div class="content">
            <a class="header">${data.name}</a>
            <div class="description">
              <p>${data.description}</p>
            </div>
          </div>
          <div class="extra content">
            <span class="right floated">
              <i class="dollar sign icon"></i>
              ${data.price}
            </span>
            <span>
              <i class="heartbeat icon"></i>
              ${data.hp}
            </span>
          </div>
        </div>
      `;

      // Ajoute l'élément à la grille
      const gridContainer = document.getElementById('gridContainer');
      gridContainer.innerHTML = '';
      gridContainer.appendChild(cardElement);
    })
    .catch(error => console.error(error));
}