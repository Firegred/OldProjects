package me.Firegred.Christmas;
 


 
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
 
    HUGE_EXPLOSION("hugeexplosion", 0), LARGE_EXPLODE("largeexplode", 1), FIREWORKS_SPARK("fireworksSpark", 2), BUBBLE("bubble", 3), SUSPEND("suspended", 4), DEPTH_SUSPEND("depthsuspend", 5), TOWN_AURA("townaura", 6), CRIT("crit", 7), MAGIC_CRIT("magicCrit", 8), SMOKE("smoke", 9), MOB_SPELL("mobSpell", 10), MOB_SPELL_AMBIENT("mobSpellAmbient", 11), SPELL("spell", 12), INSTANT_SPELL("instantSpell", 13), WITCH_MAGIC("witchMagic", 14), NOTE("note", 15), PORTAL("portal", 16), ENCHANTMENT_TABLE("enchantmenttable", 17), EXPLODE("explode", 18), FLAME("flame", 19), LAVA("lava", 20), FOOTSTEP("footstep", 21), SPLASH("splash", 22), LARGE_SMOKE("largesmoke", 23), CLOUD("cloud", 24), RED_DUST("reddust", 25), SNOWBALL_POOF("snowballpoof", 26), DRIP_WATER("dripWater", 27), DRIP_LAVA("dripLava", 28), SNOW_SHOVEL("snowshovel", 29), SLIME("slime", 30), HEART("heart", 31), ANGRY_VILLAGER("angryVillager", 32), HAPPY_VILLAGER("happyVillager", 33), ICONCRACK("iconcrack_", 34), TILECRACK("blockcrack_", 35);
 
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