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
import junit.framework.TestSuite;
import junit.framework.Test;

import org.apache.log4j.helpers.AbsoluteTimeDateFormat;
import org.apache.log4j.util.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 A superficial but general test of log4j.
 */
public class MyMsgTestCase extends TestCase {

    //private static Logger logger = Logger.getLogger(MyMinTestCase.class);

    public String asciiGenerator(int len, int lo, int hi) {
        StringBuffer s = new StringBuffer();
        for(int i = 0; i < len; i++) {
            int rNum = ((int) (Math.random()*(hi - lo))) + lo;
            s.append((char) rNum);
        }
        return s.toString();
    }

    public void testMsgWithEmptyChar() throws IOException {
        Logger logger = Logger.getLogger(MyMsgTestCase.class);
        Layout layout = new SimpleLayout();
        Appender appender = new FileAppender(layout, "output/partitioning/empty", false);
        logger.addAppender(appender);

        String msg = "";
        logger.log(Level.INFO, msg);


        FileReader fr = new FileReader("output/partitioning/empty");
        BufferedReader br = new BufferedReader(fr);
        String log = br.readLine();

        String buf = "INFO" + " - " + msg;
        assertEquals(buf, log);

    }

    public void testMsgWithPrintableChars() throws IOException {
        Logger logger = Logger.getLogger(MyMsgTestCase.class);
        Layout layout = new SimpleLayout();
        Appender appender = new FileAppender(layout, "output/partitioning/printable", false);
        logger.addAppender(appender);

        String msg = asciiGenerator(20, 32, 126);
        logger.log(Level.INFO, msg);


        FileReader fr = new FileReader("output/partitioning/printable");
        BufferedReader br = new BufferedReader(fr);
        String log = br.readLine();

        String buf = "INFO" + " - " + msg;
        assertEquals(buf, log);

    }

    public void testMsgWithNonPrintableChars() throws IOException {
        Logger logger = Logger.getLogger(MyMsgTestCase.class);
        Layout layout = new SimpleLayout();
        Appender appender = new FileAppender(layout, "output/partitioning/nonprintable", false);
        logger.addAppender(appender);

        String msg = asciiGenerator(20, 0, 31);
        logger.log(Level.INFO, msg);


        FileReader fr = new FileReader("output/partitioning/nonprintable");
        BufferedReader br = new BufferedReader(fr);
        String log = br.readLine();

        String buf = "INFO" + " - " + msg;
        assertEquals(buf, log);

    }

}
