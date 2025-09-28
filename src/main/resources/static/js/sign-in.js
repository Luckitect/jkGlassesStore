document.addEventListener("DOMContentLoaded", function () {
    const loginForm = document.getElementById("login-form");
    const errorDiv = document.getElementById("login-error");

    loginForm.addEventListener("submit", function (e) {
        e.preventDefault();

        const email = document.getElementById("Email").value;
        const password = document.getElementById("Password").value;

        fetch("http://localhost:8080/api/login", {  // Adjust to your backend login endpoint
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({ email, password })
        })
        .then(res => {
            if (!res.ok) throw new Error("Login failed");
            return res.json();
        })
        .then(data => {
            // Save user token/session if any
            sessionStorage.setItem("user", JSON.stringify(data));

            // Redirect to home or cart page after login
            window.location.href = "index.html";
        })
        .catch(err => {
            console.error(err);
            errorDiv.style.display = "block";
            errorDiv.textContent = "Invalid email or password";
        });
    });
});
