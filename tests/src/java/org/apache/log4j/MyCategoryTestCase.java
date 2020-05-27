/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.log4j;

import junit.framework.TestCase;
import org.apache.log4j.helpers.LogLog;

import java.io.*;


/**
 A superficial but general test of log4j.
 */
public class MyCategoryTestCase extends TestCase {

    public void testRemoveAll() {
        Category cat = Category.getInstance(MyCategoryTestCase.class);
        cat.addAppender(new ConsoleAppender());
        cat.removeAllAppenders();
        assertFalse(cat.getAllAppenders().hasMoreElements());
    }

    public void testMyFireRemoveAppenderEvent() {
        Category cat = Category.getInstance(MyCategoryTestCase.class);
        Appender app = new ConsoleAppender();
        app.setName("app");
        cat.addAppender(app);

        Appender aaiApp = cat.aai.getAppender("app");
        cat.aai.removeAppender("app");
        cat.myFireRemoveAppenderEvent(aaiApp);

        assertFalse(cat.getAllAppenders().hasMoreElements());
    }
}

