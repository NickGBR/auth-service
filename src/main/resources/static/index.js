"use strict";

const loginBtn = document.getElementById("login-btn");
const phoneNumberForm = document.getElementById("phone-number");

loginBtn.addEventListener("click", function (event) {
  event.preventDefault();
  let phoneNumber = phoneNumberForm.value;
  localStorage.setItem("phone", phoneNumber);
  fetch(`http://127.0.0.1:8080/api/user/exists/${phoneNumber}`, {}).then((response) => {
    console.log(response);
    if (response.ok) {
      window.location.href = `http://127.0.0.1:${location.port}/auth/auth.html`;
    } else {
      window.location.href = `http://127.0.0.1:${location.port}/registration/registration.html`;
    }
  });
});
