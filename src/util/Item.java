package util;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

enum Item implements Interactable {

  KNIFE ("knife", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  ROPE ("rope", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  BANANA ("banana", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.NORTHERN_FOREST) {
        GS.bag().add(Item.BANANA);
        return "You grabbed a banana! Ah, the good old days.";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  FISH ("fish", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.RIVER_HUT) {
        GS.bag().add(Item.FISH);
        return "You grabbed a fish! Better bring it to the fishermanʼs brother for him.";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  SOUP ("soup", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  EGGS ("eggs", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  FLOWERS ("flowers", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  NECKLACE ("magic necklace", Arrays.asList("necklace", "magical necklace")) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  CAT ("cat", Arrays.asList("kitten", "kitty")) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.CENTRAL_FOREST && !GS.savedCat()) {
        GS.bag().add(Item.CAT);
        GS.completedSavedCat();
        return "“I should find out whose cat this is.”";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  WATER ("water", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().getX() == 5) {
        return "The river looks far too polluted to get any potable water from…";
      }
      else if (GS.location() == Location.CREEK) {
        return "This creek is too dried up…";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  RING ("ring", Collections.singletonList("wedding ring")) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.BEACH_NORTH && !GS.retrievedRing()) {
        GS.bag().add(Item.RING);
        GS.completedRetrievedRing();
        return "You grabbed the wedding ring! “I wonder whose it is… Oh well!”";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  BOOTS ("boots", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.MONUMENT && !GS.gotBoots()) {
        if (GS.fedBro()) {
          GS.bag().add(Item.BOOTS);
          GS.completedGotBoots();
          return "These boots can withstand any weather!";
        }
        else {
          return "You canʼt just take someoneʼs boots without permission!";
        }
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  ARMOR ("armor", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.CAVE) {
        if (GS.gotArmor()) {
          return "You already got the armor!";
        }
        else {
          GS.bag().add(Item.ARMOR);
          GS.completedGotArmor();
          return "You found some armor!";
        }
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  SWORD ("sword", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  SAND ("sand", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().isNamed("Desert") || GS.location().isNamed("Royal Beach")) {
        GS.bag().add(Item.SAND);
        return "This is probably useless. But Iʼll take some sand.";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  SCROLL ("scroll", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location() == Location.EASTERN_ROAD) {
        return "Itʼs best to just leave that vile piece of paper here. " +
            "You donʼt want to get caught with that on you.";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  },
  SCORPION ("scorpion", Collections.emptyList()) {
    @Override
    String obtainMessage(final GameState GS) {
      if (GS.location().isNamed("Desert")) {
        return "Thatʼs dangerous!";
      }
      throw new IllegalArgumentException("This item isnʼt here.");
    }
  };

  private final String name;
  private final List<String> aliases;

  Item(final String name, final List<String> aliases) {
    this.name = name;
    this.aliases = aliases;
  }

  @Override
  public String canonicalName() {
    return name;
  }

  abstract String obtainMessage(final GameState GS);

  private boolean hasName(final String name) {
    return name.equalsIgnoreCase(this.name) || aliases.contains(name);
  }

  static Item from(final String name) {
    for (final Item item : Item.values()) {
      if (item.hasName(name)) {
        return item;
      }
    }
    throw new IllegalArgumentException("This is not a valid item.");
  }

  boolean isEdible() {
    return (this == BANANA || this == FISH || this == SOUP || this == EGGS || this == CAT);
  }

  boolean isPotable() {
    return (this == WATER || this == SOUP);
  }

  boolean canCut() {
    return (this == KNIFE || this == SWORD);
  }
}
