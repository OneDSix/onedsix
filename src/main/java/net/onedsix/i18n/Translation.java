package net.onedsix.i18n;

import com.google.gson.JsonObject;
import lombok.extern.slf4j.Slf4j;
import net.onedsix.util.FileHandler;
import net.onedsix.util.Vars;
import net.onedsix.registry.Identifier;

import java.io.File;


/** A Modding-Compatible I18N framework, meant to replace LibGDX's I18N framework.
 * */
@SuppressWarnings("rawtypes")
@Slf4j
public class Translation {

    static class TranslationData {
        public final File extractionDirectory = null;
        public static TranslationData getModMetadata(String modid) {return null;}
    }

    private Identifier ident;
    private String modid;
    private TranslationData metadata;
    private JsonObject jsonObject;
    private final String key;

    public Translation(Identifier identifier, String key) {
        this.ident = identifier;
        this.key = key;
    }

    public Translation(String modid, String key) {
        this.modid = modid;
        this.key = key;
    }

    public Translation(TranslationData metadata, String key) {
        this.metadata = metadata;
        this.key = key;
    }

    public Translation(JsonObject json, String key) {
        this.jsonObject = json;
        this.key = key;
    }


    public static String Translate(Identifier identifier, String key) {
        return Translate(identifier.modid, key);
    }

    public static String Translate(String modid, String key) {
        TranslationData modData = TranslationData.getModMetadata(modid);
        if (modData != null) {
            return Translate(modData, key);
        }
        else {
            return key;
        }
    }

    public static String Translate(TranslationData metadata, String key) {
        File jsonFile = FileHandler.findInDirectory(Vars.locale+".json", metadata.extractionDirectory);
        JsonObject jsonObject = FileHandler.JSON.readJSON(jsonFile);
        return Translate(jsonObject, key);
    }

    public static String Translate(JsonObject json, String key) {
        String output;
        try {
            output = json.get(key).getAsString();
            if (output == null) output = key;
        }
        catch (Exception e) {
            log.error(e.getMessage(), e);
            output = key;
        }
        return output;
    }

    @Override public String toString() {
        if (this.jsonObject != null) {
            return Translate(jsonObject, key);
        } else
        if (this.metadata != null) {
            return Translate(metadata, key);
        } else
        if (this.modid != null) {
            return Translate(modid, key);
        } else
        if (this.ident != null) {
            return Translate(ident, key);
        } else {
            return this.key;
        }
    }
}
