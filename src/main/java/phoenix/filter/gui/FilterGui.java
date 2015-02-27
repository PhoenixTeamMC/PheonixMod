package phoenix.filter.gui;

import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;
import phoenix.main.lib.Reference;


public class FilterGui extends GuiContainer{

	
	
	static final ResourceLocation TEXTURE = new ResourceLocation(Reference.MODID.toLowerCase() + ":textures/FilterGui.png");
	
	public FilterGui(Container container) {
		super(container);
		
		this.xSize = 184;
		this.ySize = 183;
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(float p_146976_1_,int p_146976_2_, int p_146976_3_) {
		bindTexture(TEXTURE);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
	}
	
	public void bindTexture(ResourceLocation texture) {

		mc.renderEngine.bindTexture(texture);
	}
	
	public void onMouseClicked(){
		
	}
	

}