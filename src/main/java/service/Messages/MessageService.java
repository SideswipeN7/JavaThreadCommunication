package service.Messages;

import java.util.concurrent.SubmissionPublisher;

public class MessageService extends SubmissionPublisher<Message> {
    private static MessageService instance_ = new MessageService();


    public static MessageService getInstance() {
        return instance_;
    }


    public void putMessage(Message message) {
        submit(message);
    }
}
