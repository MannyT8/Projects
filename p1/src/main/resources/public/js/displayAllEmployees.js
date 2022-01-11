//alert("all Employees")
var apiURL="http://localhost:9091/employees";
fetch(apiURL) 
    // Handle success // Promise
    .then(response => response.json())  // convert to json
    .then(json => populateData(json))    //pass data to populateDate() OR print data to console
    .catch(err => console.log('Request Failed', err));
    
  function populateData(response){
	 var dataSection = document.getElementById('responseData');
	 dataSection.innerHTML='';
	 
	 /*
	 for(i=0;i<response.length;i++){
		//alert(response[i].id)
		var idTag = document.createElement('h3');
   		 var data=response[i].id +"  "+response[i].userName+"  "+response[i].fullName;
   		 idTag.innerHTML=data;
   		  dataSection.appendChild(idTag);
	}
*/
		
		 function tableCreate() {
  const body = document.body,
        tbl = document.createElement('table');
  tbl.style.width = '100px';
  tbl.style.border = '1px solid black';
  var orderArrayHeader = ["Id","Username","Full Name", "Position"];
  var thead = document.createElement('thead');

tbl.appendChild(thead);

for(var i=0;i<orderArrayHeader.length;i++){
    thead.appendChild(document.createElement("th")).
    appendChild(document.createTextNode(orderArrayHeader[i]));
}
  

  for (let i = 0; i < response.length; i++) {
    const tr = tbl.insertRow();
    for (let j = 0; j < 4; j++) {
        const td = tr.insertCell();
        if(j==0){
        td.appendChild(document.createTextNode(response[i].id));
        }
        if(j==1){
        td.appendChild(document.createTextNode(response[i].userName));
        }
        if(j==2){
        td.appendChild(document.createTextNode(response[i].fullName));
        }
        if(j==3){
        td.appendChild(document.createTextNode(response[i].position));
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
	 

