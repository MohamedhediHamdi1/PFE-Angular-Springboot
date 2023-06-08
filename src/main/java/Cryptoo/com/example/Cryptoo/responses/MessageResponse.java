package Cryptoo.com.example.Cryptoo.responses;

public class MessageResponse {
    private String messageId;
    private String message;
    private String datemessage;
    private String senderId;

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    public String getDatemessage() {
        return datemessage;
    }

    public void setDatemessage(String datemessage) {
        this.datemessage = datemessage;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }
}
