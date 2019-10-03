package at.stnwtr.indy4j.request;

import at.stnwtr.indy4j.object.IndyObject;
import at.stnwtr.indy4j.route.Route;

public class IndyRequest<T extends IndyObject> {

  private final Route<T> route;

  public IndyRequest(Route<T> route) {
    this.route = route;
  }

  public IndyRequest<T> head() {
    return null;
  }

  public IndyRequest<T> body() {
    return null;
  }

  public T request() {
    return null;
  }
}
