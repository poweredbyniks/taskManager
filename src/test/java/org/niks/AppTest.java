package org.niks;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;

class AppTest {

    @Test
    public void testHelp() throws IOException {
        String helpCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nhelp\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(helpCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.HELP_OUTPUT, os.toString(), "Incorrect help output");
    }

    @Test
    public void testProjectCreate() throws IOException {
        String projectCreateCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\n" +
                "Project description\n30.10.2020\n31.10.2020\nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectCreateCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_CREATE_OUTPUT, os.toString(), "Incorrect project-create output");
    }

    @Test
    public void testProjectList() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nproject-list\nproject-list \n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_LIST_OUTPUT, os.toString(), "Incorrect project-list output");
    }

    @Test
    public void testTaskCreate() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020 \nexit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_CREATE_OUTPUT, os.toString(), "Incorrect task-create output");
    }

    @Test
    public void testTaskList() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\ntask-create\nnewProject\nnewTask\nTask description" +
                "\n30.11.2020\n31.11.2020\ntask-list\n exit\n\u001a";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.TASK_LIST_OUTPUT, os.toString(), "Incorrect task-create output");
    }

    @Test
    public void testProjectIsolation() throws IOException {
        String projectListCommand = "user-reg\nnewUser\n123\nuser-login\nnewUser\n123\nproject-create\nnewProject\nProject description\n30.10.2020" +
                "\n31.10.2020\nuser-reg\nsecondUser\n321\nuser-login\nsecondUser\n321\nproject-create\nsecondProject\nProject description\n" +
                "29.12.2020\n30.12.2020\nproject-list";
        System.setIn(new ByteArrayInputStream(projectListCommand.getBytes()));
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        System.setOut(new PrintStream(os));
        App.main(new String[0]);
        Assertions.assertEquals(TestOutputs.PROJECT_ISOLATION_OUTPUT, os.toString(), "Incorrect project-list output");
    }
}


