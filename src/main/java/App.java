import impl.Computer;
import impl.Phone;
import service.Messages.Message;
import service.Messages.MessageTypes;
import service.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class App extends Service {
    private static int ID = 0;
    private static List<MessageTypes> MESSAGES = new ArrayList<>();
    private static App instance = new App();

    private List<Service> services = new ArrayList<>();

    private App() {
        super(MESSAGES, ID, () -> {});
    }

    public static App getInstance() {
        return instance;
    }

    @Override
    protected void processMessage(Message message) {
        System.out.println("APP> " + message.getValue());
    }

    public void Start() {
        services.add(new Phone(Arrays.asList(MessageTypes.Type_2, MessageTypes.Type_1), 1));
        services.add(new Phone(Arrays.asList(MessageTypes.Type_2, MessageTypes.Type_1), 2));
        services.add(new Phone(Collections.singletonList(MessageTypes.Type_1), 3));

        services.add(new Computer(Arrays.asList(MessageTypes.Type_2, MessageTypes.Type_1), 4));
        services.add(new Computer(Arrays.asList(MessageTypes.Type_2, MessageTypes.Type_1), 5));


        for(var service : services){
            service.start();
        }
    }

    public void end() {
        for(var service : services){
            service.interrupt();
        }
        this.interrupt();
    }
}
