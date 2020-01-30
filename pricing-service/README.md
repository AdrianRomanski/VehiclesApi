# Pricing Service

- The Pricing Service is a REST WebService that simulates a backend that
would store and retrieve the price of a vehicle given a vehicle id as
input.
- REST WebService integrated with Spring Boot

## Features
### Genereting 20 random prices in 10 most popular currencies
### First the price is comming in US Dollar then is exchanged to other currencies
- US Dollar, exchangeRate 1.0
- Euro, exchangeRate 0.91 
- Japanese Yen, exchangeRate 108.99
- Pound, exchangeRate 0.78
- Australian Dollar, exchangeRate 1.48
- Canadian Dollar, exchangeRate 1.33
- Swiss Franc, exchangeRate 1.0
- Chinese Renmindbni, exchangeRate 7.04
- Swedish Krona, exchangeRate 9.64
- New Zealand Dollar, exchangeRate 1.56

### Eureka Netflix 

 - [Eureka Server] http://localhost:8761/
 - [Pricing Service] http://localhost:8082/
 - [Random Prices] http://localhost:8082/prices
