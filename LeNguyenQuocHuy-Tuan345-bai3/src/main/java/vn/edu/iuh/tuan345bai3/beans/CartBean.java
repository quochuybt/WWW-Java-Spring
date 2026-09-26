package vn.edu.iuh.tuan345bai3.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class CartBean implements Serializable {
    private List<CartItemBean> items;

    public CartBean() {
        items = new ArrayList<>();
    }

    public List<CartItemBean> getItems() {
        return items;
    }

    // Thêm sản phẩm
    public void addProduct(Product p) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == p.getId()) {
                item.setQuantity(item.getQuantity() + 1);
                return;
            }
        }

        items.add(new CartItemBean(p, 1));
    }

    // Xóa sản phẩm
    public void removeProduct(int productId) {
        items.removeIf(item -> item.getProduct().getId() == productId);
    }

    // Cập nhật số lượng
    public void updateQuantity(int productId, int quantity) {
        for (CartItemBean item : items) {
            if (item.getProduct().getId() == productId) {
                if (quantity > 0) {
                    item.setQuantity(quantity);
                } else {
                    // Nếu nhập <= 0 thì xóa sản phẩm
                    removeProduct(productId);
                }
                return;
            }
        }
    }

    // Tính tổng tiền
    public double getTotal() {
        double total = 0;

        for (CartItemBean item : items) {
            total += item.getSubtotal();
        }

        return total;
    }

    // Xóa hết giỏ hàng
    public void clear() {
        items.clear();
    }
}
