package backend.challenge.modules.task.repositories;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exception.TaskNotFoundException;
import backend.challenge.modules.task.models.Task;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Singleton
public class TaskRepository implements ITaskRepository {

	private final List<Task> inMemoryTaskDb = new ArrayList<>();

	private final ITaskConverter taskConverter;

	@Inject
	public TaskRepository(ITaskConverter taskConverter) {
		this.taskConverter = taskConverter;
	}

	@Override
	public Task index(final UUID taskId) {
		for(Task task : inMemoryTaskDb) {
			if(taskId.equals(task.getId())){
				return task;
			}
		}
		throw new TaskNotFoundException(taskId);
	}

	@Override
	public List<Task> show() {
		return this.inMemoryTaskDb;
	}

	@Override
	public Task create(final TaskDTO taskDTO) {
		var taskToCreate = new Task()
				.setId(UUID.randomUUID())
				.setTitle(taskDTO.getTitle())
				.setDescription(taskDTO.getDescription());
		inMemoryTaskDb.add(taskToCreate);
		return taskToCreate;
	}

	@Override
	public Task update(final UUID taskId, final TaskDTO taskDTO) {
		var foundTask = index(taskId);
		var task = taskConverter.taskDtoToTask(taskDTO);
		foundTask.updateFrom(task);
		return foundTask;
	}

	@Override
	public void delete(final UUID taskId) {
 		var foundTask = index(taskId);
		inMemoryTaskDb.remove(foundTask);
	}

}
