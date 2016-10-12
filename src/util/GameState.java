package util;

final class GameState {

  private Location location;
  private final Inventory bag;

  private boolean talkedToMom = false;
  private boolean savedCat = false;
  private boolean retrievedRing = false;
  private boolean fedBro = false;
  private boolean foundBoy = false;
  private boolean helpedDave = false;
  private boolean returnedRing = false;
  private boolean returnedCat = false;
  private boolean gotBoots = false;
  private boolean gotSword = false;
  private boolean gotArmor = false;

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

  boolean returnedCat() {
    return returnedCat;
  }

  void completedReturnedCat() {
    returnedCat = true;
  }

  boolean returnedRing() {
    return returnedRing;
  }

  void completedReturnedRing() {
    returnedRing = true;
  }

  boolean helpedDave() {
    return helpedDave;
  }

  void completedHelpedDave() {
    helpedDave = true;
  }

  boolean foundBoy() {
    return foundBoy;
  }

  void completedFoundBoy() {
    foundBoy = true;
  }

  boolean fedBro() {
    return fedBro;
  }

  void completedFedBro() {
    fedBro = true;
  }

  boolean retrievedRing() {
    return retrievedRing;
  }

  void completedRetrievedRing() {
    retrievedRing = true;
  }

  boolean savedCat() {
    return savedCat;
  }

  void completedSavedCat() {
    savedCat = true;
  }

  boolean talkedToMom() {
    return talkedToMom;
  }

  void completedTalkedToMom() {
    talkedToMom = true;
  }

  boolean gotBoots() {
    return gotBoots;
  }

  void completedGotBoots() {
    gotBoots = true;
  }

  boolean gotArmor() {
    return gotArmor;
  }

  void completedGotArmor() {
    gotArmor = true;
  }

  boolean gotSword() {
    return gotSword;
  }

  void completedGotSword() {
    gotSword = true;
  }
}
