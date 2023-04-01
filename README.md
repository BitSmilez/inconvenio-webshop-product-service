# Product Microservice

This is the Product Microservice, responsible for managing product information and handling cart-related events. It provides a RESTful API to interact with the product catalog and integrates with a message queue for cart events.
Features:
    Retrieve all products
    Retrieve a product by its ID
    Create a new product
    Update an existing product
    Delete a product by its ID
    Find products based on keywords
    Filter products by category
    Filter products on sale
    Manage product catalog
    Publish events for add-to-cart, remove-from-cart, and update-cart
    Message queue integration

Requirements

    Java 11+
    Spring Boot
    Docker

Installation

Clone the repository:



    git clone https://github.com/your-username/product-microservice.git




## Usage
Navigate to the project directory:

To start the Product Microservice using Docker, run the following command:


    docker-compose up

This will start the microservice and any required infrastructure, such as a message queue, in separate containers. The API will be accessible on http://localhost:8089.
API Endpoints
    GET /products: Retrieve all products
    GET /product/{id}: Retrieve a product by its ID
    POST /product: Create a new product
    PUT /product/{id}: Update an existing product
    DELETE /product/{id}: Delete a product by its ID
    GET /product/find?keyWord={keyWord}: Find products based on keywords
    GET /product/category/{category}: Filter products by category
    GET /products/sale: Filter products on sale

    POST /cart/add-to-cart: Publish an add-to-cart event
    POST /cart/remove-from-cart: Publish a remove-from-cart event
    POST /cart/update-cart: Publish an update-cart event

## Copyright
In this project, we've incorporated a variety of fascinating and thought-provoking images, all of which have been sourced from the talented Katerina Kamprani. These images showcase a range of quirky and unconventional designs, bringing an element of intrigue and whimsy to our work. We'd like to extend our gratitude to Katerina Kamprani for her exceptional artistry . All images can can be found at the following links:

The best boots you ever saw: https://www.theuncomfortable.com/wp-content/uploads/2017/04/20_boots-655x655.jpg 

Mugs: https://www.theuncomfortable.com/wp-content/uploads/2017/05/mug_01_p-768x520.jpg

Wineglass: https://www.theuncomfortable.com/wp-content/uploads/2017/04/19_wineglass.jpg

Champagne glass: https://www.theuncomfortable.com/wp-content/uploads/2017/05/champagne_01_p-1116x1489.jpg

fork: https://www.theuncomfortable.com/wp-content/uploads/2017/05/chain_fork_01_p-1024x684.jpg

Watercan:https://www.theuncomfortable.com/wp-content/uploads/2017/04/18_watering_can.jpg

Ruler: https://www.theuncomfortable.com/wp-content/uploads/2018/10/Ruler-768x768.jpg 

Teapot: https://ucarecdn.com/bce086de-bbc4-4dfb-92b1-a7d399167aea/

Spoon: https://www.theuncomfortable.com/wp-content/uploads/2017/04/14_spoon.jpg

glasses: https://www.theuncomfortable.com/wp-content/uploads/2018/01/Uncomfortable-glasses-02-768x768.jpg





