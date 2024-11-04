# A current Account Banking application

An API to be used for opening a new “current account” of already existing customers.

## Requirement

### Functional Requirements
    
- The API will expose an endpoint which accepts the user information (customerID, initialCredit).
- Once the endpoint is called, a new account will be opened connected to the user whose ID is customerID.
- Also, if initialCredit is not 0, a transaction will be sent to the new account.
- Another Endpoint will output the user information showing Name, Surname, balance, and transactions of the accounts.

## TL;DR - Build and Run
 
	 mvn clean install
	 
## Technical landscape.

My Goal of with this assignment was to keep it simple yet effective, bug-proof, user-friendly, maintainable and readable. To achieve this I have tried to use as many latest features as possible. I tried not to go beyond what is asked for as assignment (business requirement). To make the code and application more reliable, I have achieved 72% code coverage. I used SonarQube to scan the code smell and solved all major bugs and issues. I also avoided importing unused libraries to keep the jar light-weighted. 

## Code Quality Factors that are taken care:

- Total 72% code coverage by Junit
- Using SonarLint all "Major" issues are resolved. 	 
- No external library from untrusted source dependency is used.
- Code smell is as low as possible.


## SWAGGER URL:
	http://localhost:8080/swagger-ui/index.html
	
## Next Steps / Improvement areas:
##### 1. Java 23 can be used.
##### 2. Security can be upgraded to JWT token based.