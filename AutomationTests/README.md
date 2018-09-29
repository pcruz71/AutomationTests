# AutomationTests - Java, TestNG, Maven, Selenium 

Clone the repo from GitHub: 

	git clone https://github.com/pcruz71/AutomationTests
	
Test configuration:

	src/test/resources/testng.xml
	
![Alt text](https://github.com/pcruz71/AutomationTests/blob/master/AutomationTests/readme/testng.png?raw=true)

Failed tests will have  a screenshot taken and stored in AutomationTests/test-output/screenshots after refreshing the project

![Alt text](https://github.com/pcruz71/AutomationTests/blob/master/AutomationTests/readme/testFailedPic.png?raw=true)

From the cmd line, cd into the AutomationTests dir and enter the following command to run the tests:

	mvn clean install test

When the tests are complete, refresh the AutomationTests dir to view the results in AutomationTests/target/surefire-reports/AutomationTests/parallel tests.html

![Alt text](https://github.com/pcruz71/AutomationTests/blob/master/AutomationTests/readme/testReport.png?raw=true)

