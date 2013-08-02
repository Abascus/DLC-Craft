package Abascus.DLCCraft.common;
import java.util.EnumSet;
import java.util.Iterator;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;
public class DLCKeyBinding extends KeyHandler
{
         private EnumSet tickTypes = EnumSet.of(TickType.CLIENT);
        
         public DLCKeyBinding(KeyBinding[] keyBindings, boolean[] repeatings)
         {
                 super(keyBindings, repeatings);
         }
         @Override
         public String getLabel()
         {
                 return "DLC Shop";
         }
         @Override
         public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat)
         {
         }
         @Override
         public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd)
         {
                 //What to do when key is released/up
         }
         @Override
         public EnumSet<TickType> ticks()
         {
                 return tickTypes;
         }
}