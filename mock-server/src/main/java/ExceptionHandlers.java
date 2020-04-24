import io.javalin.http.ExceptionHandler;

/**
 * This class shows the error message with the HTTP status.
 */
public abstract class ExceptionHandlers {
    public static <T extends Exception> ExceptionHandler<T> genericHandler(int status) {
        return (exception, ctx) -> {
            ctx.status(status);
            ctx.header("Content-Type", "application/json");
            ctx.result(String.format("{\"message\": \"%s\"}", exception.getMessage()));
        };
    }
}
