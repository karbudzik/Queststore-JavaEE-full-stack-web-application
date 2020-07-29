let email_input = document.querySelector(".email-input");
const passw_input = document.querySelector(".passw-input");
const warning_paragraph = document.querySelector(".warning");
let second_passw_input = document.querySelector(".sec-passw");
let resetPasswBtn = document.querySelector(".button");
let name_input = document.querySelector(".name-surname");
const submitBtn = document.querySelector(".submit-button");
let correctName = true;
let correctEmail = true;

function switchButton(id, arr){
    var button = document.getElementById(id);
    var i;
    for (i = 0; i < arr.length; i++) {
        if (arr[i]) {
            console.log("enabled")
            button.disabled = false;
            break;
        }else{
            console.log("disabled")
            button.disabled = true;
        }
    }
}

function emailValidator() {
    var mailformat = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/
    let email_value = email_input.value;
    let validEmail = mailformat.test(email_value);
    const email_paragraph = document.querySelector(".warning-email");
    console.log(validEmail)
    if(!validEmail){
        email_paragraph.classList.remove("hidden");
        correctEmail = false;
    }else{
        email_paragraph.classList.add("hidden");
        correctEmail = true;
    }
    let arr = [correctEmail, correctName];
    switchButton("btn-update", arr);
}

function passwordValidator() {
    var passwFormat = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[@$!%*#?&])[A-Za-z\d@$!%*#?&]{8,}$/;
    let passw_value = passw_input.value;
    let validPassw = passwFormat.test(passw_value);
    if(!validPassw){
        document.querySelector(".warning-password").classList.remove("hidden");
    }else{
        document.querySelector(".warning-password").classList.add("hidden");
    }
    let arr = [validPassw];
    switchButton("password-change", arr)
}

function checkMatchPasswords(){
    let passw_value = passw_input.value;
    let sec_passw = document.querySelector(".sec-passw").value;
    const warnig_passw_message = document.querySelector(".warning-equals");
    if(passw_value !== sec_passw){
        warnig_passw_message.classList.remove("hidden");
    }else{
        warnig_passw_message.classList.add("hidden")
    }
}

function nameValidator(){
    const regExForName = /^([a-zA-Z]{2,}\s[a-zA-z]{1,}'?-?[a-zA-Z]{2,}\s?([a-zA-Z]{1,})?)/;
    let name_value = name_input.value;
    let validName = regExForName.test(name_value);
    if(validName){
        correctName = true;
    } else{
        correctName = false;
    }
    displayWarning(validName);
    let arr = [validName];
    switchButton("btn-update", arr);
}


function displayWarning(validatorResult){
    if (validatorResult == false) {
        warning_paragraph.classList.remove("hidden");
    } else {
        warning_paragraph.classList.add("hidden");
    }
}
function checkEmail(){
    email_input.addEventListener("input",emailValidator);
}
function checkName(){
    name_input.addEventListener("focusout",nameValidator);
}

function checkIfPasswordsAreEquals(){
    resetPasswBtn.addEventListener("mouseover",checkMatchPasswords);
}

function checkPasswordForRegEx(){
    passw_input.addEventListener("focusout",passwordValidator);
}

function passwordClick() {
    passw_input.addEventListener("click", emailValidator);
}

function clickBtnResetPassw() {
    submitBtn.addEventListener("mouseover", emailValidator);
}