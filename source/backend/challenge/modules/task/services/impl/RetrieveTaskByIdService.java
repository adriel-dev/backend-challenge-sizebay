package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IRetrieveTaskByIdService;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class RetrieveTaskByIdService implements IRetrieveTaskByIdService {

    private final ITaskRepository taskRepository;

    public RetrieveTaskByIdService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(UUID taskId) {
        return taskRepository.index(taskId);
    }

}
