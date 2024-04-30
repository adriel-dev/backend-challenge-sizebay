package backend.challenge.modules.task.converter;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.api.request.CreateTaskRequest;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskProgressRequest;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskRequest;
import backend.challenge.modules.task.infra.http.api.response.TaskResponse;
import backend.challenge.modules.task.models.Task;

import javax.inject.Singleton;
import java.util.UUID;

@Singleton
public class TaskConverter implements ITaskConverter {

    @Override
    public TaskDTO createTaskRequestToTaskDto(CreateTaskRequest createTaskRequest) {
        return TaskDTO.create()
                .setTitle(createTaskRequest.getTitle())
                .setDescription(createTaskRequest.getDescription());
    }

    @Override
    public TaskDTO updateTaskRequestToTaskDto(UpdateTaskRequest updateTaskRequest) {
        return TaskDTO.create()
                .setTitle(updateTaskRequest.getTitle())
                .setDescription(updateTaskRequest.getDescription());
    }

    @Override
    public Task taskDtoToTask(TaskDTO taskDTO) {
        return new Task()
                .setTitle(taskDTO.getTitle())
                .setDescription(taskDTO.getDescription());
    }

    @Override
    public TaskProgressDTO updateTaskProgressRequestToTaskProgressDto(UUID taskId, UpdateTaskProgressRequest updateTaskProgressRequest) {
        return TaskProgressDTO.create()
                .setId(taskId)
                .setProgress(updateTaskProgressRequest.getProgress());
    }

    @Override
    public TaskResponse taskToTaskResponse(Task task) {
        return new TaskResponse()
                .setId(task.getId())
                .setTitle(task.getTitle())
                .setDescription(task.getDescription())
                .setStatus(task.getStatus())
                .setProgress(task.getProgress())
                .setCreatedAt(task.getCreatedAt().toString());
    }

}
