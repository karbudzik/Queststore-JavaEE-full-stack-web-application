var defaultColor = document.getElementById("e-mail").style.backgroundColor;
var errorColor = "rgb(191, 52, 63)";

function switchButton(status){
    var button = document.getElementById('button-update');
    if (status) {
      button.disabled = false;
    }else{
      button.disabled = true;
    }
}

function checkEmail(){
	var email = document.getElementById("e-mail").value;
    
    if(email.length == 0){
        changeColor("e-mail", defaultColor);
        return;
    }

	if (email.indexOf("@") == -1) {
    	console.log("Not a valid e-mail!");
		changeColor("e-mail",errorColor);
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
	var isEmailCorrect = checkEmail();

	if(!isEmailCorrect){
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