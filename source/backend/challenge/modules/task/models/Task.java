package backend.challenge.modules.task.models;

import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter
@Setter
public class Task {

	private UUID id;
	private String title;
	private String description;
	private int progress;
	private TaskStatus status;
	private Date createdAt;

}
