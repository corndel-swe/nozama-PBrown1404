package com.corndel.nozama.exercises;

import io.javalin.Javalin;
import io.javalin.http.Context;

import java.util.Map;

import static io.javalin.apibuilder.ApiBuilder.*;

public class D3E1 {
    // This is our counter:
    // SINGLETON -> ONLY ONE Counter
    public static Counter counter = new Counter();

    /**
     * Creates a Javalin app with two endpoints.
     *
     * @return a configured Javalin instance
     * @see https://tech-docs.corndel.com/javalin/routing.html
     */
    public static Javalin createApp() {
        var app = Javalin.create(
                config -> {
                    config.router.apiBuilder(() -> {
                        path("/counter", () -> {
                            get("/", CounterController::getCounter);
                            put("/increment", CounterController::increment);
                            put("/decrement", CounterController::decrement);
                        });
                    });
                });
        // HOW CAN WE TIDY/REFACTOR OUR ENDPOINTS

        return app;
    }

    public static void main(String[] args) {
        createApp().start(8080);
    }
}

class CounterController {
    /**
     * Responds with the current counter as a JSON object, e.g. { "count": 3 }.
     */
    public static void getCounter(Context ctx) {
        // TODO
        // STATIC VARIABLE -> Counter counter.getCount()
        // WHY? -> ONE COUNTER
        var currentCount = D3E1.counter.getCount();
        ctx.json(Map.of("count", currentCount));
    }

    /**
     * Increases the counter by 1 and then responds with the count.
     */
    public static void increment(Context context) {
        // TODO
        var newCount = D3E1.counter.getCount() + 1;
        D3E1.counter.setCount(newCount);
        context.json(Map.of("count", newCount));
    }

    public static void decrement(Context context) {
        int newCount = D3E1.counter.getCount() - 1;
        D3E1.counter.setCount(newCount);
        context.json(D3E1.counter);
    }
}

class Counter {
    private int count;

    public Counter() {
        count = 0;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
