package isi.shoppingCart.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Cart {
    private List<CartItem> items;

    public Cart() {
        items = new ArrayList<>();
    }

    public List<CartItem> getItems() {
        return Collections.unmodifiableList(items);
    }

    public void addProduct(Product product) {
        for (CartItem item : items) {

            if (item.getProduct().getId() == product.getId()) {
                item.increaseQuantity(product.getAvailableQuantity());
                return;
            }
        }

        items.add(new CartItem(product, 1));
    }

    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    public boolean increaseProductQuantity(int productId, int maxStock) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (item.getQuantity() < maxStock) {
                    item.increaseQuantity(maxStock);
                    return true;
                }
                return false;
            }
        }
        return false;
    }

    public void decreaseProductQuantity(int productId) {
        for (CartItem item : items) {
            if (item.getProduct().getId() == productId) {
                item.decreaseQuantity();

                if (item.getQuantity() == 0) {
                    removeProduct(productId);
                }
                return;
            }
        }
    }

    public int getQuantityByProductId(int productId) {
        for (CartItem item : items) {

            if (item.getProduct().getId() == productId) {
                return item.getQuantity();
            }
        }

        return 0;
    }

    public double getTotal() {
        double total = 0.0;

        for (CartItem item : items) {
            total += item.getSubtotal();
        }

        return total;
    }
}
