// js/products.js
document.addEventListener("DOMContentLoaded", function () {
  const container = document.getElementById("products-container");

  fetch("http://localhost:8080/api/products") // backend endpoint
    .then(response => {
      if (!response.ok) {
        throw new Error("HTTP error " + response.status);
      }
      return response.json();
    })
    .then(products => {
      container.innerHTML = "";

      if (!products || products.length === 0) {
        container.innerHTML = "<p>No products available.</p>";
        return;
      }

      products.forEach(product => {
        const col = document.createElement("div");
        col.className = "w-col w-col-4";

        col.innerHTML = `
          <img src="images/glassphoto.jpg" alt="${product.name}" class="image-12"/>
          <div class="description">
              <strong>${product.name}</strong><br/>
              Brand ID: ${product.brandId}<br/>
              Price: $${product.price}
          </div>
          <div class="product-buttons" style="text-align:center; margin-top:10px;">
              <a href="product.html?id=${product.id}" class="navigation-item w-nav-link seedetails">See Details</a>
              <a href="cart.html?add=${product.id}" class="navigation-item signin w-nav-link add-to-cart">Add to Cart</a>
          </div>
        `;

        container.appendChild(col);
      });
    })
    .catch(error => {
      console.error("Fetch error:", error);
      container.innerHTML = "<p style='color:red'>Failed to load products. Check console.</p>";
    });
});
