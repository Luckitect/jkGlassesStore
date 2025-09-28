const apiUrl = "http://localhost:8080/api/products";

let allProducts = [];

// Fetch products
fetch(apiUrl)
  .then(res => res.json())
  .then(data => {
    allProducts = data;
    populateBrandFilter(data);
    renderProducts(data);
  })
  .catch(err => {
    console.error("Error loading products:", err);
    document.getElementById("products-grid").innerHTML = "<p>Failed to load products.</p>";
  });

// Populate Brand filter dynamically
function populateBrandFilter(products) {
  const brands = [...new Set(products.map(p => p.brand?.name || "Unknown"))];
  const brandSelect = document.getElementById("filterBrand");
  brands.forEach(b => {
    const option = document.createElement("option");
    option.value = b;
    option.textContent = b;
    brandSelect.appendChild(option);
  });
}

// Render products
function renderProducts(products) {
  const grid = document.getElementById("products-grid");
  grid.innerHTML = "";

  products.forEach(product => {
    const col = document.createElement("div");
    col.classList.add("w-col", "w-col-4");

    col.innerHTML = `
      <img src="images/glassphoto.jpg" class="image-12" alt="${product.name}" />
      <div class="description">
        <strong>${product.name}</strong><br/>
        Brand: ${product.brand?.name || "N/A"}<br/>
        Category: ${product.category?.name || "N/A"}<br/>
        Polarized: ${product.polarized ? "Yes" : "No"}<br/>
        Price: $${product.price.toFixed(2)}
      </div>
      <div>
        <a href="product-details.html?id=${product.id}" class="seedetails w-button">See Details</a>
        <a href="cart.html" class="add-to-cart w-button">Add to Cart</a>
      </div>
    `;

    grid.appendChild(col);
  });
}

// Apply filters
function applyFilters() {
  let filtered = [...allProducts];

  // Sort by price
  const sort = document.getElementById("sortPrice").value;
  if (sort === "asc") {
    filtered.sort((a, b) => a.price - b.price);
  } else if (sort === "desc") {
    filtered.sort((a, b) => b.price - a.price);
  }

  // Filter by brand
  const brand = document.getElementById("filterBrand").value;
  if (brand) {
    filtered = filtered.filter(p => (p.brand?.name || "Unknown") === brand);
  }

  // Filter by category
  const category = document.getElementById("filterCategory").value;
  if (category) {
    filtered = filtered.filter(p => (p.category?.name || "").toLowerCase() === category.toLowerCase());
  }

  // Filter by polarized
  const polarized = document.getElementById("filterPolarized").value;
  if (polarized) {
    const boolVal = polarized === "true";
    filtered = filtered.filter(p => p.polarized === boolVal);
  }

  renderProducts(filtered);
}

// Event listeners
document.getElementById("sortPrice").addEventListener("change", applyFilters);
document.getElementById("filterBrand").addEventListener("change", applyFilters);
document.getElementById("filterCategory").addEventListener("change", applyFilters);
document.getElementById("filterPolarized").addEventListener("change", applyFilters);
