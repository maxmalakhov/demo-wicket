package demo.pages;

import demo.services.QuestionDataService;
import org.apache.wicket.Component;
import org.apache.wicket.markup.html.WebPage;
import org.apache.wicket.request.mapper.parameter.PageParameters;
import org.apache.wicket.spring.injection.annot.SpringBean;


public class HomePage extends WebPage {
	private static final long serialVersionUID = 1L;

    @SpringBean
    private QuestionDataService  questionDataService;

    private final ResultObject resultObject = new ResultObject();

	public HomePage(final PageParameters parameters) {
		super(parameters);
    }

    @Override
    protected void onInitialize() {
        super.onInitialize();

        Component resultPanel = new ResultPanel("resultPanel", resultObject);
        resultPanel.setOutputMarkupId(true);

        Component searchPanel = new SearchPanel("searchPanel", resultPanel, resultObject);

        add(searchPanel);
        add(resultPanel);
    }
}
