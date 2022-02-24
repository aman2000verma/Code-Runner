package app.runner;

import app.runner.models.Code;
import app.runner.models.Compilers;
import app.runner.models.ReqBody;
import app.runner.models.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.*;


@Service
public class ExecutionService {
    @Autowired
    Repository repo;
    Compilers compilers = new Compilers();
    Logger logger = LoggerFactory.getLogger(ExecutionService.class);

    /**
     * @param command The command to be executed from command-line
     * @return The result of the command-line execution
     */
    private CLResult runProcess(String command) throws Exception {
        Process pro = Runtime.getRuntime().exec(command);
        pro.waitFor();
        return new CLResult(pro.exitValue(), pro.getInputStream(), pro.getErrorStream());
    }

    /**
     * @param syntax   Code inside the file
     * @param compiler Compiler to be used for the program
     * @return The path where file is created
     */
    private String createFile(String syntax, String compiler) throws IOException {
        //Create a file in localStorage to be used for build and execution
        String fileName = "Main";
        String extension = compilers.getFileType(compiler);
        File file = new File(fileName + extension);
        if (file.exists()) {
            file.delete();
        }
        if (file.createNewFile()) {
            FileOutputStream fos = new FileOutputStream(file, true);
            fos.write(syntax.getBytes());
            fos.close();
            logger.info("File created: " + file.getCanonicalPath());
        } else {
            logger.error("File cannot be created");
        }
        return file.getCanonicalPath();
    }

    private void deleteFile(String path) {
        File file = new File(path);
        if (file.delete()) {
            logger.info(path + " deleted successfully");
            System.out.println();
        } else
            logger.error(path + " delete unsuccessful");
    }

    public Result runCode(ReqBody body) throws IOException {
        Code code = new Code(body.getIp(), body.getSyntax(), body.getCompiler(), body.getInput());
        try {
            //1. Create a file with required extension
            String sourceCodeFile = createFile(code.getSyntax(), code.getCompiler());

            //2. Compile and Execute the file using CL commands
            String cmd = compilers.getCommands(code.getCompiler());
            System.out.print(cmd + " - ");
            CLResult res = runProcess(cmd);
            if (res.getStatus() == 0) {
                logger.info(cmd + ": Success!");
                code.setStatus(true);
                code.setOutput(res.getOutput().toString());
            } else {
                logger.error(cmd + ": Failure!");
                code.setStatus(false);
                code.setOutput(res.getError().toString());
            }
            logger.info(res.getOutput());
            repo.save(code);

            //3. Delete the file
            deleteFile(sourceCodeFile);
        } catch (Exception e) {
        }
        //4. Return a Result object
        logger.info(code.toString());
        return new Result(code.getStatus(), code.getOutput());
    }
}

class CLResult {
    private int status;
    private InputStream output;
    private InputStream error;

    public CLResult(int status, InputStream output, InputStream error) {
        this.status = status;
        this.output = output;
        this.error = error;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getOutput() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(output));
        String s = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            s += line;
        }
        return s;
    }

    public void setOutput(InputStream output) {
        this.output = output;
    }

    public String getError() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(error));
        String s = "";
        String line = "";
        while ((line = br.readLine()) != null) {
            s += line;
        }
        return s;
    }

    public void setError(InputStream error) {
        this.error = error;
    }
}
