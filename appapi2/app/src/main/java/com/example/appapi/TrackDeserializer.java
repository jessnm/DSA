/*
package com.example.appapi;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

public class TrackDeserializer implements JsonDeserializer<Track> {

    @Override
    public Track deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        Track track = new Track();

        JsonObject trackJsonObject =  json.getAsJsonObject();
        track.id = trackJsonObject.get("id").getAsString();
        track.title = trackJsonObject.get("title").getAsString();
        track.singer = trackJsonObject.get("singet").getAsString();

        return track;
    }

}
*/
