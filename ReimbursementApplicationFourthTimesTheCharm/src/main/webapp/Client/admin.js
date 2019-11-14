let currentUser;

function getReimbs(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            data.forEach(addRow)
            console.log(data);
        })
        .catch(console.log)
}

function addRow(reimb){
    const row = document.createElement('tr');

    const amountData = document.createElement('td');
    amountData.innerText = reimb.amount;
    row.appendChild(amountData);

    const submitData = document.createElement('td');
    submitData.innerText = reimb.submitted;
    row.appendChild(submitData);

    const resolveData = document.createElement('td');
    resolveData.innerText = reimb.resolved;
    row.appendChild(resolveData);

    const descriptData = document.createElement('td');
    descriptData.innerText = reimb.description;
    row.appendChild(descriptData);

    const authorData = document.createElement('td');
    authorData.innerText = reimb.author;
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    resolverData.innerText = reimb.resolver;
    row.appendChild(resolverData);

    const statusData = document.createElement('td');
    statusData.innerText = reimb.statusId;
    row.appendChild(statusData);

    const typeData = document.createElement('td');
    typeData.innerText = reimb.typeId;
    row.appendChild(typeData);
        
    const approveButton = document.createElement('button');
    approveButton.innerText = 'approve';
    approveButton.setAttribute('onclick', `buttonTest(${reimb.id}, 2, this.parentElement)`);
    row.appendChild(approveButton);
    
    document.getElementById('reimb-table-body').appendChild(row);
}

function buttonTest(reimbId, statusId, ele){
	console.log(reimbId);
	console.log(statusId);
	console.log(ele);
}

function setStatus(reimbId, statusId, ele){
	fetch(`http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager/${reimbId}/${statusId}`, {
		credentials: 'include',
		method: 'PATCH',
		body: JSON.stringify(currentUser.id),
		headers: {
            'content-type': 'application/json'
        }
	})
        .then(resp => resp.json())
        .then(data => {
        	
        })
        .catch(console.log)
}


function getCurrentUserInfo() {
    console.log('getting current user');
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/auth/session-user', {
        credentials: 'include'
    })
    .then(resp => resp.json())
    .then(data => {
        // document.getElementById('users-name').innerText = data.username
        // refreshTable();
        currentUser = data;
    	console.log(currentUser);
    	if(currentUser.role_id !== 1){
    		console.log('get out');
    		window.location = 'http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/Client/login.html';
    	}
    })
    .catch(err => {
    	console.log('get out');
        window.location = 'http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/Client/login.html';
    })
}

getCurrentUserInfo();
getReimbs();