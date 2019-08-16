# Test Framework Comparison
A simple Project to compare different testing frameworks in matter of execution time. 

## Setup
The application module hosts a very simplified version of an JavaEE application, consisting of an stateless EJB service 
accessing a DAO and a sample Entity via CDI.

The goal trying to achieve is to test the service as efficient as possible. Therefore there are three modules, each of 
them containing the same test methods but using different approaches.

1. JUnit + Mockito: Basic test setup with manual crafted service and all dependent classes mocked using mockito
2. JUnit + Weld + Eclipselink + DbUnit: Test setup using a complete CDI and JPA implementation, an inmemory database and DbUnit to load datasets
3. Junit + Arquillian + OpenLiberty-Managed: Test run using Arquillian and an OpenLiberty Server with full Java EE 8 specs 

## Stats

As gradle uses incremental builds and build caches you cannot simple run `gradle test` multiple times as the tests are 
only executed on the first run. For that reason you have to call `gradle cleanTests test` or `gradle test --rerun-tasks` 
to force the execution of the tests. The latter command was used to measure the execution time of the tests. Since the 
project only has one test class, it was duplicated n times to see how the execution time is scaling when there are 20 or 100 test classes.

Nr of TestClasses | Mockito | Weld + Eclipselink | Arquillian
---|---|---|---
1 | ~600ms | 1s | 17s
10 | <1s | 2s | 48s
20 | <1s | 2s | 69s
100 | 1s | 5s | 304s
500 | 2s | 13s | (1)
1000 | 4s | 24s | (1)

(1): not measured

All values were measured on an AMD Ryzen 7 2700X running a Linux 5.2.8 Kernel and OpenJDK 1.8.0_222 
