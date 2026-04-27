package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;

public class EliminarProductoDelCarritoUseCase {

    private CartRepository cartRepository;

    public EliminarProductoDelCarritoUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public OperationResult execute(int productId) {

        Cart cart = cartRepository.getCart();

        boolean exists = cart.getItems().stream()
                .anyMatch(item -> item.getProduct().getId() == productId);

        if (!exists) {
            return OperationResult.fail("El producto no está en el carrito.");
        }

        cart.removeProduct(productId);
        cartRepository.save(cart);

        if (cart.getItems().isEmpty()) {
            return OperationResult.ok("Producto eliminado. El carrito está vacío.");
        }

        return OperationResult.ok("Producto eliminado del carrito.");
    }
}
