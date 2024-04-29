package backend.challenge.modules.task.services;

import backend.challenge.modules.task.models.Task;

import java.util.UUID;

public interface IRetrieveTaskByIdService {

	Task execute(UUID taskId);

}
