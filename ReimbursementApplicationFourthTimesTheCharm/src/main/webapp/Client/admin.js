let currentUser;
let data;
let currentView;
let userMap = new Map();
let statusMap = new Map();
let typeMap = new Map();

function getUserMap(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/user', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(mapData => {
            mapData.forEach(function(mapData) {
            	console.log(mapData.id);
            	console.log(mapData);
            	let name = mapData.first_name + ' ' + mapData.last_name;
            	userMap.set(mapData.id, name);
            });
            console.log('1');
            console.log(userMap);
        })
        .catch(console.log)
}

function getStatusMap(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/lookup/status', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(statusData => {
            statusData.forEach(function(statusData) {
            	statusMap.set(statusData.statusId, statusData.status);
            });
            console.log('2');
            console.log(statusMap);
        })
        .catch(console.log)
}

function getTypeMap(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/lookup/type', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(typeData => {
            typeData.forEach(function(typeData) {
            	typeMap.set(typeData.typeId, typeData.type);
            });
            console.log('3');
            console.log('typeMap');
            console.log(typeMap);
        })
        .catch(console.log)
}

function getReimbs(){
	console.log('making table');
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
            //console.log(data);
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
    //authorData.innerText = reimb.author;
    let authorId = reimb.author;
//    console.log(authorId);
//    console.log(userMap.get(authorId));
//    authorData.innerText = userMap.get(authorId).first_name + ' ' + userMap.get(authorId).last_name;
    authorData.innerText = userMap.get(authorId);
    row.appendChild(authorData);

    const resolverData = document.createElement('td');
    //resolverData.innerText = reimb.resolver;
    let resolverId = reimb.resolver;
    if(resolverId !== 0){
    	//resolverData.innerText = userMap.get(resolverId).first_name + ' ' + userMap.get(resolverId).last_name;
    	resolverData.innerText = userMap.get(resolverId);
    }
    row.appendChild(resolverData);

    const statusData = document.createElement('td');
    statusData.innerText = statusMap.get(reimb.statusId);
    row.appendChild(statusData);

    const typeData = document.createElement('td');
    typeData.innerText = typeMap.get(reimb.typeId);
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
				ele.childNodes[2].innerText = new Date().getTime();
				let resolverId = currentUser.id;
				ele.childNodes[5].innerText = userMap.get(resolverId);
				ele.childNodes[6].innerText = statusMap.get(statusId);
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
    .then(currentUserData => {
        // document.getElementById('users-name').innerText = data.username
        // refreshTable();
        currentUser = currentUserData;
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

//getCurrentUserInfo();
//getUserMap();
//getStatusMap();
//getTypeMap();
//getReimbs();

//function setup(callback){
//	getCurrentUserInfo();
//	getUserMap();
//	getStatusMap();
//	getTypeMap();
//	callback();
//
//}
//
//setup(getReimbs);

getCurrentUserInfo();
getUserMap();
getStatusMap();
getTypeMap();
setTimeout(getReimbs, 500);