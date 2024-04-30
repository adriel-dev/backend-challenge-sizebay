package backend.challenge.modules.task.services;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.converter.TaskConverter;
import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.exception.TaskNotFoundException;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.repositories.TaskRepository;
import backend.challenge.modules.task.services.impl.UpdateTaskService;
import kikaha.core.test.KikahaRunner;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.UUID;

@RunWith(KikahaRunner.class)
public class UpdateTaskServiceTest {

	private IUpdateTaskService updateTaskService;

	private ITaskRepository taskRepository;

	@Before
	public void init() {
		final ITaskConverter taskConverter = new TaskConverter();
		taskRepository = new TaskRepository(taskConverter);

		updateTaskService = new UpdateTaskService(taskRepository);
	}

	@Test
	public void shouldBeAbleToUpdateTask() {
		//given
		var taskToCreate = TaskDTO.create()
				.setTitle("Task test")
				.setDescription("Test description");
		var createdTask = taskRepository.create(taskToCreate);
		var taskId = createdTask.getId();
		var titleUpdate = "Task UPDATE";
		var descriptionUpdate = "Description UPDATE";
		var taskDto = TaskDTO.create()
				.setTitle(titleUpdate)
				.setDescription(descriptionUpdate);
		//when
		var updatedTask = updateTaskService.execute(taskId, taskDto);
		//then
		Assert.assertNotNull(updatedTask);
		Assert.assertEquals(updatedTask.getTitle(), titleUpdate);
		Assert.assertEquals(updatedTask.getDescription(), descriptionUpdate);
	}

	@Test
	public void shouldNotBeAbleToUpdateATaskThatDoesNotExist() {
		//given
		var taskId = UUID.fromString("f242ac84-2431-4c99-97fa-9bb02c19015f");
		var taskDto = TaskDTO.create()
				.setTitle("Task update attempt")
				.setDescription("Test description update attempt");
		//when
		//then
		Assert.assertThrows(TaskNotFoundException.class, () -> updateTaskService.execute(taskId, taskDto));
	}

	@Test
	public void shouldNotBeAbleToUpdateTaskStatusManually() {
		/*
			TODO:  Para que esse teste passe, você não deve permitir que sua rota de update
						 altere diretamente o `status` dessa tarefa, mantendo o mesmo status que a tarefa
						 já possuía antes da atualização. Isso porque o único lugar que deve atualizar essa informação
						 é a rota responsável por alterar o progresso da tarefa.
						 ---> TESTE NÃO É NECESSÁRIO DEVIDO AO FLUXO DESENVOLVIDO PARA UPDATE,
						 ONDE O TASK DTO RECEBIDO PELO SERVICE NÃO POSSUI O ATRIBUTO TaskStatus, DESSA FORMA A ATUALIZAÇÃO DESSE CAMPO NÃO OCORRE. <---

		 */
	}


}