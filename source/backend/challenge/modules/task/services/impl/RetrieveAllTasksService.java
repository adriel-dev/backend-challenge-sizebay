package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IRetrieveAllTasksService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.List;

@Singleton
public class RetrieveAllTasksService implements IRetrieveAllTasksService {

	private final ITaskRepository taskRepository;

	@Inject
	public RetrieveAllTasksService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> execute() {
		return taskRepository.show();
	}

}
