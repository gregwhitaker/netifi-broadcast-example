# netifi-broadcast-example
An example showing how to broadcast a message to multiple services using [Netifi](https://www.netifi.io) and [RSocket](http://rsocket.io).

## What is Netifi?
[Netifi](https://www.netifi.com) is a platform for building cloud-native applications with the power of [RSocket](http://rsocket.io).

Netifi provides service discovery, load-balancing, streaming, and back-pressure without deploying a whole host of infrastructure 
components and without polluting your code with circuit breakers and client-side load-balancing. 

Check out [www.netifi.com](https://www.netifi.com) for more information.

## Project Structure
This example contains the following projects / directories:

- [client](client) - Client that sends broadcast messages to a group.
- [service](service) - Service that is listening for messages.
- [service-idl](service-idl) - Defines the API contract for the example `service`.

## Building the Example
Run the following command to build the example:

    ./gradlew clean buildImage

## Running the Example
Follow the steps below to run the example:

1. Run the following commands to start a Netifi Broker and several instances of the example service:

        cd docker
        docker-compose up
        
2. In a new terminal, run the following command to start the broadcast client:

        docker run -e SPRING_PROFILES_ACTIVE='localdocker' netifi-broadcast-example/client
        
    If successful, you will see the broadcast messages being sent in the console:
    
        Sending: This is broadcast test message 1 
        Sending: This is broadcast test message 2 
        Sending: This is broadcast test message 3 
        Sending: This is broadcast test message 4
        
3. Switch back to the terminal where you launched the Netifi Broker and the Service instances:

    If broadcast messages are being successfully sent you will see them arriving at each instance in the logs:

        example-service2    | 2019-11-24 05:24:09.087  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 1
        example-service1    | 2019-11-24 05:24:09.085  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 1
        example-service3    | 2019-11-24 05:24:09.087  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 1
        example-service2    | 2019-11-24 05:24:10.065  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 2
        example-service3    | 2019-11-24 05:24:10.073  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 2
        example-service1    | 2019-11-24 05:24:10.073  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 2
        example-service1    | 2019-11-24 05:24:11.101  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 3
        example-service2    | 2019-11-24 05:24:11.102  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 3
        example-service3    | 2019-11-24 05:24:11.104  INFO 1 --- [tor-tcp-epoll-4] example.service.ExampleServiceImpl       : Received Broadcast Message: This is broadcast test message 3

## Bugs and Feedback
For bugs, questions, and discussions please use the [Github Issues](https://github.com/gregwhitaker/netifi-broadcast-example/issues).

## License
Copyright 2019 Greg Whitaker

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
