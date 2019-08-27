package impl;

import service.Messages.Message;
import service.Messages.MessageTypes;
import service.Service;

import java.util.List;

public class Computer extends Service {
    public Computer(List<MessageTypes> supported, int id) {
        super(supported, id, () -> {
            while (true) {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("COMPUTER - todo");
            }
        });
    }

    @Override
    protected void processMessage(Message message) {
        if (isSupported(message)) {

            if (MessageTypes.Type_1 == message.getType()) {
                if (isAddressedToMe(message)) {
                    System.out.println("COMPUTER" + getServiceId() + "> " + message.getValue());
                }

            } else if (MessageTypes.Type_2 == message.getType()) {
                if (isAddressedToMe(message)) {
                    System.out.println("COMPUTER" + getServiceId() + "> " + message.getValue());
                }
            }
        }
    }

}
