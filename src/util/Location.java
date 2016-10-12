package util;

import java.util.HashMap;
import java.util.Map;

enum Location implements Interactable, LocationInfoStrings {

  EERIE_MOUNTAIN1 (1, 0, "Eerie Mountain", MOUNTAIN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      return EERIE_MOUNTAIN3.travelMessage(GS);
    }
  },
  EERIE_MOUNTAIN2 (2, 0, "Eerie Mountain", MOUNTAIN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      return EERIE_MOUNTAIN3.travelMessage(GS);
    }
  },
  EERIE_MOUNTAIN3 (3, 0, "Eerie Mountain", MOUNTAIN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.bag().contains(Item.SWORD) && GS.bag().contains(Item.ARMOR)) {
        GS.updateLocation(EERIE_MOUNTAIN3);
        return "You begin your journey to the mountain with your chin held high and your sword ready! " +
            "You climb the mountain to find a dragon awaiting with its jaws agape!";
      }
      else {
        return "An eerie mountain lies north. You're not quite strong enough to go there yet.";
      }
    }
  },
  EERIE_MOUNTAIN4 (4, 0, "Eerie Mountain", MOUNTAIN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      return EERIE_MOUNTAIN3.travelMessage(GS);
    }
  },
  EERIE_MOUNTAIN5 (5, 0, "Eerie Mountain", MOUNTAIN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      return EERIE_MOUNTAIN3.travelMessage(GS);
    }
  },
  CREEK (1, 1, "Creek", CREEK_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(CREEK);
      return CREEK_INFO;
    }
  },
  WESTERN_ROAD (2, 1, "Western Road", WROAD_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(WESTERN_ROAD);
      return "Western Road! " + WROAD_INFO;
    }
  },
  QUEENS_CITY (3, 1, "Queen's City", QUEENSCITY_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(QUEENS_CITY);
      return QUEENSCITY_INFO;
    }
  },
  EASTERN_ROAD (4, 1, "Eastern Road", EROAD_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(EASTERN_ROAD);
      return "Eastern Road! " + EROAD_INFO;
    }
  },
  CLIFF_SIDE1 (5, 1, "Cliff Side", String.format(CLIFF_INFO, "south")) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      if (prevLoc == RIVER_HUT) {
        GS.updateLocation(CLIFF_SIDE1);
        return "You scaled up the cliff side!";
      }
      else {
        GS.updateLocation(CLIFF_SIDE1);
        return prevLoc.isNamed("Cliff Side") ? String.format(CLIFF_INFO, "south") :
            "You've come to the cliff side! " + String.format(CLIFF_INFO, "south");
      }
    }
  },
  NORTHERN_FOREST (1, 2, "Forest", NFOREST_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      GS.updateLocation(NORTHERN_FOREST);
      return prevLoc.isNamed("Forest") ? NFOREST_INFO :
          "You're in the forest! Some parts are dangerous so try not to venture too far in.";
    }
  },
  OPEN_FIELD (2, 2, "Open Field", FIELD_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(OPEN_FIELD);
      return FIELD_INFO;
    }
  },
  SOUTHERN_ROAD (3, 2, "Southern Road", SROAD_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(SOUTHERN_ROAD);
      return "Southern Road! " + SROAD_INFO;
    }
  },
  CLIFF_SIDE2 (4, 2, "Cliff Side", String.format(CLIFF_INFO, "east")) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      if (GS.location() == RIVER_HUT) {
        GS.updateLocation(CLIFF_SIDE2);
        return "You scaled up the cliff side!";
      }
      else {
        GS.updateLocation(CLIFF_SIDE2);
        return prevLoc.isNamed("Cliff Side") ? String.format(CLIFF_INFO, "east") :
            "You've come to the cliff side! " + String.format(CLIFF_INFO, "east");
      }
    }
  },
  RIVER_HUT (5, 2, "River Hut", RIVER_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location() == CLIFF_SIDE1 || GS.location() == CLIFF_SIDE2) {
        return "You can't jump off the cliff like that! Do you have anything to climb down, perhaps?";
      }
      else {
        GS.updateLocation(RIVER_HUT);
        return "There's a small river hut here. It looks like there's a fisherman on the porch.";
      }
    }
  },
  CENTRAL_FOREST (1, 3, "Forest", CFOREST_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      GS.updateLocation(CENTRAL_FOREST);
      return prevLoc.isNamed("Forest") ? CFOREST_INFO :
          "You're in the forest! Some parts are dangerous so try not to venture too far in.";
    }
  },
  SMALL_FARM (2, 3, "Small Farm", FARM_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(SMALL_FARM);
      return FARM_INFO;
    }
  },
  VILLAGE (3, 3, "Village", VILLAGE_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(VILLAGE);
      return "Welcome back to your home village. Your mom hasn't left the front door. " +
          "It looks like she's just waiting for you to come back; she must miss you.";
    }
  },
  CLIFF_SIDE3 (4, 3, "Cliff Side", String.format(CLIFF_INFO, "east")) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      if (GS.location() == BEACH_NORTH) {
        GS.updateLocation(CLIFF_SIDE3);
        return "You scaled up the cliff side!";
      }
      else {
        GS.updateLocation(CLIFF_SIDE3);
        return prevLoc.isNamed("Cliff Side") ? String.format(CLIFF_INFO, "east") :
            "You've come to the cliff side! " + String.format(CLIFF_INFO, "east");
      }
    }
  },
  BEACH_NORTH (5, 3, "Royal Beach", BEACH_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location() == CLIFF_SIDE3) {
        return "You can't jump off the cliff like that! Do you have anything to climb down, perhaps?";
      }
      else {
        final Location prevLoc = GS.location();
        GS.updateLocation(BEACH_NORTH);
        return (prevLoc == BEACH_SOUTH) ? "The northern part of the beach! " +
            "It's so much nicer here! It looks like there's something shining in the sand." :
            BEACH_INFO + " It looks like there's something shining in the sand.";
      }
    }
  },
  CAVE (6, 3, "Cave", CAVE_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.bag().contains(Item.BOOTS)) {
        GS.updateLocation(CAVE);
        return "You slowly waded through the river. And have made it into the cave.";
      }
      else {
        return "The river seems crossable here. You could wade through, if only you had some boots…";
      }
    }
  },
  SOUTHERN_FOREST (1, 4, "Forest", SFOREST_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      GS.updateLocation(SOUTHERN_FOREST);
      return prevLoc.isNamed("Forest") ? SFOREST_INFO :
          "You're in the forest! Some parts are dangerous so try not to venture too far in.";
    }
  },
  DESERT1 (2, 4, "Desert", DESERT1_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT1);
          return "You drank some water and continued on in the desert. " + DESERT1_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT1);
        return "The Desert! " + DESERT1_INFO;
      }
    }
  },
  DESERT2 (3, 4, "Desert", DESERT_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT2);
          return "You drank some water and continued on in the desert. " + DESERT_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT2);
        return "The Desert! " + DESERT_INFO;
      }
    }
  },
  DESERT3 (4, 4, "Desert", DESERT_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT3);
          return "You drank some water and continued on in the desert. " + DESERT_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT3);
        return "The Desert! " + DESERT_INFO;
      }
    }
  },
  BEACH_SOUTH (5, 4, "Royal Beach", BEACH_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      final Location prevLoc = GS.location();
      GS.updateLocation(BEACH_SOUTH);
      return (prevLoc == BEACH_NORTH) ?
          "The southern part of the beach! It's not quite as nice here…" : BEACH_INFO;
    }
  },
  MONUMENT (1, 5, "Large Monument", MONUMENT_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(MONUMENT);
      return MONUMENT_INFO;
    }
  },
  DESERT4 (2, 5, "Desert", DESERT_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT4);
          return "You drank some water and continued on in the desert. " + DESERT_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT4);
        return "The Desert! " + DESERT_INFO;
      }
    }
  },
  DESERT5 (3, 5, "Desert", DESERT5_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT5);
          return "You drank some water and continued on in the desert. " + DESERT5_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT5);
        return "The Desert! " + DESERT5_INFO;
      }
    }
  },
  DESERT6 (4, 5, "Desert", DESERT6_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        if (!GS.bag().contains(Item.WATER)) {
          return "You can't keep traveling in the desert with no water! You'll die!";
        }
        else {
          GS.bag().remove(Item.WATER);
          GS.updateLocation(DESERT6);
          return "You drank some water and continued on in the desert. " + DESERT6_INFO;
        }
      }
      else {
        GS.updateLocation(DESERT6);
        return "The Desert! " + DESERT6_INFO;
      }
    }
  },
  ABANDONED_TOWN (5, 5, "Abandoned Town", ABANDONEDTOWN_INFO) {
    @Override
    String travelMessage(final GameState GS) {
      GS.updateLocation(ABANDONED_TOWN);
      return ABANDONEDTOWN_INFO;
    }
  },
  INVALID_LOCATION (-1, -1, "Nowhere", "You are nowhere.") {
    @Override
    String travelMessage(final GameState GS) {
      return "You are nowhere.";
    }
  };

  private final int x;
  private final int y;
  private final String name;
  private final String info;

  Location(final int x, final int y, final String name, final String info) {
    this.x = x;
    this.y = y;
    this.name = name;
    this.info = info;
  }

  int getX() {
    return x;
  }

  int getY() {
    return y;
  }

  @Override
  public String canonicalName() {
    return name;
  }

  abstract String travelMessage(final GameState GS);

  String info() {
    return info;
  }

  static Location from(int x, int y) {
    for (final Location location : Location.values()) {
      if (location.getX() == x && location.getY() == y) {
        return location;
      }
    }
    return INVALID_LOCATION;
  }

  boolean isNamed(final String name) {
    return name.equalsIgnoreCase(this.name);
  }

  enum Direction {
    NORTH {
      @Override
      Location of(final Location location) {
        return Location.from(location.getX(), location.getY() - 1);
      }
    },
    SOUTH {
      @Override
      Location of(final Location location) {
        return Location.from(location.getX(), location.getY() + 1);
      }
    },
    WEST {
      @Override
      Location of(final Location location) {
        return Location.from(location.getX() - 1, location.getY());
      }
    },
    EAST {
      @Override
      Location of(final Location location) {
        return Location.from(location.getX() + 1, location.getY());
      }
    },
    INVALID_DIRECTION {
      @Override
      Location of(final Location location) {
        throw new UnsupportedOperationException("You can't travel towards an invalid direction.");
      }
    };

    abstract Location of(final Location location);

    static Direction from(final String string) {
      final String name = aliases().containsKey(string) ? aliases().get(string) : string;
      for (final Direction direction : Direction.values()) {
        if (name.equalsIgnoreCase(direction.name())) {
          return direction;
        }
      }
      return INVALID_DIRECTION;
    }

    private static Map<String, String> aliases() {
      final Map<String, String> aliases = new HashMap<>();
      aliases.put("n", "north");
      aliases.put("s", "south");
      aliases.put("w", "west");
      aliases.put("e", "east");
      return aliases;
    }
  }
}
