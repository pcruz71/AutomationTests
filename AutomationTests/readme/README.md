# AutomationTests - Java, TestNG, Maven, Selenium 

Clone the repo from GitHub: 

	git clone https://github.com/pcruz71/AutomationTests
	
Test configuration:

	src/test/resources/testng.xml
	
![Alt text](readme/testng.png?raw=true)

Failed tests will have  a screenshot taken and stored in AutomationTests/test-output/screenshots after refreshing the project

![Alt text](readme/testFailedPic.png?raw=true)

From the cmd line, cd into the AutomationTests dir and run:

	mvn clean install test

When the tests are complete, refresh the AutomationTests dir to view the results under AutomationTests/parallel tests.html

![Alt text](readme/testReport.png?raw=true)

