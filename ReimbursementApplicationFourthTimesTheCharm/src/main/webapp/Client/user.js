let currentUser;
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
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/employee', {
        credentials: 'include'
    })
        .then(resp => resp.json())
        .then(data => {
            data.forEach(addRow)
            console.log(data);
        })
        .catch(console.log)
}

function newReimb(event){
    event.preventDefault();

    const reimb = readReimbInput();

    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement/employee', {
    	credentials: 'include',
        method: 'POST',
        body: JSON.stringify(reimb),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => {
        addRow(data);
        console.log(data);
    })
    .catch(err => console.log(err));
}

function readReimbInput(){
    const reimbAmount = document.getElementById('amount-input').value;
    const reimbDescription = document.getElementById('description-input').value;
    const reimbType = document.getElementById('type-input').value;

    const reimb = {
        id: 0,
        amount: reimbAmount,
        submitted: new Date().getTime(),
        resolved: null,
        description: reimbDescription,
        receipt: null,
        author: currentUser.id,
        resolver: null,
        statusId: 1,
        typeId: reimbType
    }
    console.log(reimb);
    return reimb;
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

    const resolverData = document.createElement('td');
    //resolverData.innerText = reimb.resolver;
    let resolverId = reimb.resolver;
    if(resolverId !== 0){
    	//resolverData.innerText = userMap.get(resolverId).first_name + ' ' + userMap.get(resolverId).last_name;
    	resolverData.innerText = userMap.get(resolverId);
    }
    else{
    	resolverData.innerText = null;
    }
    row.appendChild(resolverData);

    const statusData = document.createElement('td');
    statusData.innerText = statusMap.get(reimb.statusId);
    row.appendChild(statusData);

    const typeData = document.createElement('td');
    typeData.innerText = typeMap.get(reimb.typeId);
    row.appendChild(typeData);

    document.getElementById('reimb-table-body').appendChild(row);
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
        if(currentUser === null){
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
//}
//
//setup(getReimbs);

getCurrentUserInfo();
getUserMap();
getStatusMap();
getTypeMap();
setTimeout(getReimbs, 500);