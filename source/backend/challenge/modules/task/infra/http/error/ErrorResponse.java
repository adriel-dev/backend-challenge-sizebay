package backend.challenge.modules.task.infra.http.error;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor(staticName = "create")
public class ErrorResponse {

    private int status;
    private String error;
    private String message;
    private String timestamp;

}
