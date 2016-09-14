package demo.services;

import demo.client.ClientException;
import demo.model.Question;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Max Malakhov on 9/7/16.
 */
@Service
public class QuestionDataService
{

    private static org.slf4j.Logger LOG = LoggerFactory.getLogger(QuestionDataService.class);

    @Autowired
    QuestionDataProvider dataProvider;

    /**
     * Get all question
     *
     * @return list of questions
     * @throws ClientException
     */
    public List<Question> allQuestions() throws ClientException
    {
        List<Question> questions = dataProvider.getAll();

        return questions;
    }

    /**
     * Get filtered question
     *
     * @param term - filter
     * @return list of questions
     * @throws ClientException
     */
    @Cacheable(cacheNames = "searchResult", key="#term")
    public List<Question> searchQuestions(String term) throws ClientException
    {
        List<Question> questions = dataProvider.searchBy(term);

        return questions;
    }
}
