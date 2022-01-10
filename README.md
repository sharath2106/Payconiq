# Payconiq Automation task

In this project, the automation framework is created using RestAssured and Java. The tests can be executed locally and also in docker containers. 

The task automates the following scenarios in the application [https://restful-booker.herokuapp.com/apidoc/index.html#]- 

  - Get booking
    - Get all bookings
    - Get bookings by first name and last name
    - Get bookings by checkin date
    - Get bookings for a valid booking id
    - Get bookings for an invalid booking id

  - Update booking
    - Update booking for a valid booking id
    - Update booking for an invalid request body
    - Update booking for an invalid booking id (Disabled now as the API is not returning the proper response code)
    - Partial update of the booking
  - Delete booking
    - Delete booking for a booking id
    - Delete booking for an invalid booking id (Disabled now as the API is not returning the proper response code)
    
### Built with -

The automation framework used for the task uses the following languages/tools :

* [Java] - programming language used for the framework
* [RestAssured] - library used for performing http requests
* [Junit5] - testing framework used for execution of tests
* [Maven] - build tool to download and use dependencies
* [Docker] - open source containerization platform to run tests in containers
* [Github Actions] - CI/CD tool to run the test scripts which provides quicker feedback
* [Lombok] - used to generate Getters, Setters and Constructors with annotations
* [Spotless] - used to ensure proper code formatting applied across the project
* [Assertj] - used to provide readable assertions
* [Faker] - to generate fake form data


### Structure of the framework

![Screenshot 2022-01-10 at 11 07 28 AM](https://user-images.githubusercontent.com/25933070/148722388-fc29e635-24d1-4c66-b211-0d9438e5e168.png)
        
### How to run the tests?

`Pre-requisites` -
Set the following environment variables for the test to execute

```sh
export USER=<email>
export PASSWORD=<password>
```

Remember to change the `export` above to `SET` if you're running a Windows machine.

##### The tests can be executed in both local machines and docker containers.

###### To execute tests in local machine -

```sh
# Get into the directory
$ cd Payconiq-Automation
```

To run tests in LOCAL machine, execute the following command -
```
# To execute all tests in local machine
$ mvn spotless:apply clean test

    > 'mvn spotless:apply' is used to ensure proper code formatting is applied across the project and it fixes the same if it is not.

Alternatively, you can also run the following shell script 
$ sh run-tests.sh     
```

```
# To execute Get booking tests in local machine
$ mvn spotless:apply clean test -D groups=getBooking  
```

```
# To execute Update booking tests in local machine
$ mvn spotless:apply clean test -D groups=updateBooking    
```

```
# To execute Delete booking tests in local machine
$ mvn spotless:apply clean test -D groups=deleteBooking  
```

###### To execute tests in docker container -

Follow the steps in https://docs.docker.com/get-docker/ to install `Docker` in your machine.

The `Dockerfile` contains a shell script file which has the environment variables exported by default.

Execute the below commands -

```sh
# Build the docker container
$ docker build . -t e2e

$ docker run e2e

$ docker run -e TAG=<tags> e2e
We can run specific tests by passing the <tags> `getBooking` or `updateBooking` or `deleteBooking`
```
#### `Dockerfile` -

![Screenshot 2022-01-10 at 12 19 09 PM](https://user-images.githubusercontent.com/25933070/148727471-2de8a8ba-16ee-44b3-969e-9285df15c8c5.png)

#### `run-tests.sh` -

![Screenshot 2022-01-10 at 12 16 52 PM](https://user-images.githubusercontent.com/25933070/148727305-c99e5cfe-df34-4ea3-9261-f8e48fd22b8b.png)

### Checklist

- [x] Generating random values for insignificant data
- [x] Encapsulation layers like test data, logic on pages
- [x] Stability of the tests
- [x] CI/CD
- [x] Dockerized execution

### "Nice to have" that can be done


- *Integration of test scenario with test cases of any test management tool*

> We can integrate the test execution status with a test management tool like Zephyr using ZAPI.    