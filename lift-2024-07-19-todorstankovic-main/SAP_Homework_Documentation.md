# Application for processing bank loans
## Todor Stanković

The Application for processing bank loans is designed to handle loan requests and manage the steps involved in processing these loans.The system handles 4 separete but connected databases, LoanType and Step, which are a blueprint for creating LoanRequests and storing their RequestSteps, which are the other two databases. This system ensures that each loan request can have its own independent set of steps, which can be updated without affecting the original loan type data, while also strictly following rules which can be modified.
As mentioned to Nevena Brajović, I am using PostgreSQL databases, as I had problems connecting to SQLite.

## Project Structure Packages(com.fioneer.homework)

- LoanType (.loan)
- Step(.step)
- RequestStep(.requestStep)
- loanRequest(.loanRequest)

## Classes
Each package has a default class for setting up the database and fields in it. Each package also has a Controller class,Service class, Repository class, and Step and LoanType have a Config class.

 - Controller class handles all the APIs that come from the OpenAPI UI.
 - Service class handles all of the bussiness logic in its package.
 - Repository handles the CRUD operations and is filled with default function from the framework.
 - Config class is used to fill up the databases with dummy data.
 

## Foreign Key and Tables

I used foreign key features provided by the PostgreSQL to link my databases. How I link the LoanType to Step is by assigning the LoanTypeID to the Step, and then the pair is linked.
LoanType and Step tables dont allow for duplicates, because they are blueprint tables, but LoanRequest and RequestStep allow for duplicates and changes made to data in Request tables dont affect the blueprint tables.




## Conclussion

This documentation provides an overview of the Application for processing bank loans, and give you an small insight into the works of the app. For further details please refer to the source code and comments within the project.

**Thank you for you consideration,
Todor Stankovic**