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

        //Java 8
        list.add("Java 8");
        fileTypes.put("Java 8", ".java");
        commands.put("Java 8", "cmd /c javac Main.java && java Main && del Main.class");

        //Dart
        list.add("Dart");
        fileTypes.put("Dart", ".dart");
        commands.put("Dart", "dart Main.dart");

        //JavaScript
        list.add("JavaScript");
        fileTypes.put("JavaScript", ".js");
        commands.put("JavaScript", "node Main.js");

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
