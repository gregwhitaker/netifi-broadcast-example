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
package example.service;

import com.google.protobuf.Empty;
import example.service.protobuf.ExampleService;
import example.service.protobuf.MessageRequest;
import io.netty.buffer.ByteBuf;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Mono;

/**
 * Service that receives messages and prints them to the console.
 */
@SpringBootApplication
public class ExampleServiceImpl implements ExampleService {
    private static final Logger LOG = LoggerFactory.getLogger(ExampleServiceImpl.class);

    @Override
    public Mono<Empty> printMessage(MessageRequest message, ByteBuf metadata) {
        return Mono.defer(() -> {
            LOG.info("Received Broadcast Message: {}", message.getMessage());
            return Mono.empty();
        });
    }
}
