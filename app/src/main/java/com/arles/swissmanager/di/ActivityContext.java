package com.arles.swissmanager.di;

import java.lang.annotation.Retention;

import javax.inject.Qualifier;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Annotation created to improve Context injection. This annotation is used with @Inject for
 * Context class to return the current Activity context.
 *
 * This annotation can be replaced with a @Named annotation, but configure before the
 * ActivityModule.
 *
 * Created by Admin on 19.05.2015.
 */
@Qualifier
@Retention(RUNTIME)
public @interface ActivityContext {
}
