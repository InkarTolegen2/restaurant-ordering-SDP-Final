package restaurant;

import restaurant.api.Routes;
import restaurant.facade.RestaurantFacade;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class App {
    public static void main(String[] args) throws IOException {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : 7070;
        Path frontend = findFrontend();
        new Routes(new RestaurantFacade(), frontend).start(port);
        System.out.println("Frontend folder: " + frontend.toAbsolutePath().normalize());
        System.out.println("Open http://localhost:" + port);
    }

    private static Path findFrontend() {
        String configured = System.getProperty("frontend.dir");
        if (configured != null && !configured.isBlank()) {
            Path candidate = Path.of(configured).toAbsolutePath().normalize();
            if (Files.isRegularFile(candidate.resolve("index.html"))) return candidate;
        }

        Path current = Path.of("").toAbsolutePath().normalize();    
        for (int depth = 0; current != null && depth < 10; depth++, current = current.getParent()) {
            Path candidate = current.resolve("frontend");
            if (Files.isRegularFile(candidate.resolve("index.html"))) return candidate;
        }

        throw new IllegalStateException(
            "frontend/ folder with index.html not found. Set the Working directory to the project root " +
            "or use -Dfrontend.dir=<path-to-frontend>."
        );
    }
}
