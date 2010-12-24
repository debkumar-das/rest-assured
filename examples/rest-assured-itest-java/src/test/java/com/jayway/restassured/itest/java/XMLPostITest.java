/*
 * Copyright 2010 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.jayway.restassured.itest.java;

import com.jayway.restassured.itest.support.WithJetty;
import org.junit.Test;

import static com.jayway.restassured.RestAssured.expect;
import static com.jayway.restassured.RestAssured.with;
import static org.hamcrest.Matchers.*;

public class XMLPostITest extends WithJetty {

    @Test
    public void xmlParameterSupport() throws Exception {
        with().parameters("firstName", "John", "lastName", "Doe").expect().body("greeting.firstName", equalTo("John")).when().post("/greetXML");
    }

    @Test
    public void xmlParameterSupportWithAnotherAssertion() throws Exception {
        with().parameters("firstName", "John", "lastName", "Doe").expect().body("greeting.lastName", equalTo("Doe")).post("/greetXML");
    }

    @Test
    public void xmlWithLists() throws Exception {
        with().parameters("firstName", "John", "lastName", "Doe").expect().body("greeting", hasItems("John", "Doe")).post("/greetXML");
    }

    @Test
    public void postWithXPath() throws Exception {
        expect().body(hasXPath("/greeting/name/firstName[text()='John']")).with().parameters("firstName", "John", "lastName", "Doe").post("/anotherGreetXML");
    }

    @Test
    public void postWithXPathContainingHamcrestMatcher() throws Exception {
        expect().body(hasXPath("/greeting/name/firstName", containsString("Jo"))).with().parameters("firstName", "John", "lastName", "Doe").post("/anotherGreetXML");
    }
}