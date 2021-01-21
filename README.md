# interstellARR
Design Notes:

Having read the project I decided that its most imperative goal was to measure the ability of the candidate to create a solution from scratch, using best practice.

So I decided on a best case architecture, and I refactored components into the architecture.

So I followed a maven template, incorporating the rest service, using exposed interfaces and using a JPA repository pattern. I compiled my own POM file.

I used the repository pattern, to handle all data calls to and from the artefacts. I customised an excel import direct loader(see line 175/176)

My main concern was the custom algorithm, that calculated the nearest path between 2 nodes, using my own original method. I completed my function in less than 50 lines.

The algorithm function is found in com.discov.logical.CustomAlgorithm

Assumptions
The start node is always A
The end node can be any single node

Restful Web Sevice URLs
Port: 8084
CUSTOM:  http://localhost:8084/custom/A/G
STANDARD: http://localhost:8084/shortestpath/A/G

EXECUTION
In order to run the project you must change the following properties in the application.properties file:
1. Excel file path
2. Derby database connection string

By Davison Matanga
dmatangany@gmail.com
