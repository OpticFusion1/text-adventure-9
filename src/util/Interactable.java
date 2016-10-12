package util;

import java.util.List;

interface Interactable {

  String canonicalName();

  static String interactablesToString(final List<Interactable> interactables) {
    final StringBuilder sb = new StringBuilder();
    for (final Interactable interactable : interactables) {
      sb.append(interactable.canonicalName()).append(", ");
    }
    return sb.toString().substring(0, sb.length()-2);
  }
}
