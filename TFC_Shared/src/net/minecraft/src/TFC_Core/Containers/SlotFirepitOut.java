package net.minecraft.src.TFC_Core.Containers;

import net.minecraft.src.*;
import net.minecraft.src.TFC_Core.TFC_Core;

public class SlotFirepitOut extends Slot

{
	public SlotFirepitOut(EntityPlayer entityplayer, IInventory iinventory, int i, int j, int k)
	{
		super(iinventory, i, j, k);

	}

	public boolean isItemValid(ItemStack itemstack)
	{
		if(itemstack.itemID == TFCItems.terraCeramicMold.shiftedIndex) {
			return true;
		}

		return false;
	}
	
    public int getSlotStackLimit()
    {
        return 1;
    }
}