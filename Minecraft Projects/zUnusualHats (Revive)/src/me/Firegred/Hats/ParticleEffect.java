package me.Firegred.Hats;
 


 
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
 
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
 
public enum ParticleEffect {
 
    HUGE_EXPLOSION("hugeexplosion", 2), LARGE_EXPLODE("largeexplode", 1), FIREWORKS_SPARK("fireworksSpark", 3), BUBBLE("bubble", 4), SUSPEND("suspended", 7), DEPTH_SUSPEND("depthsuspend", 22), TOWN_AURA("townaura", 8), CRIT("crit", 9), MAGIC_CRIT("magicCrit", 10), SMOKE("smoke", 11), MOB_SPELL("mobSpell?", 13), MOB_SPELL_AMBIENT("bubble", 4), SPELL("spell", 15), INSTANT_SPELL("instantSpell", 14), WITCH_MAGIC("witchMagic", 17), NOTE("note", 23), PORTAL("portal", 24), ENCHANTMENT_TABLE("enchantmenttable", 25), EXPLODE("explode", 1), FLAME("flame", 26), LAVA("lava", 27), FOOTSTEP("footstep", 28), SPLASH("splash", 6), LARGE_SMOKE("largesmoke", 12), CLOUD("cloud", 29), RED_DUST("reddust", 30), SNOWBALL_POOF("snowballpoof", 31), DRIP_WATER("dripWater", 18), DRIP_LAVA("dripLava", 19), SNOW_SHOVEL("snowshovel", 32), SLIME("slime", 30), HEART("heart", 34), ANGRY_VILLAGER("angryVillager", 20), HAPPY_VILLAGER("happyVillager", 21);
 
    private String name;
    private int id;
 
    ParticleEffect(String name, int id) {
        this.name = name;
        this.id = id;
    }
 
    public String getName() {
        return name;
    }
 
    public int getId() {
        return id;
    }
 
    private static final Map<String, ParticleEffect> NAME_MAP = new HashMap<String, ParticleEffect>();
    private static final Map<Integer, ParticleEffect> ID_MAP = new HashMap<Integer, ParticleEffect>();
    static {
        for (ParticleEffect effect : values()) {
            NAME_MAP.put(effect.name, effect);
            ID_MAP.put(effect.id, effect);
        }
    }
 
    public static ParticleEffect fromName(String name) {
        if (name == null) {
            return null;
        }
        for (Entry<String, ParticleEffect> e : NAME_MAP.entrySet()) {
            if (e.getKey().equalsIgnoreCase(name)) {
                return e.getValue();
            }
        }
        return null;
    }
 
    public static ParticleEffect fromId(int id) {
        return ID_MAP.get(id);
    }
 
    
 
    public static void sendToPlayer(ParticleEffect effect, Player player, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
        sendPacket(player, packet);
    }
 
    public static void sendToLocation(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        Object packet = createPacket(effect, location, offsetX, offsetY, offsetZ, speed, count);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player, packet);
        }
    }
 
    public static void sendCrackToPlayer(boolean icon, int id, byte data, Player player, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
        Object packet = createCrackPacket(icon, id, data, location, offsetX, offsetY, offsetZ, count);
        sendPacket(player, packet);
    }
 
    public static void sendCrackToLocation(boolean icon, int id, byte data, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
        Object packet = createCrackPacket(icon, id, data, location, offsetX, offsetY, offsetZ, count);
        for (Player player : Bukkit.getOnlinePlayers()) {
            sendPacket(player, packet);
        }
    }
 
    public static Object createPacket(ParticleEffect effect, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {
        if (count <= 0)
            count = 1;
        Object packet = getPacketPlayOutWorldParticles();
        setValue(packet, "a", effect.name);
        setValue(packet, "b", (float) location.getX());
        setValue(packet, "c", (float) location.getY());
        setValue(packet, "d", (float) location.getZ());
        setValue(packet, "e", offsetX);
        setValue(packet, "f", offsetY);
        setValue(packet, "g", offsetZ);
        setValue(packet, "h", speed);
        setValue(packet, "i", count);
        return packet;
    }
 
    public static Object createCrackPacket(boolean icon, int id, byte data, Location location, float offsetX, float offsetY, float offsetZ, int count) throws Exception {
        if (count <= 0)
            count = 1;
        Object packet = getPacketPlayOutWorldParticles();
        String modifier = "iconcrack_" + id;
        if (!icon) {
            modifier = "tilecrack_" + id + "_" + data;
        }
        setValue(packet, "a", modifier);
        setValue(packet, "b", (float) location.getX());
        setValue(packet, "c", (float) location.getY());
        setValue(packet, "d", (float) location.getZ());
        setValue(packet, "e", offsetX);
        setValue(packet, "f", offsetY);
        setValue(packet, "g", offsetZ);
        setValue(packet, "h", 0.1F);
        setValue(packet, "i", count);
        return packet;
    }
 
    private static void setValue(Object instance, String fieldName, Object value) throws Exception {
        Field field = instance.getClass().getDeclaredField(fieldName);
        field.setAccessible(true);
        field.set(instance, value);
    }
 
    private static Object getEntityPlayer(Player p) throws Exception {
        Method getHandle = p.getClass().getMethod("getHandle");
        return getHandle.invoke(p);
    }
 
    private static String getPackageName() {
        return "net.minecraft.server." + Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];
    }
 
    private static Object getPacketPlayOutWorldParticles() throws Exception {
        Class<?> packet = Class.forName(getPackageName() + ".PacketPlayOutWorldParticles");
        return packet.getConstructors()[0].newInstance();
    }
 
    private static void sendPacket(Player p, Object packet) throws Exception {
        Object eplayer = getEntityPlayer(p);
        Field playerConnectionField = eplayer.getClass().getField("playerConnection");
        Object playerConnection = playerConnectionField.get(eplayer);
        for (Method m : playerConnection.getClass().getMethods()) {
            if (m.getName().equalsIgnoreCase("sendPacket")) {
                m.invoke(playerConnection, packet);
                return;
            }
        }
    }
}