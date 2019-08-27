package impl;

import service.Messages.Message;
import service.Messages.MessageTypes;
import service.Service;

import java.util.List;

public class Phone extends Service {


    public Phone(List<MessageTypes> supported, int id) {
        super(supported, id, () -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("PHONE - todo");
            }
        });
    }

    @Override
    protected void processMessage(Message message) {
        if (isSupported(message)) {

            if (MessageTypes.Type_1 == message.getType()) {
                if (isAddressedToMe(message)) {
                    System.out.println("PHONE_" + getServiceId() + "> " + message.getValue());
                }

            } else if (MessageTypes.Type_2 == message.getType()) {
                System.out.println("PHONE_" + getServiceId() + "> " + message.getValue());
            }
        }
    }
}
