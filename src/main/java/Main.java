import service.Messages.Message;
import service.Messages.MessageService;
import service.Messages.MessageTypes;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        App app = App.getInstance();
        app.Start();

        var push = MessageService.getInstance();


        push.putMessage(new Message(2, MessageTypes.Type_1, "Test__1"));
        push.putMessage(new Message(2, MessageTypes.Type_2, "Test__2"));
        Thread.sleep(200);
        push.putMessage(new Message(1, MessageTypes.Type_1, "Test__3"));
        push.putMessage(new Message(1, MessageTypes.Type_2, "Test__4"));

        app.end();
    }

}
