//   Copyright 2012,2013 Vaughn Vernon
//
//   Licensed under the Apache License, Version 2.0 (the "License");
//   you may not use this file except in compliance with the License.
//   You may obtain a copy of the License at
//
//       http://www.apache.org/licenses/LICENSE-2.0
//
//   Unless required by applicable law or agreed to in writing, software
//   distributed under the License is distributed on an "AS IS" BASIS,
//   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//   See the License for the specific language governing permissions and
//   limitations under the License.

package com.dddtraining.catalog.domain.model.product.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DomainEventPublisher {

    private static final ThreadLocal<DomainEventPublisher> instance = new ThreadLocal<DomainEventPublisher>() {
        protected DomainEventPublisher initialValue() {
            return new DomainEventPublisher();
        }
    };

    private boolean publishing;

    @SuppressWarnings("rawtypes")
    private List subscribers;

    public static DomainEventPublisher instance() {
        return instance.get();
    }

    public <T> void publish(final T aDomainEvent) {

        if (!this.isPublishing() && this.hasSubscribers()) {

           // System.out.println("\n\nListe des subscribers avant enregistrement: " + subscribers);

            try {
                this.setPublishing(true);

                Class<?> eventType = aDomainEvent.getClass();

                @SuppressWarnings("unchecked")
                List<DomainEventSubscriber<T>> allSubscribers = this.subscribers();

                for (DomainEventSubscriber<T> subscriber : allSubscribers) {
                    Class<?> subscribedToType = subscriber.subscribedToEventType();

                    if (eventType == subscribedToType || subscribedToType == DomainEvent.class) {
                        subscriber.handleEvent(aDomainEvent);
                    }
                }

            } finally {
                this.setPublishing(false);
                //System.out.println("\n\nListe des subscribers apres enregistrement: " + subscribers);
            }
        }
    }

    public void publishAll(Collection<DomainEvent> aDomainEvents) {
        for (DomainEvent domainEvent : aDomainEvents) {
            this.publish(domainEvent);
        }
    }

    public void reset() {
        if (!this.isPublishing()) {
            this.setSubscribers(null);
        }
    }

    @SuppressWarnings("unchecked")
    public <T> void subscribe(DomainEventSubscriber<T> aSubscriber) {
        if (!this.isPublishing()) {
            this.ensureSubscribersList();

            this.subscribers().add(aSubscriber);
        }
    }

    private DomainEventPublisher() {
        super();

        this.setPublishing(false);
        this.ensureSubscribersList();
    }

    @SuppressWarnings("rawtypes")
    private void ensureSubscribersList() {
        if (!this.hasSubscribers()) {
            this.setSubscribers(new ArrayList());
        }
    }

    private boolean isPublishing() {
        return this.publishing;
    }

    private void setPublishing(boolean aFlag) {
        this.publishing = aFlag;
    }

    private boolean hasSubscribers() {
        return this.subscribers() != null;
    }

    @SuppressWarnings("rawtypes")
    private List subscribers() {
        return this.subscribers;
    }

    @SuppressWarnings("rawtypes")
    private void setSubscribers(List aSubscriberList) {
        this.subscribers = aSubscriberList;
    }

    public <T> void unSubscribe(final Class<ProductPromoted> productPromotedClass) {
        Class<?> eventType = productPromotedClass;
        //System.out.println("Liste des subscribers avant unregister: " + subscribers);

        @SuppressWarnings("unchecked")
        List<DomainEventSubscriber<T>> allSubscribers = this.subscribers();
        List<DomainEventSubscriber<T>> newListSubscribers = new ArrayList<DomainEventSubscriber<T>>();
        for (DomainEventSubscriber<T> subscriber : allSubscribers) {
            Class<?> subscribedToType = subscriber.subscribedToEventType();

            if (eventType != subscribedToType) {
                newListSubscribers.add(subscriber);
            }
        }
        this.subscribers = newListSubscribers;
        //System.out.println("Liste des subscribers apres unregister: " + subscribers);
    }

    public List getSubscribers() {
        return subscribers;
    }
}
