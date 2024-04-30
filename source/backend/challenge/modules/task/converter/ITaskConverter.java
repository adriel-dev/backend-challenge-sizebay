package backend.challenge.modules.task.converter;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.dtos.TaskProgressDTO;
import backend.challenge.modules.task.infra.http.api.request.CreateTaskRequest;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskProgressRequest;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskRequest;
import backend.challenge.modules.task.infra.http.api.response.TaskResponse;
import backend.challenge.modules.task.models.Task;

import java.util.UUID;

public interface ITaskConverter {
    TaskDTO createTaskRequestToTaskDto(CreateTaskRequest createTaskRequest);
    TaskDTO updateTaskRequestToTaskDto(UpdateTaskRequest updateTaskRequest);
    Task taskDtoToTask(TaskDTO taskDTO);
    TaskProgressDTO updateTaskProgressRequestToTaskProgressDto(UUID taskId, UpdateTaskProgressRequest updateTaskProgressRequest);
    TaskResponse taskToTaskResponse(Task task);
}
