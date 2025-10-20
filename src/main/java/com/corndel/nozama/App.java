package com.corndel.nozama;

import com.corndel.nozama.repositories.ProductRepository;
import com.corndel.nozama.models.User;
import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

import java.sql.ResultSet;

public class App {
    public record UserLogin(String username, String password) {
    }

    private Javalin app;

    public static void main(String[] args) {
        var app = new App().javalinApp();
        app.start(8080);
    }

    public App() {
        app = Javalin.create();
        app.get("/", ctx -> {
            var users = UserRepository.findAll();
            ctx.json(users);
        });
        app.get("/users/{userId}", ctx -> {
            var id = Integer.parseInt(ctx.pathParam("userId"));
            var user = UserRepository.findById(id);
            ctx.status(HttpStatus.IM_A_TEAPOT).json(user);
        });
    app.get(
            "/products",
            ctx -> {
                var product = ProductRepository.findAll();
                ctx.json(product);
            });
    app.get(
            "/products/{productId}",
            ctx -> {
                var id = Integer.parseInt(ctx.pathParam("productId"));
                var product = ProductRepository.findById(id);
                ctx.status(HttpStatus.IM_A_TEAPOT).json(product);
            });
  }
        app.post("/users/{userId}", ctx -> {
            var id = Integer.parseInt(ctx.pathParam("userId"));
            Integer res = User.deleteUser(id);
            if (res > 0) {
                System.out.println("Deleted user at id:" + id);
            } else {
                System.out.println("Failed to delete user");
            }
            ctx.json(res);
        });
        app.post("/users/login", ctx -> {
            UserLogin body = ctx.bodyAsClass(UserLogin.class);

            var login = User.loginUser(body.username, body.password);
            ctx.status(201);
            ctx.json(login);
        });
        app.post("/users", ctx -> {
            User body = ctx.bodyAsClass(User.class);
            System.out.println(body);
            Integer changes = User.createUser(body.getUsername(), body.getPassword(), body.getFirstName(), body.getLastName(), body.getEmail(), body.getAvatar());
            ctx.status(201);
            if (changes > 0) {
                ctx.json("Success, user added");
            } else {
                ctx.json("No changes made");
            }

        });
//    app.get(
//            "/products/",
//            ctx -> {
//                var id = Integer.parseInt(ctx.queryParam("id"));
//                var name = ctx.queryParam("name");
//                var description = ctx.queryParam("description");
//                var price = ctx.queryParam("price");
//                var stockQuantity = Integer.parseInt(ctx.queryParam("stockQuantity"));
//                var imageURL = ctx.queryParam("imageURL");
//                ctx.status(HttpStatus.ACCEPTED)
//            });
  }
    }

    public Javalin javalinApp() {
        return app;
    }
}
