package backend.challenge.modules.task.services;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.RetrieveAllTasksService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;

@RunWith( KikahaRunner.class )
public class RetrieveAllTasksServiceTest {

	private IRetrieveAllTasksService retrieveAllTasksService;

	private ITaskRepository taskRepository;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		taskRepository = new TaskRepository(taskConverter);

		retrieveAllTasksService = new RetrieveAllTasksService(taskRepository);
	}

	@Test
	public void shouldBeAbleToListTheTasks() {
		//given
		int numOfRecords = 5;
		populateInMemoryDb(numOfRecords);
		//when
		var tasksList = retrieveAllTasksService.execute();
		//then
		Assert.assertNotNull(tasksList);
		Assert.assertEquals(tasksList.size(), numOfRecords);
	}

	private void populateInMemoryDb(int numOfRecords) {
		for (int i = 0; i < numOfRecords; i++) {
			taskRepository.create(
					TaskDTO.create()
							.setTitle("Task test "+i)
							.setDescription("Test description "+i)
			);
		}
	}

}