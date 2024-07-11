package cn.ksmcbrigade.lastserver;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.File;
import java.nio.file.Files;

@Mod(LastServer.MODID)
public class LastServer {

    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "lastserver";

    public static final File config = new File("config/ls-config.json");
    public static boolean right = true;

    public static String lastIp = "";

    public LastServer(){
        MinecraftForge.EVENT_BUS.register(this);

        try {
            if(!config.exists()){
                JsonObject object = new JsonObject();
                object.addProperty("location","right");
                Files.writeString(config.toPath(),object.toString());
            }
            right = JsonParser.parseString(Files.readString(config.toPath())).getAsJsonObject().get("location").getAsString().toLowerCase().contains("right");
        }
        catch (Exception e){
            LOGGER.error("A exception in init the mod {}",e.getMessage(),e);
        }
    }
}
