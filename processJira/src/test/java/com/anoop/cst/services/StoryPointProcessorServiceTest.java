package com.anoop.cst.services;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import com.anoop.cst.models.Message;

import rx.Observable;

public class StoryPointProcessorServiceTest {

    @Mock
    private JiraService mockJiraService;
    @Mock
    private SQSService mockSQSService;

    @InjectMocks
    private StoryPointProcessorService service;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testTransform() {
        Observable<Integer> totalStoryPoints = Observable.just(10);
        when(mockJiraService.getTotalStoryPoints(Mockito.anyString())).thenReturn(totalStoryPoints);

        service.transform("dummy query", "p1Issues");
        Message message = new Message("p1Issues", 10);

        verify(mockJiraService, times(1)).getTotalStoryPoints(Mockito.anyString());
        verify(mockSQSService, times(1)).putMessageAsync(message);
    }
}