package service.Messages;

public class Message {
    private int id_;
    private String value_;
    private MessageTypes type_;

    public Message(int id, MessageTypes type, String value) {
        this.id_ = id;
        this.type_ = type;
        this.value_ = value;
    }

    public int getId_() {
        return id_;
    }

    public String getValue() {
        return value_;
    }

    public MessageTypes getType() {
        return type_;
    }
}
