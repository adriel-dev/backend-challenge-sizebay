package backend.challenge.modules.task.models;

import backend.challenge.modules.task.enums.TaskStatus;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

	public Task() {
		this.progress = 0;
		this.status = TaskStatus.PROGRESS;
		this.createdAt = LocalDateTime.now();
	}

	public void updateFrom(Task task) {
		this.title = task.getTitle();
		this.description = task.getDescription();
	}

}
