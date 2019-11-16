package at.stnwtr.indy4j.util;

import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Some small helper methods for parsing json.
 *
 * @author stnwtr
 * @since 15.11.2019
 */
public final class JsonUtility {

  /**
   * Converts a {@link JSONArray} to a {@link Set} which consists of {@link JSONObject} objects.
   *
   * @param jsonArray The json array to convert.
   * @return The new json object set.
   */
  public static Set<JSONObject> jsonArrayToJsonObjectSet(final JSONArray jsonArray) {
    Set<JSONObject> set = new HashSet<>();

    for (int i = 0; i < jsonArray.length(); i++) {
      set.add(jsonArray.getJSONObject(i));
    }

    return set;
  }
}
