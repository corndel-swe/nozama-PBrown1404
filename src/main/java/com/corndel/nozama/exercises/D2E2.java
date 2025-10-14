package com.corndel.nozama.exercises;

import io.javalin.Javalin;

public class D2E2 {
  /**
   * Creates a Javalin app with two endpoints:
   * 
   * - GET /sumup responds with the sum of the integers from 1 to n, where n is
   * provided as a query parameter. If n is not provided, it responds with 0.
   * 
   * - GET /multiply/{x}/{y} responds with the product of x and y.
   * 
   * @see https://tech-docs.corndel.com/javalin/query-params.html
   * @see https://tech-docs.corndel.com/javalin/url-params.html
   * @return a configured Javalin instance
   */
  public static Javalin createApp() {
    var app = Javalin.create();
    app.get(
        "/sumup",
        ctx -> {
            var n = Integer.parseInt(ctx.queryParam("n"));
            if (n == 0 || ctx.queryParam("n") == null)
            {
                ctx.result("0");
            }
            else {
                var sum = (n * (n + 1)) / 2;
                ctx.result(String.valueOf(sum));
            }
        });

    app.get(
        "/multiply/{x}/{y}",
        ctx -> {
            var x = Integer.parseInt(ctx.pathParam("x"));
            var y = Integer.parseInt(ctx.pathParam("y"));
            ctx.result(String.valueOf(x * y));
        });

    return app;
  }

    public static void main(String[] args) {
        createApp().start(8080);
    }
}
