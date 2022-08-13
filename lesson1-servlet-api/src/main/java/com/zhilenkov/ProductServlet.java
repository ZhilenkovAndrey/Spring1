package com.zhilenkov;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends HttpServlet {


    private ProductRepository productRepository;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter writer = response.getWriter();
        writer.println("<table>");
        writer.println("<tr>");
        writer.println("<th>id</th>");
        writer.println("<th>title</th>");
        writer.println("</tr>");

        for (Product product : productRepository.findAll()) {
            writer.println("<tr>");
            writer.println("<td>" + product.getId() + "</td>");
            writer.println("<td>" + product.getTitle() + "</td>");
            writer.println("</tr>");
        }

        writer.println("</table>");
    }

    @Override
    public void init() throws ServletException {
        this.productRepository = productRepository;
        this.productRepository.insert(new Product("Milk", 30.0));
        this.productRepository.insert(new Product("Bread", 25.0));
        this.productRepository.insert(new Product("Cheese", 80.0));
        this.productRepository.insert(new Product("Meat", 180.0));
        this.productRepository.insert(new Product("Fish", 240.0));
    }
}
