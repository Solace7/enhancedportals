package enhancedportals.item;

import com.mojang.realmsclient.gui.ChatFormatting;
import enhancedportals.Reference;
import enhancedportals.utility.Localization;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

import java.util.List;

public class ItemStabilizer extends Item
{
    public ItemStabilizer(String n)
    {
        super();
        setMaxDamage(0);
        setHasSubtypes(true);
        setRegistryName(Reference.EPMod.mod_id, n);
        setUnlocalizedName(getRegistryName().toString());
    }

    @SuppressWarnings({"rawtypes", "unchecked"})
    @Override
    public void addInformation(ItemStack stack, EntityPlayer player, List list, boolean par4)
    {
        list.add(Localization.get("block.multiblockStructure"));
        list.add(ChatFormatting.DARK_GRAY + Localization.get("block.dbsSize"));
    }

    @Override
    public int getMetadata(int par1)
    {
        return par1;
    }
}
