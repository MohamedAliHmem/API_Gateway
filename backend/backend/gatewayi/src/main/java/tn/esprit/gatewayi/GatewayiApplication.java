package tn.esprit.gatewayi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.function.RouterFunction;
import org.springframework.web.servlet.function.ServerResponse;

import static org.springframework.cloud.gateway.server.mvc.filter.BeforeFilterFunctions.uri;
import static org.springframework.cloud.gateway.server.mvc.filter.LoadBalancerFilterFunctions.lb;
import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;
import static org.springframework.cloud.gateway.server.mvc.predicate.GatewayRequestPredicates.path;

@EnableDiscoveryClient
@SpringBootApplication
public class GatewayiApplication {

    public static void main(String[] args) {
        SpringApplication.run(GatewayiApplication.class, args);
    }

    // ==========================================
    // APPROCHE 1 : Configuration Statique (URL directe)
    // On utilise l'URL en dur : http://localhost:PORT
    // ==========================================

    @Bean
    public RouterFunction<ServerResponse> candidatureRoute() {
        return route("candidatures")
                .route(path("/mic3/candidature/**"), http())
                .before(uri("http://localhost:8085"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> addressRoute() {
        return route("addresses")
                .route(path("/mic1/addresses/**"), http())
                .before(uri("http://localhost:8081"))
                .build();
    }

    // ==========================================
    // APPROCHE 2 : Configuration Dynamique via Eureka
    // On utilise filter(lb("nomService")) pour découvrir
    // automatiquement le service via le registre Eureka.
    // ==========================================

    @Bean
    public RouterFunction<ServerResponse> candidatRoute() {
        return route("candidat")
                .route(path("/mic1/candidates/**"), http())
                .filter(lb("candidat"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> jobRoute() {
        return route("job")
                .route(path("/api/jobs/**"), http())
                .filter(lb("job"))
                .build();
    }

    @Bean
    public RouterFunction<ServerResponse> categoryRoute() {
        return route("categories")
                .route(path("/api/categories/**"), http())
                .filter(lb("job"))
                .build();
    }

}
