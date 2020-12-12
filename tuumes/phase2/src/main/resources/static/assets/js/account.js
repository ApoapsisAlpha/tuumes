const USER_ID = localStorage.getItem("TU-userId");
const USER_TYPE = localStorage.getItem("TU-userType");

if (USER_ID === null) {
    location.href = "http://localhost:8080";
}