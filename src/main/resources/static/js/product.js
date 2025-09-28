// js/product.js
document.addEventListener("DOMContentLoaded", function () {
  const params = new URLSearchParams(window.location.search);
  const productId = params.get("id");

  const container = document.getElementById("product-container"); // add an empty div with id="product-container" in your HTML

  if (!productId) {
    container.innerHTML = "<p style='color:red'>Product ID not specified in URL.</p>";
    return;
  }

  fetch(`http://localhost:8080/api/products/${productId}`)
    .then(response => {
      if (!response.ok) {
        throw new Error("HTTP error " + response.status);
      }
      return response.json();
    })
    .then(product => {
      container.innerHTML = `
        <div class="w-layout-layout wf-layout-layout product-details">
          <div class="w-layout-cell">
            <img src="images/glassphoto.jpg" alt="${product.name}" class="productmainphoto"/>
          </div>
          <div class="w-layout-cell productmaindescription">
            <div class="productname">${product.name}</div>
            <div>Price: $${product.price}</div>
            <div>Brand ID: ${product.brandId}</div>
            <div>Category ID: ${product.categoryId}</div>
          </div>
          <div class="w-layout-cell productdescription">
            <div>Size: ${product.size}</div>
            <div>Color: ${product.color}</div>
            <div>Material: ${product.material}</div>
            <div>Polarized: ${product.polarized ? "Yes" : "No"}</div>
          </div>
          <div class="w-layout-cell addtocartcell">
            <a href="cart.html?add=${product.id}" class="add-to-cart w-button">Add To Cart</a>
          </div>
        </div>
      `;
    })
    .catch(error => {
      console.error("Fetch error:", error);
      container.innerHTML = "<p style='color:red'>Failed to load product. Check console.</p>";
    });


});
