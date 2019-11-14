let currentUser;

function getReimbs(){
    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm/reimbursement', {
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

    fetch('http://localhost:8080/ReimbursementApplicationFourthTimesTheCharm', {
        method: 'POST',
        body: JSON.stringify(reimb),
        headers: {
            'content-type': 'application/json'
        }
    })
    .then(res => res.json())
    .then(data => {
        addRow(reimb);
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
        submitted: new Date(),
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
    })
    .catch(err => {
        window.location = '/login.html';
    })
}

getCurrentUserInfo();
getReimbs();