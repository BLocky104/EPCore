package cn.gtcommunity.epimorphism.client;

import cn.gtcommunity.epimorphism.client.event.EPClientEventHandler;
import cn.gtcommunity.epimorphism.client.renderer.handler.StructureSelectRenderer;
import cn.gtcommunity.epimorphism.client.renderer.texture.EPTextures;
import cn.gtcommunity.epimorphism.client.utils.ShaderHelper;
import cn.gtcommunity.epimorphism.common.CommonProxy;
import cn.gtcommunity.epimorphism.common.blocks.EPMetablocks;
import net.minecraftforge.client.event.ModelRegistryEvent;
import net.minecraftforge.client.event.RenderWorldLastEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
@Mod.EventBusSubscriber(Side.CLIENT)
public class ClientProxy extends CommonProxy {
    public ClientProxy() {}

    public void preLoad() {
        super.preLoad();
        MinecraftForge.EVENT_BUS.register(new EPClientEventHandler());
        ShaderHelper.initShaders();
        EPTextures.preInit();
    }

    @Override
    public void onLoad() {
        super.onLoad();
        EPMetablocks.registerColors();
    }

    @SubscribeEvent
    public static void registerModels(ModelRegistryEvent event) {
        EPMetablocks.registerItemModels();
    }

    @SubscribeEvent
    public static void onRenderWorldLast(RenderWorldLastEvent event) {
        StructureSelectRenderer.render(event);
    }
}
