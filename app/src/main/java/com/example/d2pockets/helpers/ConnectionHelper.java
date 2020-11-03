package com.example.d2pockets.helpers;

import android.util.Log;

import com.example.d2pockets.constants.Endpoints;
import com.example.d2pockets.item_instance.WeaponItem;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ConnectionHelper {

    private Endpoints endpoints = new Endpoints();
    private String apiKey = "a81f82870a4a4b0aa302632f91768e6a";
    private String membershipId;  //playerId
    private String membershipType; //platform (3 - Steam, 2 - PS, -1 - All)
    private List<String> characters;
    private JsonArray itemArray;
    private final String BASE_URL = "https://bungie.net";

    public JsonObject getResponse(String endpoint) throws IOException {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder().url(endpoint).header("X-API-KEY", apiKey).build();
        Response response = client.newCall(request).execute();
        JsonObject result = null;
        if (response.body() != null)
            result = (JsonObject) JsonParser.parseString(response.body().string());
        return result;
    }


    public void getMembershipIdAndType(String username) {
        JsonObject jsonObject = null;
        try {
            jsonObject = getResponse(String.format(endpoints.GET_USER, username));
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (jsonObject != null) {
            membershipId = jsonObject.getAsJsonArray("Response").get(0).getAsJsonObject().get("membershipId").toString().replaceAll("\"", "");
            membershipType = jsonObject.getAsJsonArray("Response").get(0).getAsJsonObject().get("membershipType").toString().replaceAll("\"", "");
        }
        List<String> membIdAndType = Arrays.asList(membershipId, membershipType);
        membIdAndType.forEach(x -> Log.e("!@#", x));
    }

    private JsonObject getProfile() throws IOException {
        return getResponse(String.format(endpoints.GET_PROFILE, membershipType, membershipId));
    }

    public List<String> getCharachterIDs(String username) throws IOException {
        getMembershipIdAndType(username);
        JsonObject jsonObject = getProfile();
        List<String> characterIDs = new ArrayList<>();
        JsonArray jsonArray = jsonObject.getAsJsonObject("Response").getAsJsonObject("profile").getAsJsonObject("data").getAsJsonArray("characterIds");
        for (int i = 0; i < jsonArray.size(); i++) {
            characterIDs.add(jsonArray.get(i).getAsString());
        }
        characters = new ArrayList<>(characterIDs);
        characters.forEach(x -> Log.e("!@#", x));
        return characters;
    }

    public void getCharacterInfo(int character) throws IOException {
        JsonObject object = getResponse(String.format(endpoints.GET_CHARACTER_INFO, membershipType, membershipId, characters.get(character)));
        itemArray = object.getAsJsonObject("Response").getAsJsonObject("equipment").getAsJsonObject("data").getAsJsonArray("items");
//        itemArray.forEach(x->Log.e("!@#", x.toString()));
    }

    public JsonObject getItemInfo(int itemPosInArray) throws IOException {
        String item = itemArray.get(itemPosInArray).getAsJsonObject().get("itemInstanceId").toString().replaceAll("\"", "");
        JsonObject itemObject = getResponse(String.format(endpoints.GET_ITEM, membershipType, membershipId, item));
        Log.e("!@#", itemObject.toString());
        return itemObject;
    }

    public String getManifest() throws IOException {
        String manifestLink = getResponse(endpoints.GET_MANIFEST).getAsJsonObject("Response").getAsJsonObject("mobileWorldContentPaths").get("en").toString().replaceAll("\"", "");
        return BASE_URL + manifestLink;
    }

    public List<String> getItemPerks(int itemPosition) throws IOException {
        List<String> perkIcons = new ArrayList<>();
        JsonObject weapon = getItemInfo(itemPosition);
        JsonArray perks = weapon.getAsJsonObject("Response").getAsJsonObject("perks").getAsJsonObject("data").getAsJsonArray("perks");
        for (int i = 0; i < perks.size(); i++) {
            String perk = perks.get(i).getAsJsonObject().get("iconPath").toString().replaceAll("\"", "");
            if (!perk.isEmpty())
                perkIcons.add(BASE_URL + perk);
        }
        perkIcons.forEach(x -> Log.e("!@#%&", x));
        return perkIcons;
    }
}
