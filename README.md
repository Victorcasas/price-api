# PRICE-API

## Description of price-api 

Spring Boot 3 and Java 17 application, built trying to follow the s.o.l.i.d. and clean design principles inspired by the hexagonal and inspired by hexafonal architecture style, that implements a REST service to get the priority price of a product.

The Domain layer descibes the entities Brand, Product and Price and the PriceRepository, wich describes a method to find entities which are used within an Application PriceUseCase.

In the Apllication layer PriceRepository use the parameters brand and product to find all the prices for a certain brand-product combination. And PriceUseCase use the parameters brand, product and date, returning the right entity Price (the priority price) for that brand-product combination in that date. This application usecase take all the entities that are delivered by the domain repository (filtered by brand and product) and make the necessary logic to find and to return the domain entity with highest priority attribute. This logic (priority discrimination) seems a business domain requirement and it had been implemented in the application layer following the proposed architecture (although the database itself could easily make this logic too).

The Infraestructure layer extends the repository using Spring Data JPA with the ORM Hibernate conecting to an a H2 database (PriceJpaRepository). At the other side of the hexagon, the usecase cis extended creating a PriceService that transfers the will deliver the found domain entity to an Spring REST controller (PriceController). Previously, the PriceService transforms the entity in a convenient presentation object as required (hiding its priority field, but showing all the rest fields of price entity).

The adapted JPA repository retrieve from the database the entities matching with the search criteries and the corresponding adpated service adpated returns to the controller the entity with highest priority across the results are found, according to the required application logic.

It have been considerd unnecesary the use a dto objet, since the JpaRepository let us to make directly the mapping between the jpa entity and the domain entity .


## Starting the application

After cloning the repository run the following command in Linux:

```
cd price-api
./mvnw spring-boot:run
```

In Windows run this command:

```
cd price-api
./mvn.cmd spring-boot:run
```
Alternatively, to use your own local maven installation run the following command in any operating system:

```
cd price-api
mvn spring-boot:run
```

## Operating

When the application is started, it populates the database with the appropriate script, starts the server and expose the rest service on http port 8080 of localhost, allowing to make requests to it using the url template:
* price-api/{brandId}/{productId}?date={date}

For example, it's possible to make requests to these urls (using postman or swagger-ui), corresponding to the proposed five sample use cases:

+ http://localhost:8080/price-api/1/35455?date=2020-06-14T10:00:00
+ http://localhost:8080/price-api/1/35455?date=2020-06-14T16:00:00
+ http://localhost:8080/price-api/1/35455?date=2020-06-14T21:00:00
+ http://localhost:8080/price-api/1/35455?date=2020-06-15T10:00:00
+ http://localhost:8080/price-api/1/35455?date=2020-06-16T21:00:00

To use swagger-ui navigate to http://localhost:8080/swagger-ui/index.html and execute the call filling the properties fields. To use postman import the file containing the postman collection located at the root of the project and try the samples.


## Testing
The use case, repository and contoller unit tests are implemented to prove the correct isolated working of them using the needed mocked data. And the integration test use all the infraestructure with the real database, populated with the provided sample data, in order to tests automatically the provided five sample use cases against the H2 database, thus proving the correct application operation. Tests coverage is available through the Jacoco maven plugin. Additionally it's possible tu run mutation tests through pitest maven plugin.

Run the tests with the command:

```
cd price-api
mvn test
```
## Docker
Since an in-memory database is used, the possibility of using modern technologies such as trestcontainer for integration testing has not been considered. On the other hand, a Dockerfile is included at the root of the project, allowing to dockerize the apllication. 

## Final considerations
An improvement would be to avoid searching for the priority price in a prices list and, instead, use an specific query in the infrastructure layer so that the database performs the search and returns the priority price directly. This would be more efficient and scalable, especially if you have a large amount of data in your database. We possibly might consider enhancing the efficiency of price searching by modifying the infrastructure layer to perform such a more specific query.

But, intended to be inspired by the hexagonal architecture style, it has been choosed to consider the required priority logic as an sctrict business logic requirement and to keep it at the domain layer.
