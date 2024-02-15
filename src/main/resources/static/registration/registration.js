"use strict";

const registrationBtn = document.getElementById("registration-btn");
const requestCodeButton = document.getElementById("request-code-btn");
const firstNameInput = document.getElementById("first-name-input");
const lastNameInput = document.getElementById("last-name-input");
const phoneNumberInput = document.getElementById("phone-number-input");
const conformationCodeInput = document.getElementById("confirmation-code-input");
const passwordInput = document.getElementById("password-input");
const passwordInputCheck = document.getElementById("password-input-check");
let inputErrorMessage = "";

phoneNumberInput.value = localStorage.getItem("phone");

registrationBtn.addEventListener("click", async function () {
  let phoneNumber = phoneNumberInput.value;
  checkPassword();
  checkName();
  checkLastName();
  console.log(inputErrorMessage + "FF");
  await getСode(phoneNumber);
  console.log(inputErrorMessage);
  if (inputErrorMessage) {
    alert(inputErrorMessage);
    inputErrorMessage = "";
    return;
  }

  let requestBody = getUserData();

  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(requestBody),
  };

  fetch(`http://127.0.0.1:8080/api/user/create`, requestOptions).then((response) => {
    console.log(response);
    if (response.ok) {
      window.location.href = `http://127.0.0.1:${location.port}/index.html`;
    } else {
      alert("Ошибка регистрации, повторите попытку!");
      window.location.href = `http://127.0.0.1:${location.port}/registration/registration.html`;
    }
    r;
  });
});

requestCodeButton.addEventListener("click", function () {
  const requestOptions = {
    method: "POST",
  };

  let phoneNumber = phoneNumberInput.value;
  fetch(`http://127.0.0.1:8080/api/mock/code/generate/${phoneNumber}`, requestOptions).then(
    (response) => {
      console.log(response);
      if (response.ok) {
        response
          .json()
          .then((data) => alert(`code ${data} has sent. USE THIS ALERT FOR TEST PURPOSES ONLY`));
      }
    }
  );
});

function checkPassword() {
  if (!passwordInput.value || !passwordInputCheck.value) {
    inputErrorMessage += "укажите пароль\n";
  } else if (passwordInput.value != passwordInputCheck.value) {
    inputErrorMessage += "пароли не совпадают\n";
  }
}

function checkName() {
  if (!firstNameInput.value) {
    inputErrorMessage += "укажите имя\n";
  }
}

function checkLastName() {
  if (!lastNameInput.value) {
    inputErrorMessage += "укажите фамилию\n";
  }
}

async function getСode(phoneNumber) {
  let res;
  let code = conformationCodeInput.value;
  if (!code) {
    inputErrorMessage += "укажите смс код\n";
    return;
  }
  const response = await fetch(`http://127.0.0.1:8080/api/mock/code/get/${phoneNumber}`);
  if (response.ok) {
    res = await response.json();
  }
  if (res != code) {
    inputErrorMessage += "Неверный код подтверждения";
  }
}

function getUserData() {
  return {
    name: firstNameInput.value,
    surname: lastNameInput.value,
    password: passwordInput.value,
    phoneNumber: phoneNumberInput.value,
    code: conformationCodeInput.value,
  };
}
