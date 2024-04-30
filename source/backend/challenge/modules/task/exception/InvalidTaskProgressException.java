package backend.challenge.modules.task.exception;

import java.util.UUID;

public class InvalidTaskProgressException extends RuntimeException {

    public InvalidTaskProgressException(){
        super("The task progress value must not be less than 0 or greater than 100!");
    }

}
