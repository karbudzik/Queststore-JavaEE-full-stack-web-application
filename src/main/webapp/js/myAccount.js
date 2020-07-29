var defaultColor = document.getElementById("name-and-surname").style.backgroundColor;
var errorColor = "rgb(191, 52, 63)";

function switchButton(status){
    var button = document.getElementById('update');
    if (status) {
      button.disabled = false;
    }else{
      button.disabled = true;
    }
}

function checkName(){
	var submitOK = true;
	var name = document.getElementById("name-and-surname").value;
    const maxLength = 20;
    
    if(name.length == 0){
        changeColor("name-and-surname", defaultColor);
        return false;
    }
	
	if(name.length > maxLength){
		changeColor("name-and-surname", errorColor);
		return false;
	}
	
	var patt = new RegExp("([A-Z][A-Z]*)");
  	var res = patt.test(name);
	console.log(res);
	if(!res){
		changeColor("name-and-surname", errorColor);
		return false;
	}
	changeColor("name-and-surname", defaultColor);
	
	return true;
}

function checkEmail(){
	var email = document.getElementById("e-mail").value;
	
	if (email.indexOf("@") == -1) {
    	console.log("Not a valid e-mail!");
		changeColor("email",errorColor);
    	return false;
  	}
	
	var patt = new RegExp("^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$");
  	var res = patt.test(email);
	console.log(res);
	if(!res){
		changeColor("e-mail",errorColor);
		return false;
	}
	
	changeColor("e-mail", defaultColor);
	
	return true;	
}

function checkData() {
	var isNameCorrect = checkName();
	var isEmailCorrect = checkEmail();

	if(!isNameCorrect || !isEmailCorrect){
		switchButton(false);
	}else{
    	switchButton(true);
	}
}

function clickEmail(){
    console.log("kliknołeś mnie");
}

function changeColor(IDForChange ,nameOfColor){
	document.getElementById(IDForChange).style.backgroundColor = nameOfColor;	
}

function displayMessage(){
	alert("You update a data");	
}