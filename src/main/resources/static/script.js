// Set the current year in the footer
document.getElementById("currentYear").textContent = new Date().getFullYear();


// Function to show Create User Page
function showCreateUserPage() {
    document.getElementById('homePage').style.display = 'none';
    document.getElementById('createUserPage').style.display = 'block';
}


// Function to reset matches view
function resetMatchesView() {
    document.getElementById('userId').value = '';
    document.getElementById('matchesList').innerHTML = '';
    document.getElementById('errorMessage').style.display = 'none'; // Hide error message
}

// Function to show home page and hide others
function showHomePage() {
    document.getElementById('homePage').style.display = 'block';
    document.getElementById('createUserPage').style.display = 'none';
    document.getElementById('viewMatchesPage').style.display = 'none';
    resetMatchesView(); // Reset matches when returning home
}

// Handle browser back button - directly go home without confirmation
window.onpopstate = function(event) {
    history.pushState(null, '', window.location.pathname);
    showHomePage();
};

// Push initial state
history.pushState(null, '', window.location.pathname);

// Navigation to Add/Create User page
document.getElementById('addUserButton').addEventListener('click', function() {
    document.getElementById('homePage').style.display = 'none';
    document.getElementById('createUserPage').style.display = 'block';
    history.pushState(null, '', window.location.pathname);

    // Change the SVG to Love.svg on the Create User Page
    document.getElementById('mainImage').src = '/images/Love.svg'; // Change the image to Love.svg
});

// Navigation to View Matches page
document.getElementById('viewMatchesButton').addEventListener('click', function() {
    document.getElementById('homePage').style.display = 'none';
    document.getElementById('viewMatchesPage').style.display = 'block';
    resetMatchesView(); // Reset matches when opening view
    history.pushState(null, '', window.location.pathname);

    // Change the SVG to Matches.svg on the View Matches Page
    document.getElementById('mainImage').src = '/images/Matches.svg'; // Change the image to Matches.svg
});

// Back to Home from Create User
document.getElementById('backToHomeFromCreate').addEventListener('click', function() {
    showHomePage();

    // Change the SVG back to Testimonials.svg on the Home Page
    document.getElementById('mainImage').src = '/images/Testimonials.svg'; // Change the image back to Testimonials.svg
});

// Back to Home from View Matches
document.getElementById('backToHomeFromMatches').addEventListener('click', function() {
    showHomePage();

    // Change the SVG back to Testimonials.svg on the Home Page
    document.getElementById('mainImage').src = '/images/Testimonials.svg'; // Change the image back to Testimonials.svg
});

// Handle Create User Form Submission
document.getElementById('createUserForm').addEventListener('submit', function(event) {
    event.preventDefault();

    const name = document.getElementById('name').value.trim();
    const gender = document.getElementById('gender').value.trim();
    const age = parseInt(document.getElementById('age').value.trim());
    const interests = document.getElementById('interests').value.trim().split(',');

    // Validation logic
    if (!name || !gender || isNaN(age) || interests.length === 0) {
        alert("All fields are required!");
        return;
    }
    if (!["Male", "Female"].includes(gender)) {
        alert("Gender must be Male or Female");
        return;
    }
    if (age <= 0 || age < 18 || age > 100) {
        alert("Please enter a valid age between 18 and 100");
        return;
    }

    const user = { name, gender, age, interests };

    fetch('http://localhost:8080/api/users/create', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(user),
    })
	 .then(response => response.text())  // Using .text() to handle plain text response
	    .then(message => {
	        document.getElementById('successMessage').style.display = 'block';
	        document.getElementById('successMessage').textContent = message;  // Display user ID message
	        document.getElementById('createUserForm').reset();
	        setTimeout(() => {
	            document.getElementById('successMessage').style.display = 'none';
	        }, 2000);
	    })
	    .catch(error => alert('Error: ' + error));
	});

// Fetch Matches by User ID
document.getElementById('getMatchesButton').addEventListener('click', function() {
    const userId = document.getElementById('userId').value.trim();

    if (!userId || isNaN(userId)) {
        const errorMessage = document.getElementById('errorMessage');
        errorMessage.style.display = 'block';
        errorMessage.textContent = 'Please enter a valid User ID';
        return;
    }

    document.getElementById('loadingSpinner').style.display = 'block';
    document.getElementById('matchesList').style.display = 'none';

    fetch(`http://localhost:8080/api/users/${userId}/matches`)
    .then(response => {
        if (!response.ok) throw new Error('Failed to fetch matches');
        return response.json();
    })
    .then(matches => {
        document.getElementById('loadingSpinner').style.display = 'none';
        const matchesList = document.getElementById('matchesList');
        matchesList.style.display = 'block';
        matchesList.innerHTML = ''; // Clear previous results

        if (matches && matches.length > 0) {
            matches.forEach(match => {
                const card = document.createElement('div');
                card.className = 'match-card custom-font';
                card.innerHTML = `
                    <h3>${match.name}</h3>
                    <p>Age: ${match.age}</p>
                    <p>Gender: ${match.gender}</p>
                `;
                matchesList.appendChild(card);
            });
        } else {
            matchesList.innerHTML = '<li>No matches found</li>';
        }
    })
    .catch(error => {
        document.getElementById('loadingSpinner').style.display = 'none';
        const errorMessage = document.getElementById('errorMessage');
        errorMessage.style.display = 'block';
        errorMessage.textContent = error.message;
    });
});
