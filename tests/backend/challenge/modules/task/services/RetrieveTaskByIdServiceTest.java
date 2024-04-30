package backend.challenge.modules.task.services;


import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.RetrieveTaskByIdService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith( KikahaRunner.class )
public class RetrieveTaskByIdServiceTest {

	private IRetrieveTaskByIdService retrieveTaskByIdService;

	private ITaskRepository taskRepository;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		taskRepository = new TaskRepository(taskConverter);

		retrieveTaskByIdService = new RetrieveTaskByIdService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTaskById() {
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		//when
		var foundTask = retrieveTaskByIdService.execute(taskId);
		//then
		Assert.assertNotNull(foundTask);
		Assert.assertEquals(foundTask.getId(), taskId);
	}

}
