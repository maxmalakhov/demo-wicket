package demo.services;

import demo.model.Question;
import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class QuestionDataServiceTest extends TestCase
{

    @InjectMocks
    private QuestionDataService service;

    @Mock
    private QuestionDataProvider provider;

    @Test
    public void testAllQuestions() throws Exception
    {
        List<Question> testList = createListOfTestQuestions();

        when(provider.getAll()).thenReturn(testList);
        List<Question> actual = service.allQuestions();

        verify(provider).getAll();

        Assert.assertNotNull(actual);
        Assert.assertEquals(testList.size(), actual.size());
    }

    @Test
    public void testSearchQuestions() throws Exception
    {
        List<Question> testList = createListOfTestQuestions();
        String searchTerm = "term";

        when(provider.searchBy(searchTerm)).thenReturn(testList);
        List<Question> actual = service.searchQuestions(searchTerm);

        verify(provider).searchBy(searchTerm);

        Assert.assertNotNull(actual);
        Assert.assertEquals(testList.size(), actual.size());
    }

    private List<Question> createListOfTestQuestions()
    {
        List<Question> questions = new ArrayList<>(2);
        questions.add(new Question());
        questions.add(new Question());

        return questions;
    }
}