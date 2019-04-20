package io.github.ravichaturvedi.k8s.explorer;

import io.javalin.Javalin;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class K8sExploration {

    private static final Logger LOGGER = LoggerFactory.getLogger(K8sExploration.class);

    public static void main(String[] args) {
        Javalin app = Javalin.create().start(7000);

        app.get("/healthz", ctx -> {
            LOGGER.info("'/healthz' called...");
            ctx.result("OK");
        });

        app.get("/message", ctx -> {
            LOGGER.info("'/message' called...");
            ctx.result(Configuration.getInstance().getMessage());
        });

        app.get("/greet", ctx -> {
            LOGGER.info("'/greet' called...");
            Thread.sleep(Configuration.getInstance().sleepTimeInSecondsBeforeGreet() * 1000);
            ctx.result("Hello!");
        });

        app.put("/config", ctx -> {
            String body = ctx.body();
            LOGGER.info("'/config' called with body: \n {}", body);
            Configuration.getInstance().updateProperties(body);
        });
    }
}
