package org.example.service;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.Message;
import com.amazonaws.services.sqs.model.ReceiveMessageRequest;
import com.amazonaws.transform.MapEntry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SQSMessageService {
    @Autowired
    private AmazonSQS sqsClient;

    public String queueName = "TestQueue";
    public Logger logger = LoggerFactory.getLogger(getClass());

    public void receiveMessage() {
        logger.info("start receiving message");
        ReceiveMessageRequest receiveMessageRequest = new ReceiveMessageRequest(queueName);
        List<Message> messages = sqsClient.receiveMessage(receiveMessageRequest).getMessages();

        for (Message message1 : messages) {
            logger.info("message received: " + message1.getBody());
            Map<String, String> message1Attributes = message1.getAttributes();
            for (Map.Entry<String, String> entry: message1Attributes.entrySet()) {
                logger.info("name is {}, value is {}", entry.getKey(), entry.getValue());
            }
            sqsClient.deleteMessage(getQueueUrl(queueName), message1.getReceiptHandle());
        }
    }

    public String getQueueUrl(String queueName) {
        return sqsClient.getQueueUrl(queueName).getQueueUrl();
    }
}
