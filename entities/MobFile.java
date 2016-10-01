package entities;

import com.gmail.zyphicxs.Main;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class MobFile{
    private YamlConfiguration file;

    MobFile(YamlConfiguration file){
        this.file = file;
    }

    public String getString(String fieldPath){
        if(file.contains(fieldPath)){
            return file.getString(fieldPath);
        }
        else{
            return "";
        }
    }

    public int getInt(String fieldPath){
        if(file.contains(fieldPath)){
            return file.getInt(fieldPath);
        }
        else{
            return 0;
        }
    }

    public double getDouble(String fieldPath){
        if(file.contains(fieldPath)){
            return file.getDouble(fieldPath);
        }
        else{
            return 0;
        }
    }

    public boolean getBoolean(String fieldPath){
        if(file.contains(fieldPath)){
            return file.getBoolean(fieldPath);
        }
        else{
            return false;
        }
    }

    public List<String> getStringList(String fieldPath){
        if(file.contains(fieldPath)){
            return file.getStringList(fieldPath);
        }
        else{
            return null;
        }
    }
    public static HashMap<String, EntityInfo> loadAllMobs(){
        HashMap<String, EntityInfo> mobMap = new HashMap<String, EntityInfo>();

        for(File file : new File(Main.instance.getDataFolder() + File.separator + "Mobs").listFiles()){
            EntityInfo entity = new EntityInfo(file);
            mobMap.put(file.getName().replaceFirst("[.][^.]+$", ""), entity);
        }
        return mobMap;
    }
}
