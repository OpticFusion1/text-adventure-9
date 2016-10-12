package util;

final class GameState extends GameBooleans {

  private Location location;
  private final Inventory bag;


  GameState(final Location location, final Inventory bag) {
    this.location = location;
    this.bag = bag;
  }

  Location location() {
    return location;
  }

  void updateLocation(final Location newLocation) {
    location = newLocation;
  }

  Inventory bag() {
    return bag;
  }

}
