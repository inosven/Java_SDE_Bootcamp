package org.example.service;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.GetQueueUrlResult;
import org.example.ApplicationBootstrap;
import org.example.ApplicationBootstrapTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ApplicationBootstrap.class)
public class MessageServiceTest {
    @Autowired
    private MessageService messageService;
    @Autowired
    private AmazonSQS sqs;
    @Mock
    private GetQueueUrlResult getQueueUrl;

    @Test
    public void sendMessage() {
        messageService.sendMessage("TestQueue","test message", 1);
    }
//        when(sqs.getQueueUrl(anyString())).thenReturn(getQueueUrl);
//        messageService.sendMessage("test", "test message", 1);
//        verify(sqs, times(1)).sendMessage(any(sendMessageRequest().class));
//    }
}
