let currentUser;

async function login(event){
    event.preventDefault();
    
    const username = document.getElementById('username-input').value;
    const password = document.getElementById('password-input').value;
    const credential = {
        username,
        password
    };

    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/auth/login', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        credentials: 'include',     //use this to use session info
        body: JSON.stringify(credential)
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        currentUser = data;
        console.log('userInfo function ' + currentUser.role_id);
        window.location = currentUser.role_id === 1 ? '/admin.html' : '/user.html';
    })
    .catch(err => {
        console.log(err);
        document.getElementById('error-message').innerText = 'Failed to login';
        //window.location = '/login.html';
    })
}

// function getCurrentUser(){
//     const response = fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/auth/session-user');
//     const myJson = response.json();
//     console.log('tryouts' + JSON.stringify(myJSON));
// }

// function getCurrentUserInfo() {
//     //console.log('test');
//     fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/auth/session-user', {
//         credentials: 'include'
//     })
//     .then(resp => resp.json())
//     .then(data => {
//         //document.getElementById('users-name').innerText = data.username
//         //refreshTable();
//         currentUser = data;
//         console.log('userInfo function ' + currentUser.username);
//     })
//     .catch(err => {
//         window.location = '/login.html';
//     })
// }