package com.OficinaDeSoftware.EmissorCertificadosBackend.annotations;

import feign.RetryableException;
import feign.Retryer;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface FeignRetryable {

    class AnnotationRetryer implements Retryer {

        private final Retryer delegate;

        public AnnotationRetryer( Retryer delegate ) {
            this.delegate = delegate;
        }

        @Override
        public void continueOrPropagate( RetryableException e ) {

            final boolean hasAnnotation =
                    e.request().requestTemplate().methodMetadata().method().getAnnotation(FeignRetryable.class) != null;

            if ( hasAnnotation ) {
                delegate.continueOrPropagate(e);
                return;
            }

            throw e;

        }

        @Override
        public Retryer clone() {
            return new AnnotationRetryer(delegate.clone());
        }
    }
}
