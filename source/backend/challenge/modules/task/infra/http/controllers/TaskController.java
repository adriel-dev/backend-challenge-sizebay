package backend.challenge.modules.task.infra.http.controllers;

import backend.challenge.modules.task.converter.ITaskConverter;
import backend.challenge.modules.task.exception.TaskNotFoundException;
import backend.challenge.modules.task.infra.http.api.request.CreateTaskRequest;
import backend.challenge.modules.task.infra.http.api.request.UpdateTaskRequest;
import backend.challenge.modules.task.infra.http.error.ErrorResponse;
import backend.challenge.modules.task.models.Task;
import backend.challenge.modules.task.services.*;
import kikaha.urouting.api.*;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;
import java.util.stream.Collectors;

@Singleton
@Path("tasks")
public class TaskController {

	private final ICreateTaskService createTaskService;
	private final IDeleteTaskService deleteTaskService;
	private final IRetrieveAllTasksService retrieveAllTasksService;
	private final IRetrieveTaskByIdService retrieveTaskByIdService;
	private final IUpdateTaskService updateTaskService;

	private final ITaskConverter taskConverter;

	@Inject
	public TaskController(
			final ICreateTaskService createTaskService,
			final IDeleteTaskService deleteTaskService,
			final IRetrieveAllTasksService retrieveAllTasksService,
			final IRetrieveTaskByIdService retrieveTaskByIdService,
			final IUpdateTaskService updateTaskService,
			final ITaskConverter taskConverter
			) {
		this.createTaskService = createTaskService;
		this.deleteTaskService = deleteTaskService;
		this.retrieveAllTasksService = retrieveAllTasksService;
		this.retrieveTaskByIdService = retrieveTaskByIdService;
		this.updateTaskService = updateTaskService;
		this.taskConverter = taskConverter;
	}

	@GET
	public Response show() {
		var tasksList = retrieveAllTasksService.execute();
		var tasksResponse = tasksList.stream().map(taskConverter::taskToTaskResponse).collect(Collectors.toList());
		return DefaultResponse.ok().entity(tasksResponse);
	}

	@GET
	@Path("single/{taskId}")
	public Response index(@PathParam("taskId") String taskId) {
		try {
			var foundTask = retrieveTaskByIdService.execute(UUID.fromString(taskId));
			return DefaultResponse.ok().entity(taskConverter.taskToTaskResponse(foundTask));
		} catch (TaskNotFoundException e) {
			var errorResponse = ErrorResponse.create()
					.setError("Not Found")
					.setStatus(404)
					.setMessage(e.getMessage())
					.setTimestamp(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
			return DefaultResponse.notFound().entity(errorResponse);
		}

	}

	@POST
	public Response create(CreateTaskRequest task) {
		var taskDto = taskConverter.createTaskRequestToTaskDto(task);
		var createdTask = createTaskService.execute(taskDto);
		return DefaultResponse.created("tasks/single/"+createdTask.getId()).entity(taskConverter.taskToTaskResponse(createdTask));
	}

	@PUT
	@Path("single/{taskId}")
	public Response update(@PathParam("taskId") String taskId, UpdateTaskRequest task) {
		try {
			var taskDto = taskConverter.updateTaskRequestToTaskDto(task);
			var updatedTask = updateTaskService.execute(UUID.fromString(taskId), taskDto);
			return DefaultResponse.ok().entity(taskConverter.taskToTaskResponse(updatedTask));
		} catch (TaskNotFoundException e) {
			var errorResponse = ErrorResponse.create()
					.setError("Not Found")
					.setStatus(404)
					.setMessage(e.getMessage())
					.setTimestamp(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
			return DefaultResponse.notFound().entity(errorResponse);
		}
	}

	@DELETE
	@Path("single/{taskId}")
	public Response delete(@PathParam("taskId") String taskId) {
		try {
			deleteTaskService.execute(UUID.fromString(taskId));
			return DefaultResponse.noContent();
		} catch (TaskNotFoundException e) {
			var errorResponse = ErrorResponse.create()
					.setError("Not Found")
					.setStatus(404)
					.setMessage(e.getMessage())
					.setTimestamp(Instant.now().truncatedTo(ChronoUnit.SECONDS).toString());
			return DefaultResponse.notFound().entity(errorResponse);
		}
	}

}
