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
public class MyFileAppenderTestCase extends TestCase {

    private static String path = "output/CoverageTest/fileAppender";

    public void testConstructor() throws IOException {
        Appender app = new FileAppender(new SimpleLayout(), path, false, false, 0);
        String appName = MyConsoleAppenderTestCase.class.getPackage().getName() + ".FileAppender";

//        System.out.println(appName);
//        System.out.println(app.getClass().getName());
        assertEquals(appName, app.getClass().getName());
    }

    public void testGetFile() throws IOException {
        FileAppender app = new FileAppender(new SimpleLayout(), path);
        assertEquals(path, app.getFile());
    }

    public void testGetAppend() throws IOException {
        FileAppender app = new FileAppender(new SimpleLayout(), path);
        assertTrue(app.getAppend());

    }

    public void testSetAppend() throws IOException {
        FileAppender app = new FileAppender(new SimpleLayout(), path);
        app.setAppend(false);
        assertFalse(app.fileAppend);
    }

    public void testGetBufferIO() throws IOException {
        FileAppender app = new FileAppender(new SimpleLayout(), path, false, true, 1024);
        assertTrue(app.getBufferedIO());
    }

    public void testSetBufferIO() {
        FileAppender app = new FileAppender();
        app.setBufferedIO(true);
        assertTrue(app.getBufferedIO());
    }

    public void testGetBufferSize() throws IOException {
        FileAppender app = new FileAppender(new SimpleLayout(), path, false, true, 1024);
        assertEquals(1024, app.getBufferSize());
    }

    public void testSetBufferSize() {
        FileAppender app = new FileAppender();
        app.setBufferedIO(true);
        app.setBufferSize(1024);
        assertEquals(1024, app.getBufferSize());
    }

}

