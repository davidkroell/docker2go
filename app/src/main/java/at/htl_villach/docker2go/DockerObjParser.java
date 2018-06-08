package at.htl_villach.docker2go;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by kroel on 18.05.2018.
 */

public class DockerObjParser {
    // able to generate docker object without command
    public static DockerInfo Info(String input){
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(input, DockerInfo.class);
    }

    public static DockerContainer[] Containers(String input) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(input, DockerContainer[].class);
    }

    public static DockerImage[] Images(String input) {
        Gson gson = new GsonBuilder().create();
        return gson.fromJson(input, DockerImage[].class);
    }

    // another method to create Info object with command supplied
    public static DockerObj Any(DockerCommandBuilder command){
        switch (command.getApiEndpoint()){
            case "/info":
                return Info(command.getResult());
            default:
                return null;
        }
    }
}
