document.addEventListener('DOMContentLoaded', () => {
    const API_URL = '/card';

    const energyProgress = document.getElementById('energyProgress');
    const hpProgress = document.getElementById('hpProgress');
    const defenceProgress = document.getElementById('defenceProgress');
    const attackProgress = document.getElementById('attackProgress');

    function updateProgressBar(progressBar, value) {
        progressBar.querySelector('.bar').style.width = `${value}%`;
        progressBar.querySelector('.label').textContent = value;
        progressBar.dataset.value = value;
    }

    [energyProgress, hpProgress, defenceProgress, attackProgress].forEach(progressBar => {
        progressBar.addEventListener('click', event => {
            const rect = progressBar.getBoundingClientRect();
            const x = event.clientX - rect.left;
            const width = rect.width;
            const value = Math.round((x / width) * 100);
            updateProgressBar(progressBar, value);

            const inputId = progressBar.id.replace('Progress', '');
            document.getElementById(inputId).value = value;
        });
    });

    document.getElementById('cardForm').addEventListener('submit', (event) => {
        event.preventDefault();

        const name = document.getElementById('name').value;
        const description = document.getElementById('description').value;
        const family = document.getElementById('family').value;
        const affinity = document.getElementById('affinity').value;
        const imgUrl = document.getElementById('imgUrl').value;
        const smallImgUrl = document.getElementById('smallImgUrl').value;
        const energy = document.getElementById('energy').value;
        const hp = document.getElementById('hp').value;
        const defence = document.getElementById('defence').value;
        const attack = document.getElementById('attack').value;
        const price = document.getElementById('price').value;
        const userId = document.getElementById('userId').value;

        const cardData = {
            name: name,
            description: description,
            family: family,
            affinity: affinity,
            imgUrl: imgUrl,
            smallImgUrl: smallImgUrl,
            energy: parseInt(energy),
            hp: parseInt(hp),
            defence: parseInt(defence),
            attack: parseInt(attack),
            price: parseInt(price),
            userId: parseInt(userId)
        };

        fetch(API_URL, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(cardData)
        })
        .then(response => response.json())
        .then(data => {
            console.log('Card created:', data);
            alert('Card created successfully!');
        })
        .catch(error => {
            console.error('Error:', error);
            alert('An error occurred while creating the card.');
        });
    });

});