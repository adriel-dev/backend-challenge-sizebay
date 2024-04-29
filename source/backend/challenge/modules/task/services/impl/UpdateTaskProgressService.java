package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IUpdateTaskProgressService;

import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;

    public UpdateTaskProgressService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(TaskProgressDTO taskProgressDTO) {
        var foundTask = taskRepository.index(taskProgressDTO.getId());
        foundTask.setProgress(taskProgressDTO.getProgress());
        return foundTask;
    }

}
