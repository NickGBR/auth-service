"use strict";
const loginBtn = document.getElementById("login-btn");
const phoneNumberForm = document.getElementById("phone-number");
const passwordForm = document.getElementById("password");

phoneNumberForm.value = localStorage.getItem("phone");
console.log(localStorage.getItem("phone"));
phoneNumberForm.setAttribute("readOnly", true);

loginBtn.addEventListener("click", function (event) {
  event.preventDefault();
  let phoneNumber = phoneNumberForm.value;
  let password = passwordForm.value;

  let body = {
    phoneNumber: phoneNumber,
    password: password,
  };
  const requestOptions = {
    method: "POST",
    headers: {
      "Content-Type": "application/json",
    },
    body: JSON.stringify(body),
  };

  fetch(`http://127.0.0.1:8080/api/auth`, requestOptions).then((response) => {
    if (response.ok) {
      response.json().then((data) => {
        localStorage.setItem("accessToken", data.accessToken);
      });
      window.location.href = `http://127.0.0.1:${location.port}/userInfo/info.html`;
    } else {
      alert("Неверный пароль");
      passwordForm = "";
    }
  });
});
