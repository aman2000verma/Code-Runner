package app.runner.models;

import javax.persistence.*;

/**
 * This is the model which is going to be stored in the database.
 */
@Entity
@Table(name = "Runner")
public class Code {
    @Id
    @Column(unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(nullable = false)
    private String ip;
    @Column(nullable = false)
    private String syntax;
    @Column(nullable = false)
    private String compiler;
    @Column
    private boolean status;
    @Column(nullable = true)
    private String input;
    @Column(nullable = true)
    private String output;

    public Code(String ip, String syntax, String compiler, String input) {
        this.ip = ip;
        this.syntax = syntax;
        this.compiler = compiler;
        this.input = input;
    }

    public Code() {

    }

    @Override
    public String toString() {
        return "Code{" +
                "id=" + id +
                ", ip='" + ip + '\'' +
                ", syntax='" + syntax + '\'' +
                ", compiler='" + compiler + '\'' +
                ", status=" + status +
                ", input='" + input + '\'' +
                ", output='" + output + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getSyntax() {
        return syntax;
    }

    public void setSyntax(String syntax) {
        this.syntax = syntax;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

}
