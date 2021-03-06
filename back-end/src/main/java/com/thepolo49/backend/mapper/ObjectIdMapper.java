package com.thepolo49.backend.mapper;

import org.bson.types.ObjectId;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public class ObjectIdMapper {

    public String objectIdToString(ObjectId objectId) {
        return objectId.toString();
    }

    public ObjectId stringToObjectId(String string) {
        if (string == null) {
            return null;
        }
        return new ObjectId(string);
    }

}