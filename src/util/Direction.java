package util;

enum Direction {
  NORTH ("n") {
    @Override
    Location of(final Location location) {
      return Location.from(location.getX(), location.getY() - 1);
    }
  },
  SOUTH ("s") {
    @Override
    Location of(final Location location) {
      return Location.from(location.getX(), location.getY() + 1);
    }
  },
  WEST ("w") {
    @Override
    Location of(final Location location) {
      return Location.from(location.getX() - 1, location.getY());
    }
  },
  EAST ("e") {
    @Override
    Location of(final Location location) {
      return Location.from(location.getX() + 1, location.getY());
    }
  },
  INVALID_DIRECTION ("i") {
    @Override
    Location of(final Location location) {
      throw new UnsupportedOperationException("You canʼt travel towards an invalid direction.");
    }
  };

  private final String shortName;

  Direction(final String shortName) {
    this.shortName = shortName;
  }

  abstract Location of(final Location location);

  private boolean hasName(final String name) {
    return name.equalsIgnoreCase(this.name()) || name.equalsIgnoreCase(this.shortName);
  }

  static Direction from(final String name) {
    for (final Direction direction : Direction.values()) {
      if (direction.hasName(name)) {
        return direction;
      }
    }
    return INVALID_DIRECTION;
  }
}
