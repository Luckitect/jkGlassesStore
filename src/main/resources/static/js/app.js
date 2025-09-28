async function loadProducts() {
    const container = document.getElementById('products-container');
    try {
        const response = await fetch('/products'); // Your backend endpoint
        const products = await response.json();

        products.forEach(product => {
            const card = document.createElement('div');
            card.className = 'product-card';
            card.innerHTML = `
                <h3>${product.name}</h3>
                <p>Price: $${product.price}</p>
                <p>Color: ${product.color}</p>
                <p>Material: ${product.material}</p>
            `;
            container.appendChild(card);
        });
    } catch (err) {
        container.innerHTML = '<p>Failed to load products.</p>';
        console.error(err);
    }
}

window.onload = loadProducts;
