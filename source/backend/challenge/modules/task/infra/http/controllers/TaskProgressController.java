package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.exception.InvalidTaskProgressException;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskProgressRequest;
import backend.challenge.modules.task.infra.http.error.ErrorResponse;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

@Singleton
@Path("tasks/progress")
public class TaskProgressController {

	private final IUpdateTaskProgressService updateTaskProgressService;

	private final ITaskConverter taskConverter;

	@Inject
	public TaskProgressController(final IUpdateTaskProgressService updateTaskProgressService, ITaskConverter taskConverter) {
		this.updateTaskProgressService = updateTaskProgressService;
        this.taskConverter = taskConverter;
    }

	@PUT
	@Path("single/{taskId}")
	public Response updateProgress(@PathParam("taskId") String taskId, UpdateTaskProgressRequest updateTaskProgressRequest) {
		try {
			var taskProgressDto = taskConverter.updateTaskProgressRequestToTaskProgressDto(UUID.fromString(taskId), updateTaskProgressRequest);
			var updatedTask = updateTaskProgressService.execute(taskProgressDto);
			return DefaultResponse.ok().entity(taskConverter.taskToTaskResponse(updatedTask));
		} catch (InvalidTaskProgressException e) {
			var errorResponse = ErrorResponse.create()
				.setError("Bad Request")
				.setStatus(400)
				.setMessage(e.getMessage())
				.setTimestamp(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
			return DefaultResponse.badRequest().entity(errorResponse);
		}
	}

}
