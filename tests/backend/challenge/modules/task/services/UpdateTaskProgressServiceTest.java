package backend.challenge.modules.task.services;


import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exception.InvalidTaskProgressException;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.UpdateTaskProgressService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(KikahaRunner.class)
public class UpdateTaskProgressServiceTest {

	private IUpdateTaskProgressService updateTaskProgressService;

	private ITaskRepository taskRepository;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		taskRepository = new TaskRepository(taskConverter);

		updateTaskProgressService = new UpdateTaskProgressService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTaskProgress() {
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		var taskProgressDto = TaskProgressDTO.create()
				.setId(taskId)
				.setProgress(100);
		//when
		var updatedTask = updateTaskProgressService.execute(taskProgressDto);
		//then
		Assert.assertEquals(updatedTask.getProgress(), 100);
	}

	@Test
	public void shouldBeAbleToUpdateOnlyTaskStatusWhenProgressEqualsOneHundred() {
		/*
			TODO:  Para que esse teste passe, sua aplicação deve permitir que sejam
		         alterado apenas o campo `status`, quando o progresso for igual a 100.
		*/
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		var taskProgressDto = TaskProgressDTO.create()
				.setId(taskId)
				.setProgress(100);
		//when
		var updatedTask = updateTaskProgressService.execute(taskProgressDto);
		//then
		Assert.assertEquals(updatedTask.getProgress(), 100);
		Assert.assertEquals(updatedTask.getStatus(), TaskStatus.COMPLETE);
	}

	@Test
	public void shouldGetExceptionWhenProgressGreaterThanOneHundred() {
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		var taskProgressDto = TaskProgressDTO.create()
				.setId(taskId)
				.setProgress(101);
		//when
		//then
		Assert.assertThrows(InvalidTaskProgressException.class, () -> updateTaskProgressService.execute(taskProgressDto));
	}


	@Test
	public void shouldGetExceptionWhenProgressLessThanZero() {
		//given
		var taskDto = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskDto);
		var taskId = createdTask.getId();
		var taskProgressDto = TaskProgressDTO.create()
				.setId(taskId)
				.setProgress(-1);
		//when
		//then
		Assert.assertThrows(InvalidTaskProgressException.class, () -> updateTaskProgressService.execute(taskProgressDto));
	}

}
