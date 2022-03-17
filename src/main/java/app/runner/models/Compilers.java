package app.runner.models;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Compilers {
    private Set<String> list;
    private Map<String, String> fileTypes;
    private Map<String, String> commands;

    public Compilers() {
        list = new HashSet<>();
        fileTypes = new HashMap<>();
        commands = new HashMap<>();

        //JavaScript
        list.add("JavaScript");
        fileTypes.put("JavaScript", ".js");
        commands.put("JavaScript", "node Main.js");

        //Golang
        list.add("Golang");
        fileTypes.put("Golang", ".go");
        commands.put("Golang", "go run Main.go");
    }

    public String getFileType(String compiler) {
        return fileTypes.get(compiler);
    }

    public Set<String> getCompilers() {
        return list;
    }

    public String getCommands(String compiler) {
        return commands.get(compiler);
    }
}

