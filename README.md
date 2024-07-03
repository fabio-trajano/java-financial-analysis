# Java Financial Analysis

## Overview
Java Financial Analysis is a Spring Boot application designed to provide financial data and metrics for stocks using [Financial Modeling Prep API](https://site.financialmodelingprep.com/).

## Prerequisites
To run this application, ensure you have the following installed:
- **Java 17** or higher
- **Maven 3.6** or higher

## Installation and Running
1. **Clone the Repository**
    ```sh
    git clone https://github.com/fabio-trajano/java-financial-analysis.git
    cd java-financial-analysis
    ```

2. **Build the Project**
    ```sh
    mvn clean install
    ```

3. **Run the Application**
    ```sh
    mvn spring-boot:run
    ```

The application will start and be available on the default port `8080`.

## API Endpoints

### Get Basic Stock Data
Retrieve basic information about a stock using its ticker symbol.
- **Endpoint:** `GET /api/stock/{ticker}`


### Get Stock Metrics
Fetch detailed financial metrics for a specific stock.
- **Endpoint:** `GET /api/stock/{ticker}/metrics`


### Get Full Stock Data
Obtain both basic information and detailed metrics.
- **Endpoint:** `GET /api/stock/{ticker}/full`


## Contact
For any questions or feedback, please reach at [fabiotrajano.eng@gmail.com](mailto:fabiotrajano.eng@gmail.com).

