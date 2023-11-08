package dev.patika.plus.util;

public class Response {
    private int response;
    private String process;

    private Response() {}

    private Response(int response) {
        this.response = response;
    }

    private Response(int response, String process) {
        this.response = response;
        this.process = process;
    }

    public static Response form() {
        return new Response();
    }

    public static Response form(int response) {
        return new Response(response);
    }

    public static Response form(int response, String process) {
        return new Response(response, process);
    }

    public static Response form(Response response) {
        return response;
    }

    public Response withResponse(int response) {
        this.response = response;
        return this;
    }

    public Response withProcess(String process) {
        this.process = process;
        return this;
    }

    public void handleResponse() {
        if (response == -1) {
            Dialog.of(Dialog.Type.MESSAGE)
                    .withMessage("An error occurred while " + process + ".")
                    .withTitle("Error")
                    .withBehaviour(Dialog.BehaviourType.ERROR)
                    .display();
        }
        else {
            Dialog.of(Dialog.Type.MESSAGE)
                    .withMessage("Successfully " + process + ".")
                    .withTitle("Success")
                    .withBehaviour(Dialog.BehaviourType.INFORMATION)
                    .display();
        }
    }

    public int getResponse() {
        return response;
    }

    public String getProcess() {
        return process;
    }
}
