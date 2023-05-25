// Get the user data from the server
function getUserData() {
    fetch('http://localhost:8090/auth/user', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Not authorized');
            }
        })
        .then(data => {
            document.getElementById('user-account').textContent = data.account;
            document.getElementById('user-login').textContent = data.login;
        })
        .catch(error => {
            console.error('Error:', error);
            window.location.replace('/login.html');
        });
}

// Logout function
function logout() {
    fetch('http://localhost:8090/auth/logout', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (response.ok) {
                window.location.replace('/login.html');
            } else {
                console.error('Logout failed');
            }
        })
        .catch(error => console.error('Error:', error));
}

// Add event listener for the logout button
document.getElementById('logout-btn').addEventListener('click', logout);

// Get user data on page load
getUserData();
