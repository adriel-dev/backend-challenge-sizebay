package backend.challenge.modules.task.services;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.CreateTaskService;
import kikaha.core.test.KikahaRunner;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith( KikahaRunner.class )
public class CreateTaskServiceTest {

	private ICreateTaskService createTaskService;

	private final ITaskConverter taskConverter;

	public CreateTaskServiceTest(ITaskConverter taskConverter) {
		this.taskConverter = taskConverter;
	}

	@Before
	public void init() {
		final ITaskRepository taskRepository = new TaskRepository(taskConverter);

		createTaskService = new CreateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToCreateANewTask() {
		// TODO:  Para que esse teste passe, sua aplicação deve permitir que
		//  uma tarefa seja criado, e retorne um json com a tarefa criada.
	}




}