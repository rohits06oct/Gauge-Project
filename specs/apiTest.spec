# Validate GET Post API

## Validate fetching a get call
* Send GET request to endpoint "https://jsonplaceholder.typicode.com/posts/1"
* Verify the response status code is "200"
* Verify the user ID is "1"
* Verify the title is "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"

## Validate wrong status code call
* Send GET request to endpoint "https://jsonplaceholder.typicode.com/posts/1"
* Verify the response status code is "400"
* Verify the user ID is "1"
* Verify the title is "sunt aut facere repellat provident occaecati excepturi optio reprehenderit"
