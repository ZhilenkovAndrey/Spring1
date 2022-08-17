package com.zhilenkov.order;

import com.zhilenkov.cart.Cart;
import com.zhilenkov.cart.CartService;
import com.zhilenkov.product.ProductService;
import com.zhilenkov.user.User;
import com.zhilenkov.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
public class OrderService {
    private UserService userService;
    private CartService cartService;
    private ProductService productService;
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private String a;

    @Autowired
    public OrderService(UserService userService, CartService cartService, ProductService productService) {
        this.userService = userService;
        this.cartService = cartService;
        this.productService = productService;
    }

    public void printListProducts() {
        productService.init();
        System.out.println("---------------------------------------------------------");
        productService.findAll().stream().forEach(System.out::print);
        System.out.println();
        System.out.println();
        System.out.println("---------------------------------------------------------");
        System.out.println();
        System.out.println("Do you want to make an order: y/n ");
    }

    public void beginOrdering(AnnotationConfigApplicationContext context) {
        try {
            a = reader.readLine();
        } catch (IOException e) {
            System.out.println(" Unknown command");
            beginOrdering(context);
        }

        if (a.equalsIgnoreCase("y")) {
            createNewOrder(context);
        } else if (a.equalsIgnoreCase("n")) {
            System.out.println(" Press q to quite");
            try {
                a = reader.readLine();
                exit(a, context);
            } catch (IOException e) {
                System.out.println(" Unknown command");
                beginOrdering(context);
            }
        } else {
            System.out.println(" Unknown command");
            beginOrdering(context);
        }
    }

    public void finishOrder(AnnotationConfigApplicationContext context, Order order) {
        System.out.println(" Do you want change order? y/n");
        System.out.println(" To go to confirm press q");
        try {
            a = reader.readLine();
        } catch (IOException e) {
            System.out.println(" Unknown command...");
            finishOrder(context, order);
        }

        if (a.equalsIgnoreCase("y")) {
            cartService.getCurrentCart().setProducts(productAdd().getProducts());
            order.setProducts(cartService.getCurrentCart().getProducts());
            printOrder(order);

            cartService.getCurrentCart().setProducts(productRemove().getProducts());
            order.setProducts(cartService.getCurrentCart().getProducts());
            printOrder(order);
        }
        if (a.equalsIgnoreCase("q")) {
            context.close();
        }

        if (a.equalsIgnoreCase("n")) {
            printOrder(order);
            context.close();
        }
    }

    public Order createNewOrder(AnnotationConfigApplicationContext context) {
        userService.init();
        cartService.init();

        Order order = new Order();
        Cart currentCart = cartService.getCurrentCart();

        order.setId(UUID.randomUUID().toString());

        User currentUser = userService.getCurrentUser();
        order.setUser(UserRegistration(currentUser));

        currentCart.setProducts(productAdd().getProducts());
        order.setProducts(currentCart.getProducts());
        printOrder(order);

        currentCart.setProducts(productRemove().getProducts());
        order.setProducts(currentCart.getProducts());
        printOrder(order);

        finishOrder(context, order);
        return order;
    }

    public User UserRegistration(User currentUser) {
        System.out.println("Write your name:");
        try {
            a = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        currentUser.setUsername(a);
        return currentUser;
    }

    public Cart productAdd() {
        while (true) {
            System.out.println("Enter name or id of product to add: ");
            System.out.println("Press f to finish");
            try {
                a = reader.readLine();
                if (a.equalsIgnoreCase("f")) {
                    break;
                }
                cartService.addToCart(a);
            } catch (NoSuchElementException | IOException e) {
                if (a.equalsIgnoreCase("f")) {
                    break;
                }
                try {
                    Long c =  Long.parseLong(a);
                    cartService.addToCart(c);
                } catch (NumberFormatException ex) {
                    System.out.println(" Unknown position...");
                    productAdd();
                }
            }
        }
        return cartService.getCurrentCart();
    }

    public void exit(String a, AnnotationConfigApplicationContext context) {
        if (a.equalsIgnoreCase("q")) {
            context.close();
        } else {
            System.out.println(" Unknown command...");
            beginOrdering(context);
        }
    }

    public Cart productRemove() {
        while (true) {
            System.out.println("Enter name or id of product to remove: ");
            System.out.println("Press f to finish");
            try {
                a = reader.readLine();
                if (a.equalsIgnoreCase("f")) {
                    break;
                }
                cartService.removeFromCart(a);
            } catch (NoSuchElementException | IOException e) {
                if (a.equalsIgnoreCase("f")) {
                    break;
                }
                try {
                    Long c =  Long.parseLong(a);
                    cartService.removeFromCart(c);
                } catch (NumberFormatException ex) {
                    System.out.println(" Unknown command...");
                    productRemove();
                }
            }
        }
        return cartService.getCurrentCart();
    }

    public void printOrder(Order order) {
        System.out.println(" Order id = " + order.getId());
        System.out.println(" Order owner: " + order.getUser().getUsername());
        System.out.println("------------------------------------------------");
        cartService.getCurrentCart().getProducts().forEach(System.out::println);
        System.out.println("------------------------------------------------");
    }
}
