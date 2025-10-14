package com.corndel.nozama;

import com.corndel.nozama.repositories.UserRepository;
import io.javalin.Javalin;
import io.javalin.http.HttpStatus;

public class App {
  private Javalin app;

  public static void main(String[] args) {
    var app = new App().javalinApp();
    app.start(8080);
  }

  public App() {
    app = Javalin.create();
    app.get(
        "/",
        ctx -> {
          var users = UserRepository.findAll();
          ctx.json(users);
        });
    app.get(
        "/users/{userId}",
        ctx -> {
          var id = Integer.parseInt(ctx.pathParam("userId"));
          var user = UserRepository.findById(id);
          ctx.status(HttpStatus.IM_A_TEAPOT).json(user);
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

  public Javalin javalinApp() {
    return app;
  }
}
