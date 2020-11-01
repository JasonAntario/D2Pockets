package com.example.d2pockets;

import android.util.Log;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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

    public void getCharacterInfo() throws IOException {
        JsonObject object = getResponse(String.format(endpoints.GET_CHARACTER_INFO, membershipType, membershipId, characters.get(0)));
        itemArray = object.getAsJsonObject("Response").getAsJsonObject("equipment").getAsJsonObject("data").getAsJsonArray("items");
        Log.e("!@#", itemArray.get(0).getAsJsonObject().get("itemInstanceId").toString().replaceAll("\"", ""));
    }

    public void getItemInfo(int itemPosInArray) throws IOException {
        JsonObject object = getResponse(String.format(endpoints.GET_ITEM, membershipType, membershipId, itemArray.get(itemPosInArray).getAsJsonObject().get("itemInstanceId").toString().replaceAll("\"", "")));
        Log.e("!@#", object.toString());

    }
}
