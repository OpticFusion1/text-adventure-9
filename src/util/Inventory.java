package util;

import java.util.Map;
import java.util.HashMap;

final class Inventory {

  private final Map<Item, Integer> items = new HashMap<>();

  int getCount(final Item item) {
    return items.containsKey(item) ? items.get(item) : 0;
  }

  void add(final Item item) {
    if (!isFull()) {
      if (!items.containsKey(item)) {
        items.put(item, 1);
      }
      else {
        items.put(item, items.get(item) + 1);
      }
    }
    else {
      throw new UnsupportedOperationException("Your bag is full!");
    }
  }

  void remove(final Item item) {
    if (contains(item)) {
      items.put(item, items.get(item) - 1);
      if (items.get(item) == 0) {
        items.remove(item);
      }
    }
    else {
      throw new IllegalArgumentException("That item is not in the inventory.");
    }
  }

  boolean contains(final Item item) {
    return items.containsKey(item);
  }

  boolean isFull() {
    return size() >= 50;
  }

  int size() {
    int numItems = 0;
    for (final Map.Entry<Item, Integer> entry : items.entrySet()) {
      numItems += entry.getValue();
    }
    return numItems;
  }

  static Inventory of(final Item item) {
    final Inventory inventory = new Inventory();
    inventory.add(item);
    return inventory;
  }

  public String toString() {
    final StringBuilder bagList = new StringBuilder();
    for (Map.Entry<Item, Integer> entry : items.entrySet()) {
      final Item item = entry.getKey();
      final int count = entry.getValue();
      bagList.append(item.canonicalName()).append(" x").append(count).append(", ");
    }
    return bagList.toString().substring(0, bagList.length()-2);
  }
}
