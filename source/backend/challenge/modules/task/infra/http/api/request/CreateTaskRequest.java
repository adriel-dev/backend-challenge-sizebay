package backend.challenge.modules.task.infra.http.api.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateTaskRequest {

	private String title;
	private String description;

}
