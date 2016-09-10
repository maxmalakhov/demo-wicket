## Synopsis

This demo application displays the results in a tabular format showing the date of the question, its title and who posted it.
If the question has been answered, a visual style is different from unanswered questions.
The displayed item have a means to navigate to the original question on Stack Exchange.

## Technologies stack

This application includes the following frameworks and libraries:

**Java**, **Spring**, **Spring Cache**, **Apache Jersey**, **Jackson**, **Apache Wicket**.

## Installation

The application can be compiled and built by Maven tool.

**$ mvn install**

As a result of it the war file will be created. This file can be deployed to an application container/server.

## API Reference

The application uses the Stackoverflow API:

[Search action](http://api.stackexchange.com/docs/search#order=desc&sort=activity&intitle=java&filter=default&site=stackoverflow&run=true)

## Examples

Here is an example of the Home Page:

![alt text](https://github.com/maxmalakhov/demo-wicket/raw/master/home-page.png "Home Page")

## Tests

There are two kind of tests such as Unit tests and Integration tests.
Tests can be run with the Maven goal - **test**

## Contributors

TBD

## License

TBD