package backend.challenge.modules.task.services;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.exception.TaskNotFoundException;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.DeleteTaskService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class DeleteTaskServiceTest {

	private IDeleteTaskService deleteTaskService;

	private ITaskRepository taskRepository;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		taskRepository = new TaskRepository(taskConverter);

		deleteTaskService = new DeleteTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToDeleteTaskById() {
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		//when
		deleteTaskService.execute(taskId);
		//then
		Assert.assertThrows(TaskNotFoundException.class, () -> taskRepository.index(taskId));
	}




}