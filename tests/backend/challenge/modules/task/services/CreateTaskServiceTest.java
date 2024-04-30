package backend.challenge.modules.task.services;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.CreateTaskService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(KikahaRunner.class)
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		final ITaskRepository taskRepository = new TaskRepository(taskConverter);

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		//given
		final var taskTitle = "Task test";
		final var taskDescription = "Test description";
		var taskDto = TaskDTO.create()
				.setTitle(taskTitle)
				.setDescription(taskDescription);
		//when
		var savedTask = createTaskService.execute(taskDto);
		//then
		Assert.assertNotNull(savedTask);
		Assert.assertNotNull(savedTask.getId());
		Assert.assertEquals(savedTask.getTitle(), taskTitle);
		Assert.assertEquals(savedTask.getDescription(), taskDescription);
		Assert.assertEquals(savedTask.getProgress(), 0);
		Assert.assertEquals(savedTask.getStatus(), TaskStatus.PROGRESS);
		Assert.assertNotNull(savedTask.getCreatedAt());
	}




}