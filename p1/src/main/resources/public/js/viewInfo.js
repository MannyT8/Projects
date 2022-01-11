//alert("View My Info")
var apiURL="http://localhost:9091/viewInfo";
fetch(apiURL) 
    // Handle success // Promise
    .then(response => response.json())  // convert to json
    .then(json => populateData(json))    //pass data to populateDate() OR print data to console
    .catch(err => console.log('Request Failed', err));
    
  function populateData(response){
	 var dataSection = document.getElementById('responseData');
	 dataSection.innerHTML='';
	 
	/*	
		"id": 5,
"requester": 1,
"amount": 100,
"reason": "cuz",
"requestDate": "Dec 17, 2021",
"status": "pending",
"decidingManager": 0
*/
		 function tableCreate() {
  const body = document.body,
        tbl = document.createElement('table');
  tbl.style.width = '100px';
  tbl.style.border = '1px solid black';
  var orderArrayHeader = ["User Id","Username","Full Name", "Position","Password"];
  var thead = document.createElement('thead');

tbl.appendChild(thead);

for(var i=0;i<orderArrayHeader.length;i++){
    thead.appendChild(document.createElement("th")).
    appendChild(document.createTextNode(orderArrayHeader[i]));
}
  

  for (let i = 0; i < 1; i++) {
    const tr = tbl.insertRow();
    for (let j = 0; j < 5; j++) {
        const td = tr.insertCell();
        
        
        if(j==0){
        td.appendChild(document.createTextNode(response.id));
        }
        if(j==1){
        td.appendChild(document.createTextNode(response.userName));
        }
        if(j==2){
        td.appendChild(document.createTextNode(response.fullName));
        }
        if(j==3){
        td.appendChild(document.createTextNode(response.position));
        }
         if(j==4){
        td.appendChild(document.createTextNode(response.password));
        }
   
        //td.appendChild(document.createTextNode(`Cell I${i}/J${j}`));
        td.style.border = '1px solid black';  
    }
  }
  body.appendChild(tbl);
        tbl.bgColor = "White";            

}

tableCreate();

}    
	 

