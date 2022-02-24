package app.runner.models;

/**
 * This is the model of the data being sent back to the client.
 */
public class Result {

    private boolean status;
    private String output;

    public Result(boolean status, String output) {
        this.status = status;
        this.output = output;
    }

    public boolean getStatus() {
        return status;
    }

    public String getOutput() {
        return output;
    }

    @Override
    public String toString() {
        return "Result{" +
                "status=" + status +
                ", output='" + output + '\'' +
                '}';
    }
}
