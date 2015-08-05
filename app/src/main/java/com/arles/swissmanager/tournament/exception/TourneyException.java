package com.arles.swissmanager.tournament.exception;

/**
 * Exception throw by the application when a tournament went in wrong way.
 *
 * Created by Admin on 26.06.2015.
 */
public class TourneyException extends Exception {

    public TourneyException() {
        super();
    }

    public TourneyException(final String message) {
        super(message);
    }

    public TourneyException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public TourneyException(final Throwable cause) {
        super(cause);
    }

}
