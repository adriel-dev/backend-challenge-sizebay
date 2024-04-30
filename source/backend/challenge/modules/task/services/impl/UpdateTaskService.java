package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IUpdateTaskService;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class UpdateTaskService implements IUpdateTaskService {

    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(UUID taskId, TaskDTO taskDto) {
        return taskRepository.update(taskId, taskDto);
    }

}
