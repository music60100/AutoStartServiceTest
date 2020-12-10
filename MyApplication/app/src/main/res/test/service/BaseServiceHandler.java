package com.example.myapplication.service;

/**
 * The base class for all service handlers that react to specific services.
 */
public class BaseServiceHandler {

    // region Constants

    // The name of the service action used in the subclass
    protected final String SERVICE_ACTION;

    // endregion

    // region Data Members

    // The handler used to react.
    protected ServiceHandler _service;

    // endregion

    // region Constructors

    public BaseServiceHandler(String serviceActionName) {
        SERVICE_ACTION = serviceActionName;
    }

    // endregion


}