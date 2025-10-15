/**
 * Copyright 2013-2016 BlackLocus
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
package com.blacklocus.metrics;

import software.amazon.awssdk.services.cloudwatch.model.Dimension;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @author Jason Dunkelberger (dirkraft)
 */
public class MetricNameBuilderTest {

    @Test
    public void test() {
        MetricNameBuilder builder = new MetricNameBuilder();
        builder.addNameToken("Token");
        builder.addDimension("key", "value", true);
        assertEquals("Token key=value*", builder.toString());

        builder.add("thing=stuff abc def*");
        assertEquals("Token abc def* key=value* thing=stuff", builder.toString());

        builder.addNameToken("herring", true);
        builder.addNameToken("option*");
        builder.addDimension(Dimension.builder().name("color").value("red").build());
        assertEquals("Token abc def* herring* option* key=value* thing=stuff color=red", builder.toString());
    }
}
