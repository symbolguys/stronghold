/*
 * Copyright (c) 2022 by Fred George
 * @author Fred George  fredgeorge@acm.org
 * Licensed under the MIT License; see LICENSE file in root.
 */

package com.nrkei.training.microservices.unit

import com.nrkei.training.microservices.river.Status
import com.nrkei.training.microservices.river.Status.PacketProblemsException
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.fail

// Ensures that Status operates correctly
internal class StatusTest {

    companion object {
        private const val VALID_JSON = "{\"key1\":\"value1\"}"
    }

    private lateinit var problems: Status

    @BeforeEach
    fun setUp() {
        problems = Status(VALID_JSON)
    }

    @Test
    fun noProblemsFoundDefault() {
        assertFalse(problems.hasErrors())
    }

    @Test
    fun errorsDetected() {
        problems.error("Simple error")
        assertTrue(problems.hasErrors())
        assertTrue("Simple error" in problems.toString())
    }

    @Test
    fun severeErrorsDetected() {
        try {
            problems.severeError("Severe error")
            fail("PacketProblemsException expected")
        } catch (e: PacketProblemsException) {
            assertTrue(problems.hasErrors())
            assertTrue("Severe error" in problems.toString())
        } catch (e: Exception) {
            fail("PacketProblemsException expected, but ")
        }
    }

    @Test
    fun warningsDetected() {
        problems.warning("Warning explanation")
        assertFalse(problems.hasErrors())
        assertTrue("Warning explanation" in problems.toString())
    }

    @Test
    fun informationalMessagesDetected() {
        problems.informationOnly("Information only message")
        assertFalse(problems.hasErrors())
        assertTrue("Information only message" in problems.toString())
    }
}