package isi.shoppingCart.usecases.services;

import isi.shoppingCart.entities.Cart;
import isi.shoppingCart.entities.Product;
import isi.shoppingCart.usecases.dto.OperationResult;
import isi.shoppingCart.usecases.ports.CartRepository;
import isi.shoppingCart.usecases.ports.ProductRepository;

public class AumentarCantidadUseCase {

    private CartRepository cartRepository;
    private ProductRepository productRepository;

    public AumentarCantidadUseCase(CartRepository cartRepository,
                                   ProductRepository productRepository) {
        this.cartRepository = cartRepository;
        this.productRepository = productRepository;
    }

    public OperationResult execute(int productId) {

        Cart cart = cartRepository.getCart();
        Product product = productRepository.findById(productId);

        if (product == null) {
            return OperationResult.fail("Producto no existe");
        }

        boolean success = cart.increaseProductQuantity(
                productId,
                product.getAvailableQuantity()
        );

        cartRepository.save(cart);

        if (!success) {
            return OperationResult.fail("No hay más stock disponible");
        }

        return OperationResult.ok("Cantidad aumentada");
    }
}
