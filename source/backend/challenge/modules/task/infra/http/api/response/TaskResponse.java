package backend.challenge.modules.task.infra.http.api.response;

import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class TaskResponse {

    private UUID id;
    private String title;
    private String description;
    private int progress;
    private TaskStatus status;
    private String createdAt;

}
