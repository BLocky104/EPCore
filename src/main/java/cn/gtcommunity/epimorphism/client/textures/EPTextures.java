package cn.gtcommunity.epimorphism.client.textures;

import cn.gtcommunity.epimorphism.client.textures.Renderer.IsaMillRenderer;
import gregtech.client.renderer.texture.cube.OrientedOverlayRenderer;

public class EPTextures {

    //  OrientedOverlayRenderer
    public static OrientedOverlayRenderer CATALYTIC_REFORMER_OVERLAY = new OrientedOverlayRenderer("multiblock/catalytic_reformer");
    public static OrientedOverlayRenderer CRYSTALLIZATION_CRUCIBLE_OVERLAY = new OrientedOverlayRenderer("multiblock/crystallization_crucible");
    public static OrientedOverlayRenderer CVD_UNIT_OVERLAY = new OrientedOverlayRenderer("multiblock/cvd_unit");
    public static OrientedOverlayRenderer NANOSCALE_FABRICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/nanoscale_fabricator");
    public static OrientedOverlayRenderer SONICATOR_OVERLAY = new OrientedOverlayRenderer("multiblock/sonicator");
    public static OrientedOverlayRenderer ISA_MILL_OVERLAY = new OrientedOverlayRenderer("multiblock/isa_mill");
    public static OrientedOverlayRenderer BURNER_REACTOR_OVERLAY = new OrientedOverlayRenderer("multiblock/burner_reactor");

    //  EPOverlayRenderer
    public static EPOverlayRenderer ADVANCED_INVAR_CASING = new EPOverlayRenderer("casings/solid/advanced_invar_casing");
    public static EPOverlayRenderer ADVANCED_ALUMINIUM_CASING = new EPOverlayRenderer("casings/solid/advanced_aluminium_casing");
    public static EPOverlayRenderer ISA_MILL_CASING = new EPOverlayRenderer("casings/ep_mill_casing/isa_mill_casing");
    public static EPOverlayRenderer FLOTATION_CASING = new EPOverlayRenderer("casings/ep_mill_casing/flotation_casing");
    public static EPOverlayRenderer MULTIPART_BUFFER_HATCH = new EPOverlayRenderer("multiparts/overlay_buffer_hatch");
    public static EPOverlayRenderer MULTIPART_BALL_HATCH = new EPOverlayRenderer("multiparts/overlay_ball_hatch");

    // EPMultiRenderer
    public static IsaMillRenderer ISA_MILL = new IsaMillRenderer();

    public EPTextures() {
    }

    public static void preInit() {}

}
