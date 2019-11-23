/**
 * Copyright 2019 Greg Whitaker
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package example.client;

import com.netifi.spring.core.annotation.Broadcast;
import example.service.protobuf.ExampleServiceClient;
import example.service.protobuf.MessageRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@SpringBootApplication
public class ClientApplication {
    private static final Logger LOG = LoggerFactory.getLogger(ClientApplication.class);

    public static void main(String... args) {
        SpringApplication.run(ClientApplication.class, args);
    }

    @Component
    public class Runner implements CommandLineRunner {

        @Broadcast("example.service")
        private ExampleServiceClient exampleServiceClient;

        @Override
        public void run(String... args) throws Exception {
            MessageRequest request = MessageRequest.newBuilder()
                    .setMessage(getMessageFromArgs(args))
                    .build();

            LOG.info("Broadcasting: {}", request.getMessage());

            // Broadcasting the message to the group
            exampleServiceClient.printMessage(request).block();
        }

        /**
         * Gets the message to broadcast from the command line arguments.
         *
         * @param args command line arguments
         * @return message to broadcast
         */
        public String getMessageFromArgs(String... args) {
            if (args.length != 1) {
                throw new IllegalArgumentException("Invalid number of arguments");
            }

            if (StringUtils.isEmpty(args[0])) {
                throw new IllegalArgumentException("Message argument cannot be null or empty string");
            }

            return args[0];
        }
    }
}
