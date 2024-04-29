package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.ICreateTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CreateTaskService implements ICreateTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public CreateTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public Task execute(TaskDTO taskDTO) {
		return taskRepository.create(taskDTO);
	}

}
