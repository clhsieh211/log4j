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
public class MyFSMTestCase extends TestCase {

    public void testInit2LoggerCreated() {
        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        assertEquals(logger.getName(), MyFSMTestCase.class.getName());
    }

    public void testLoggerCreated2AppenderAttached() {
        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);
        String appenderName = "appName";
        Appender app = new ConsoleAppender();
        app.setName(appenderName);
        logger.addAppender(app);

        assertEquals(appenderName, ((Appender)logger.getAllAppenders().nextElement()).getName());
    }

    public void testAppenderAttached2LayoutConfigured() {
        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);
        logger.addAppender(new ConsoleAppender(new SimpleLayout()));

        assertNotNull(((Appender) logger.getAllAppenders().nextElement()).getLayout());
    }

    public void testLogWithValidLevel() throws IOException {
        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);

        String path = "output/FSMTest/logWithValidLevel";
        String msg = "This message should always be logged.";

        logger.addAppender(new FileAppender(new SimpleLayout(), path, false));
        logger.log(Level.OFF, msg);

        String buf = (new BufferedReader(new FileReader(path))).readLine();
        String log = "OFF - " + msg;

        assertEquals(log, buf);
    }

    public void testLogWithInvalidLevel() throws IOException {
        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);

        String path = "output/FSMTest/logWithInValidLevel";
        String msg = "This message should always not be logged.";

        logger.addAppender(new FileAppender(new SimpleLayout(), path, false));
        logger.log(Level.ALL, msg);

        String buf = (new BufferedReader(new FileReader(path))).readLine();
        String log = null;

        assertEquals(log, buf);
    }

    public void testLogWhenAppenderIsNotAttached() throws IOException {
        String errMsgPath = "output/FSMTest/logWhenAppenderIsNotAttached";
        FileOutputStream ferr = new FileOutputStream(errMsgPath, false);
        PrintStream err = new PrintStream(ferr);
        System.setErr(err);

        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);
        logger.log(Level.OFF, "There is no appender attached in this logger.");

        String errMsg = "log4j:WARN No appenders could be found for logger (" + logger.getName() + ").";
        String buf = (new BufferedReader(new FileReader(errMsgPath))).readLine();

        assertEquals(errMsg, buf);
    }

    public void testLogWhenLayoutIsNotConfigured() throws IOException {
        String errMsgPath = "output/FSMTest/logWhenLayoutIsNotConfigured";
        FileOutputStream ferr = new FileOutputStream(errMsgPath, false);
        PrintStream err = new PrintStream(ferr);
        System.setErr(err);

        Logger logger = Logger.getLogger(MyFSMTestCase.class);
        logger.removeAllAppenders();
        logger.setAdditivity(false);
        logger.addAppender(new ConsoleAppender());
        logger.log(Level.OFF, "Layout is not configured in this logger.");

        String errMsg = "log4j:ERROR No output stream or file set for the appender named [null].";
        String buf = (new BufferedReader(new FileReader(errMsgPath))).readLine();

        assertEquals(errMsg, buf);
    }
}

