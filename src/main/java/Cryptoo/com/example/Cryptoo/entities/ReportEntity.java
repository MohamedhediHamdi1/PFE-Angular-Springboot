package Cryptoo.com.example.Cryptoo.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity(name = "report")
public class ReportEntity implements Serializable {

    private static final long serialVersionUID = -3704114801414402541L;

    @Id
    private String reportId;
    private String messageId;
    private String reportedMessage;
    private String reportUser;
    private String reportedUser;
    private String admin;

    public String getReportId() {
        return reportId;
    }

    public void setReportId(String reportId) {
        this.reportId = reportId;
    }

    public String getReportedMessage() {
        return reportedMessage;
    }

    public void setReportedMessage(String reportedMessage) {
        this.reportedMessage = reportedMessage;
    }

    public String getReportUser() {
        return reportUser;
    }

    public void setReportUser(String reportUser) {
        this.reportUser = reportUser;
    }

    public String getReportedUser() {
        return reportedUser;
    }

    public void setReportedUser(String reportedUser) {
        this.reportedUser = reportedUser;
    }

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }
}
