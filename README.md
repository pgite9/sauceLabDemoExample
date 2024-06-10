
# SauceDemo Automation Framework

This repository contains an automation framework for testing the SauceDemo website using Selenium, TestNG, and Maven.

## Introduction

The SauceDemo website Automation 

## Features

- Page Factory design pattern for better code organization and maintainability.
- TestNG for test execution and reporting, allowing for easy parallel test execution and detailed test reports.
- Maven for project management and dependency resolution, simplifying project setup and maintenance.
- Integration with popular reporting tools like Extent Reports for comprehensive test reporting.
- Dynamic product selection to enhance test coverage and flexibility. ( Future scope )


## Prerequisites

Before running the tests, ensure you have the following installed:

- Java Development Kit (JDK) 1.8 or higher
- Maven
- Chrome Browser (or any other browser supported by Selenium)

## How to Run

Follow these steps to run the automated tests:

Step 1 : Clone to code
Step 2 : Update the test data into json file if needed ( currently it uses the same test data provided on saucelabdemo website)
Step 3 : Resolve dependancies and execute command mvn test

## Test Reports
After the tests execution new file 'extent-report.html' is created in folder , open that file into chrome and we can see the execution summary report.


#### Contributor
Paresh Gite 




