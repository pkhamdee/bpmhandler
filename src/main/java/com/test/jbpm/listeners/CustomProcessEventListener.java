package com.test.jbpm.listeners;

import org.kie.api.event.process.ProcessCompletedEvent;
import org.kie.api.event.process.ProcessEventListener;
import org.kie.api.event.process.ProcessNodeLeftEvent;
import org.kie.api.event.process.ProcessNodeTriggeredEvent;
import org.kie.api.event.process.ProcessStartedEvent;
import org.kie.api.event.process.ProcessVariableChangedEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CustomProcessEventListener implements ProcessEventListener {

    private static final Logger logger = LoggerFactory.getLogger(CustomProcessEventListener.class);

    public void beforeProcessStarted(ProcessStartedEvent event) {
        logger.info("beforeProcessStarted");
    }

    public void afterProcessStarted(ProcessStartedEvent event) {
        logger.info("afterProcessStarted");
    }

    public void beforeProcessCompleted(ProcessCompletedEvent event) {
        logger.info("beforeProcessCompleted");
    }

    public void afterProcessCompleted(ProcessCompletedEvent event) {
        logger.info("afterProcessCompleted");
    }

    public void beforeNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.info("beforeNodeTriggered : " + event.getNodeInstance().getNodeName());
    }

    public void afterNodeTriggered(ProcessNodeTriggeredEvent event) {
        logger.info("afterNodeTriggered");
    }

    public void beforeNodeLeft(ProcessNodeLeftEvent event) {
        logger.info("beforeNodeLeft");
    }

    public void afterNodeLeft(ProcessNodeLeftEvent event) {
        logger.info("beforeNodeLeft");
    }

    public void beforeVariableChanged(ProcessVariableChangedEvent event) {
        logger.info("beforeVariableChanged");
    }

    public void afterVariableChanged(ProcessVariableChangedEvent event) {
        logger.info("afterVariableChanged");
    }

}