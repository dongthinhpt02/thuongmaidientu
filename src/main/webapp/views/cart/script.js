let cartItems = [];
let cartTotal = 0;

function addToCart(productName, price) {
  cartItems.push({ name: productName, price: price });
  cartTotal += price;

  updateCart();
}

function updateCart() {
  const cartItemsElement = document.getElementById("cart-items");
  cartItemsElement.innerHTML = "";

  cartItems.forEach(item => {
    const listItem = document.createElement("li");
    listItem.innerText = `${item.name} - $${item.price.toFixed(2)}`;
    cartItemsElement.appendChild(listItem);
  });

  document.getElementById("cart-total").innerText = cartTotal.toFixed(2);
}

function checkout() {
  alert(`Total Amount: $${cartTotal.toFixed(2)}\nThank you for shopping!`);
  cartItems = [];
  cartTotal = 0;
  updateCart();
}
