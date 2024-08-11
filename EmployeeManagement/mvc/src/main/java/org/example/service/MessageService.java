package org.example.service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    @Autowired
    private AmazonSQS sqs;

    public void sendMessage(String queueName, String message, int delaySeconds) {
        SendMessageRequest send_msg_request = new SendMessageRequest().withQueueUrl(getqueueURl(queueName))
                .withMessageBody(message)
                .withDelaySeconds(delaySeconds);
        sqs.sendMessage(send_msg_request);
    }

    private String getqueueURl(String queueName) {
        GetQueueUrlResult getQueueUrlResult = sqs.getQueueUrl(queueName);
        return getQueueUrlResult.getQueueUrl();
    }
}
