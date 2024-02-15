"use strict";

const namePlaseholder = document.getElementById("name");
const surnamePlaseholder = document.getElementById("surname");
const phoneNumberPlaseholder = document.getElementById("phone-number");
phoneNumberPlaseholder.textContent = localStorage.getItem("phone");

window.onload = function () {
  loadUserData();
};

function loadUserData() {
  let phoneNumber = phoneNumberPlaseholder.textContent;
//   let body = {
//     query: `query user{\n  getUserByPhoneNumber(phoneNumber: "${phoneNumber}"){name, surname, phoneNumber}}","operationName":"user`,
//   };
  let body = {
    query: `query user{getUserByPhoneNumber(phoneNumber: "${phoneNumber}"){\n    name, \n    surname,\n    phoneNumber\n  }\n}`,
  };
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      Authorization: `Bearer ${localStorage.getItem("accessToken")}`,
    },
    body: JSON.stringify(body),
  };

  fetch(`http://127.0.0.1:8080/graphql`, requestOptions).then((response) => {
    if (response.ok) {
      response.json().then((data) => {
        console.log(data);
        namePlaseholder.textContent = data.data.getUserByPhoneNumber.name;
        surnamePlaseholder.textContent = data.data.getUserByPhoneNumber.surname;
      });
    } else {
      alert("Неверный пароль");
      passwordForm = "";
    }
  });
}
