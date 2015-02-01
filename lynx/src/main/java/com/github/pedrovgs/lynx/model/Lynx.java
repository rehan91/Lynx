/*
 * Copyright (C) 2015 Pedro Vicente Gomez Sanchez.
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

package com.github.pedrovgs.lynx.model;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Main business logic class for this project. Lynx responsibility is related to listen Logcat
 * events and notify it to the Lynx listeners transforming all the information from a plain String
 * to a Trace with all the information needed.
 *
 * @author Pedro Vicente Gómez Sánchez.
 */
public class Lynx {

  private final List<Listener> listeners;

  public Lynx() {
    listeners = new ArrayList<Listener>();
  }

  public void startReading() {
    List<Trace> fakeTraces = generateFakeTraces(2000);
    notifyListeners(fakeTraces);
  }

  private List<Trace> generateFakeTraces(int numberOfTraces) {
    List<Trace> traces = new LinkedList<Trace>();
    for (int i = 0; i < numberOfTraces; i++) {
      traces.add(new Trace(TraceLevel.VERBOSE, "Pedro Vicente es el puto amo " + i));
    }
    return traces;
  }

  public void stopReading() {

  }

  public void registerListener(Listener lynxPresenter) {
    listeners.add(lynxPresenter);
  }

  public void unregisterListener(Listener lynxPresenter) {
    listeners.remove(lynxPresenter);
  }

  private void notifyListeners(List<Trace> fakeTraces) {
    for (Listener listener : listeners) {
      listener.onNewTraces(fakeTraces);
    }
  }

  public interface Listener {

    void onNewTraces(List<Trace> traces);
  }
}
