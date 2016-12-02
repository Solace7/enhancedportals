package enhancedportals.tile;

import net.minecraftforge.fml.common.Optional.Interface;
import net.minecraftforge.fml.common.Optional.InterfaceList;
import net.minecraftforge.fml.common.Optional.Method;
import dan200.computercraft.api.lua.ILuaContext;
import dan200.computercraft.api.peripheral.IComputerAccess;
import dan200.computercraft.api.peripheral.IPeripheral;
import enhancedportals.Reference;
import enhancedportals.item.ItemNanobrush;
import enhancedportals.network.GuiHandler;
import enhancedportals.utility.GeneralUtils;
import enhancedportals.utility.Localization;
import li.cil.oc.api.machine.Arguments;
import li.cil.oc.api.machine.Callback;
import li.cil.oc.api.machine.Context;
import li.cil.oc.api.network.SimpleComponent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ChatComponentText;

@InterfaceList(value = {@Interface(iface = "dan200.computercraft.api.peripheral.IPeripheral", modid = Reference.Dependencies.MODID_COMPUTERCRAFT), @Interface(iface = "li.cil.oc.api.network.SimpleComponent", modid = Reference.Dependencies.MODID_OPENCOMPUTERS)})
public class TileNetworkInterface extends TileFrame implements IPeripheral, SimpleComponent
{
    @Override
    public boolean activate(EntityPlayer player, ItemStack stack)
    {
        if (player.isSneaking())
        {
            return false;
        }

        TileController controller = getPortalController();

        if (stack != null && controller != null && controller.isFinalized())
        {
            if (GeneralUtils.isWrench(stack) && !player.isSneaking())
            {
                if (controller.getIdentifierUnique() == null)
                {
                    if (!worldObj.isRemote)
                    {
                        player.addChatComponentMessage(new ChatComponentText(Localization.getChatError("noUidSet")));
                    }
                }
                else
                {
                    GuiHandler.openGui(player, controller, GuiHandler.NETWORK_INTERFACE_A);
                }
            }
            else if (stack.getItem() == ItemNanobrush.instance)
            {
                GuiHandler.openGui(player, controller, GuiHandler.TEXTURE_A);
                return true;
            }
        }

        return false;
    }

    @Override
    public void addDataToPacket(NBTTagCompound tag)
    {

    }

    @Override
    public boolean canUpdate()
    {
        return true;
    }


    //OpenComputers Methods

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public void attach(IComputerAccess computer)
    {

    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public Object[] callMethod(IComputerAccess computer, ILuaContext context, int method, Object[] arguments) throws Exception
    {
        if (method == 0)
        {
            getPortalController().connectionDial();
        }
        else if (method == 1)
        {
            getPortalController().connectionTerminate();
        }

        return null;
    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public void detach(IComputerAccess computer)
    {

    }

    @Callback(doc = "function():boolean -- Attempts to create a connection to the next portal in the network.")
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public Object[] dial(Context context, Arguments args)
    {
        getPortalController().connectionDial();
        return new Object[]{true};
    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public boolean equals(IPeripheral other)
    {
        return other == this;
    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public String getComponentName()
    {
        return "ep_interface_network";
    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public String[] getMethodNames()
    {
        return new String[]{"dial", "terminate"};
    }

    @Override
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public String getType()
    {
        return "ep_interface_network";
    }

    @Override
    public void onDataPacket(NBTTagCompound tag)
    {

    }

    @Callback(doc = "function():boolean -- Terminates any active connection.")
    @Method(modid = Reference.Dependencies.MODID_OPENCOMPUTERS)
    public Object[] terminate(Context context, Arguments args)
    {
        getPortalController().connectionTerminate();
        return new Object[]{true};
    }
}
