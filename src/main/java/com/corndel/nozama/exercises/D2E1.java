package com.corndel.nozama.exercises;

import io.javalin.Javalin;

public class D2E1 {
  /**
   * Creates a Javalin app with a single GET /ping endpoint.
   * This endpoint responds with the simple string "pong".
   * 
   * @see https://tech-docs.corndel.com/javalin/creating-a-server.html
   * @see https://tech-docs.corndel.com/javalin/request-response.html
   * @return a configured Javalin instance
   */
  public static Javalin createApp() {
    Javalin app = Javalin.create();

    // TODO: add the GET /ping endpoint
    app.get("/ping", ctx -> {
      ctx.result("pong");
    });

    return app;
  }

  public static void main(String[] args) {
    createApp().start(8080);
  }
}