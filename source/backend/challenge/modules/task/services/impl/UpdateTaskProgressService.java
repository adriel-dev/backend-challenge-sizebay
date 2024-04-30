package backend.challenge.modules.task.services.impl;

import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.enums.TaskStatus;
import backend.challenge.modules.task.exception.InvalidTaskProgressException;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.repositories.ITaskRepository;
import backend.challenge.modules.task.services.IUpdateTaskProgressService;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class UpdateTaskProgressService implements IUpdateTaskProgressService {

    private final ITaskRepository taskRepository;

    @Inject
    public UpdateTaskProgressService(ITaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task execute(TaskProgressDTO taskProgressDTO) {
        var progress = taskProgressDTO.getProgress();
        if(progress < 0 || progress > 100) { throw new InvalidTaskProgressException(); }
        var foundTask = taskRepository.index(taskProgressDTO.getId());
        foundTask.setProgress(progress);
        if(progress == 100) { foundTask.setStatus(TaskStatus.COMPLETE); }
        return foundTask;
    }

}
