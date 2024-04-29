package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IUpdateTaskService;

import javax.inject.Singleton;

@Singleton
public class UpdateTaskService implements IUpdateTaskService {

    private final ITaskRepository taskRepository;

    public UpdateTaskService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(Task task) {
        return taskRepository.update(task);
    }

}
