package app.runner.models;

/**
 * This is the model of the request sent to the server.
 */
public class ReqBody {

    private String ip;
    private String syntax;
    private String compiler;
    private String input;

    public ReqBody(String ip, String syntax, String compiler, String input) {
        this.ip = ip;
        this.syntax = syntax;
        this.compiler = compiler;
        this.input = input;
    }

    @Override
    public String toString() {
        return "ReqBody{" +
                "ip='" + ip + '\'' +
                ", syntax='" + syntax + '\'' +
                ", compiler='" + compiler + '\'' +
                ", input='" + input + '\'' +
                '}';
    }

    public String getIp() {
        return ip;
    }

    public String getSyntax() {
        return syntax;
    }

    public String getCompiler() {
        return compiler;
    }

    public String getInput() {
        return input;
    }

}
