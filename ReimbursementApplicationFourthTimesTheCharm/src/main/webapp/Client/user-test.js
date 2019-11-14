

function getCurrentUserInfo(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/user', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            data.forEach(addName)
            document.getElementById('test-here').innerText = data
            console.log(data);
        })
        .catch(console.log)
}

getCurrentUserInfo();

function addName(user){
    let p = document.createElement('p');
    p.innerText = user.username;
    document.body.appendChild(p);

}