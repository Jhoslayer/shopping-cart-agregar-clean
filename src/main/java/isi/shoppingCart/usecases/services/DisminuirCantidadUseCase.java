package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;

public class DisminuirCantidadUseCase {

    private CartRepository cartRepository;

    public DisminuirCantidadUseCase(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    public OperationResult execute(int productId) {

        Cart cart = cartRepository.getCart();

        cart.decreaseProductQuantity(productId);

        cartRepository.save(cart);

        return OperationResult.ok("Cantidad reducida");
    }
}
