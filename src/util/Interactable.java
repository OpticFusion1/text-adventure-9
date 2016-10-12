package util;

import java.util.List;

interface Interactable {

  String canonicalName();

  static String interactablesToString(final List<Interactable> interactables) {
    final StringBuilder sb = new StringBuilder();
    boolean first = true;
    for (final Interactable interactable : interactables) {
      if (first) {
        first = false;
      }
      else {
        sb.append(", ");
      }
      sb.append(interactable.canonicalName());
    }
    return sb.toString();
  }
}
