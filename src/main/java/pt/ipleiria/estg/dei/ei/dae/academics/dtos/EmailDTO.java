package pt.ipleiria.estg.dei.ei.dae.academics.dtos;

public class EmailDTO {

    // <editor-fold desc="Fields">
    private String subject;

    private String body;

    // </editor-fold>

    // <editor-fold desc="Constructors">

    public EmailDTO() {
    }

    public EmailDTO(String to, String subject, String body) {
        this.subject = subject;
        this.body = body;
    }

    // </editor-fold>

    // <editor-fold desc="Getters and Setters">

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    // </editor-fold>

}
