package backend.challenge.modules.task.models;

import backend.challenge.modules.task.enums.TaskStatus;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class Task {

	private UUID id;
	private String title;
	private String description;
	private int progress;
	private TaskStatus status;
	private LocalDateTime createdAt;

	public void updateFrom(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
		this.progress = task.getProgress();
		this.status = task.getStatus();
		this.createdAt = task.getCreatedAt();
	}
}
