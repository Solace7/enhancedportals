package enhancedportals.tile;

import net.minecraft.nbt.NBTTagCompound;

public class TileFrameBasic extends TileFrame
{
    //todo Activate

   /* @Override
    public boolean activate(EntityPlayer player, ItemStack stack)
    {
        if (player.isSneaking())
        {
            return false;
        }

        TileController controller = getPortalController();

        if (stack != null && controller != null && controller.isFinalized())
        {
            if (GeneralUtils.isWrench(stack))
            {
                GuiHandler.openGui(player, controller, GuiHandler.PORTAL_CONTROLLER_A);
                return true;
            }
            else if (stack.getItem() == ItemNanobrush.instance)
            {
                GuiHandler.openGui(player, controller, GuiHandler.TEXTURE_A);
                return true;
            }
        }

        return false;
    }*/

    @Override
    public void addDataToPacket(NBTTagCompound tag)
    {

    }

    @Override
    public void onDataPacket(NBTTagCompound tag)
    {

    }
}
