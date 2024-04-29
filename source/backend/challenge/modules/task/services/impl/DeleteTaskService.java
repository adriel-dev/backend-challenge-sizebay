package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IDeleteTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class DeleteTaskService implements IDeleteTaskService {

	private final ITaskRepository taskRepository;

	@Inject
	public DeleteTaskService(final ITaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public void execute(UUID taskId) {
		taskRepository.delete(taskId);
	}

}
