document.addEventListener('DOMContentLoaded', () => {
    const API_URL_AUTH = 'http://localhost:8090/auth';
    const API_URL_USER = 'http://localhost:8090/user';

    const showLoginFormButton = document.getElementById('show-login-form');
    const showRegisterFormButton = document.getElementById('show-register-form');
    const loginForm = document.getElementById('login-form');
    const registerForm = document.getElementById('register-form');

    showLoginFormButton.addEventListener('click', () => {
        loginForm.style.display = 'block';
        registerForm.style.display = 'none';
    });

    showRegisterFormButton.addEventListener('click', () => {
        loginForm.style.display = 'none';
        registerForm.style.display = 'block';
    });

    loginForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const login = loginForm.elements['login'].value;
        const pwd = loginForm.elements['pwd'].value;

        const loginData = {
            login: login,
            pwd: pwd
        };

        fetch(API_URL_AUTH, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(loginData)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else {
                throw new Error('Unauthorized');
            }
        })
        .then(data => {
            console.log('Logged in:', data);
            alert('Logged in successfully!');
            // Redirect to home.html
            window.location.href = '/home.html';


        })
        .catch(error => {
            console.error('Error:', error);
            alert('Invalid username or password.');
        });
    });

    registerForm.addEventListener('submit', (event) => {
        event.preventDefault();

        const login = registerForm.elements['login'].value;
        const pwd = registerForm.elements['pwd'].value;
        const lastName = registerForm.elements['lastName'].value;
        const surName = registerForm.elements['surName'].value;
        const email = registerForm.elements['email'].value;

        const userData = {
            login: login,
            pwd: pwd,
            lastName: lastName,
            surName: surName,
            email: email
        };

        fetch(API_URL_USER, {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(userData)
        })
        .then(response => {
            if (response.ok) {
                return response.json();
            } else if (response.status === 409) {
                throw new Error('Conflict');
            } else {
                throw new Error('Error');
            }
        })
        .then(data => {
            console.log('User created:', data);
            alert('User created successfully!');
            // Redirect to home.html
            window.location.href = '/home.html';
        })
        .catch(error => {
            console.error('Error:', error);
            if (error.message === 'Conflict') {
                alert('Username already exists.');
            } else {
                alert('An error occurred while registering.');
            }
        });
    });
});
