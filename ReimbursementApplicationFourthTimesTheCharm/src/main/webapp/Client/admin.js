let currentUser;
let data;
let currentView;

function getReimbs(){
    document.getElementById('reimb-table-body').innerHTML = "";
	
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            data.forEach(addRow)
            console.log(data);
        })
        .catch(console.log)
    
    currentView = 0;
}

function getReimbsByStatus(statusId){
    document.getElementById('reimb-table-body').innerHTML = "";
	
    const reimbId = 0;
    const resolverId = 0;
    
	const upData = {
			reimbId,
			statusId,
			resolverId
	}
    
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager/filter', {
        credentials: 'include',
        method: 'POST',
		body: JSON.stringify(upData),
		headers: {
            'content-type': 'application/json'
        }
    })
        .then(resp => resp.json())
        .then(data => {
            data.forEach(addRow)
            console.log(data);
        })
        .catch(console.log)
	
	currentView = statusId;
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
    approveButton.setAttribute('onclick', `setStatus(${reimb.id}, 2, this.parentElement)`);
    if(reimb.statusId !== 1){
    	approveButton.disabled = true;
    }
    row.appendChild(approveButton);
    
    const denyButton = document.createElement('button');
    denyButton.innerText = 'deny';
    denyButton.setAttribute('onclick', `setStatus(${reimb.id}, 3, this.parentElement)`);
    if(reimb.statusId !== 1){
    	denyButton.disabled = true;
    }
    row.appendChild(denyButton);
    
    document.getElementById('reimb-table-body').appendChild(row);
        
}

function buttonTest(reimbId, statusId, ele){
	console.log(reimbId);
	console.log(statusId);
	console.log(ele);
	console.log(currentUser.id);
}

function setStatus(reimbId, statusId, ele){
	const resolverId = currentUser.id;
	const upData = {
			reimbId,
			statusId,
			resolverId
	}
	
	fetch(`http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/manager/${reimbId}/${statusId}`, {
		credentials: 'include',
		method: 'PUT',
		body: JSON.stringify(upData),
		headers: {
            'content-type': 'application/json'
        }
	})
//        .then(resp => resp.json())
//        .then(data => {
//        	console.log(data);
//        })
		.then(() => {
			console.log('fetch successful');
			if(currentView === 0){
				//getReimbs();
				ele.childNodes[2].innerText = new Date();
				ele.childNodes[5].innerText = currentUser.id;
				ele.childNodes[6].innerText = statusId;
				ele.childNodes[8].disabled = true;
				ele.childNodes[9].disabled = true;
			}
			else{
				//getReimbsByStatus(currentView);
				  let i = ele.rowIndex;
				  document.getElementById("reimb-table").deleteRow(i);
			}
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