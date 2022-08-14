package com.zhilenkov;

import com.zhilenkov.config.ProductConfiguration;
import com.zhilenkov.order.OrderService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context =
                new AnnotationConfigApplicationContext(ProductConfiguration.class);
        OrderService orderService = context.getBean("orderService", OrderService.class);

        orderService.printListProducts();
        orderService.beginOrdering((AnnotationConfigApplicationContext) context);
    }
}
