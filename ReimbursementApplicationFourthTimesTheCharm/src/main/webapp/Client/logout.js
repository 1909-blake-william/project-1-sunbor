function logout(event){
    event.preventDefault();
    
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/auth/logout', {
        method: 'POST',
        headers: {
            'content-type': 'application/json'
        },
        credentials: 'include',     //use this to use session info
        //body: JSON.stringify(credential)
    })
    .then(resp => resp.json())
    .then(data => {
        console.log(data);
        window.location = 'http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/Client/login.html';
    })
    .catch(err => {
        console.log(err);
        //window.location = '/login.html';
    })
}