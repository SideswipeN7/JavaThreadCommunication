package service;


import service.Messages.Message;
import service.Messages.MessageService;
import service.Messages.MessageTypes;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Flow;

public abstract class Service extends Thread implements Flow.Subscriber<Message> {
    private Queue<Message> messages = new ArrayDeque<>();
    private List<MessageTypes> supported_;
    private int id_;
    private Flow.Subscription subscription_;
    private Thread loopThread_;

    protected Service(List<MessageTypes> supported, int id, Runnable execute) {
        super(execute);
        supported_ = supported;
        id_ = id;
        //subscribe to messages
        MessageService.getInstance().subscribe(this);
        loop();
    }

    protected boolean isAddressedToMe(Message message) {
        return message.getId_() == id_;
    }

    protected boolean isSupported(Message message) {
        return supported_.parallelStream().anyMatch(messageTypes -> messageTypes == message.getType());
    }

    protected abstract void processMessage(Message message);

    private void loop() {
        loopThread_ = new Thread(() -> {
            while (true) {
                var message = messages.poll();
                if (message != null) {
                    processMessage(message);
                }
            }
        });
        loopThread_.start();
    }

    @Override
    public void onSubscribe(Flow.Subscription subscription) {
        this.subscription_ = subscription;
        subscription.request(1);
        System.out.println("Subscribed!");
    }

    @Override
    public void onNext(Message item) {
        messages.add(item);
        subscription_.request(1);
    }

    @Override
    public void onError(Throwable throwable) {
        System.out.println("Already subscribed");
    }

    @Override
    public void onComplete() {
        //skip
    }

    protected int getServiceId() {
        return id_;
    }

    @Override
    public void interrupt() {
        super.interrupt();
        loopThread_.interrupt();
    }
}
