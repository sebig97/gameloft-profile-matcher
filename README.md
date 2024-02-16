# Profile Matcher

## Overview

The Profile Matcher project is a simple Spring service designed to serve as a user profile matcher. It is tasked with extracting a player's profile based on a unique client ID and updating the profile based on current running campaigns.

## Microservice Architecture and Patterns Used

The project incorporates various microservice-based tools and patterns:

- **API Gateway**: Provides a single entry point for clients to access various services.
- **Service Registry with Eureka Server**: Registers and manages service instances, enabling service discovery and load balancing.
- **Config Server**: Manages externalized configuration for applications across all environments.
- **Circuit Breaker with Resilience4j**: Implements circuit breaker pattern to handle and prevent failures.
- **Distributed Tracing with Zipkin**: Provides visibility into the flow of requests across microservices.
- **Micrometer**: A metrics collection facade for gathering application metrics.
- **OpenFeign**: Declarative HTTP client for making RESTful API calls. It simplifies communication between microservices by allowing you to write interface-based API clients.

## Components

The project consists of the following components for each service:

- **Player Profile Service**:
  - Controller: Handles incoming HTTP requests and invokes service methods.
  - Service: Contains business logic and orchestrates data retrieval and manipulation.
  - Repository: Manages interactions with the database.
  - DTO (Data Transfer Object): Defines objects exchanged between service layers and external clients.
  - Entity: Represents data stored in the database.
  - Exception Handler Class: Handles exceptions thrown within the service and returns appropriate responses.
  - Annotations: Includes various stereotypes such as `@Service`, `@Controller`, `@Repository`, etc.

- **Campaign Service**: (Similar structure as Player Profile Service)

## Usage

To use the Profile Matcher service:

1. Start the Eureka Server to register service instances.
2. Start the Config Server to manage configurations.
3. Deploy the Player Profile Service and Campaign Service instances.
4. Access the API Gateway to interact with the services.
5. Utilize the `/get_client_config/{player_id}` endpoint to retrieve and update player profiles.

