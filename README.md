# WeatherAPITest

This is a simple web and rest-api based project where weather details from UI of www.ndtv.com are verified with web-service https://openweathermap.org/current results.

Tool Stack Used -
* Maven Project with added dependencies for Selenium + TestNG + Rest-Assured + Extent Report
* Chrome browser/driver version - 85.0.4183.121
* Java - 8
* Eclipse IDE

<B> Steps to inport and run project in Ecipse </B>
* Open eclipse
* Go to File -> Import and select Git -> Projects frm Git
* Select Clone URIand enter URL as ->
* Also provide your github credentials
* Follow rest of the steps in standard fashion
* Verify that project is created
* Run build
* No error should be visible in the project

<B> How to run </B>

<I>Assumption</I> : TestNG plug-in is already installed in Eclipse
* Go to location - \src\TestCases\TestCases\WeatherTests.java
* Click on Run button from menu bar

<B> Checking Report </B>

Report is stored at below location in project ->
<I>\WeatherTest\test-output\Report\test\ExtentReport.html</I>

<B> Enhancements that can be done </B>
* Integrate data from external source like Excel, xml, csv etc. for API and UI Tests
* Seamless integration of Extent report
* Add classes if required for further HTTP requests

<B> Known Issues </B>
* Hard coded city name used as Pune due to section above.
* API parameters, headers etc. are hard-coded. No external file integration yet.

<B> Extensions </B>

Please feel free to enhance it or ask question for anything.
<I>Email</I> - kumar.vinay888@gmail.com
