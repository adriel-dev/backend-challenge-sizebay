package backend.challenge.modules.task.services;

import backend.challenge.modules.task.dtos.TaskDTO;
import backend.challenge.modules.task.models.Task;

import java.util.UUID;

public interface IUpdateTaskService {

	Task execute(UUID taskId, TaskDTO taskDto);

}
