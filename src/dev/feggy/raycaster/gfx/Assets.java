package dev.feggy.raycaster.gfx;

import java.awt.image.BufferedImage;

public class Assets {
	
	// private static final int width = 64, height = 64;

	public static BufferedImage crosshair;
	public static BufferedImage[] machinegun;
	public static BufferedImage wood, bricks;
	public static BufferedImage floor, ceiling, grass;
	public static BufferedImage bronzepillar;
	public static BufferedImage greenlamp;
	
	public static BufferedImage[] npcGuard_s;
	public static BufferedImage npcGuard_pain, npcGuard_shoot1, npcGuard_shoot2, npcGuard_shoot3;
	
	public static BufferedImage sky;
	
	public static void init() {
		
		// GUI stuff
		crosshair = ImageLoader.loadImage("/textures/ui/crosshair.png");
		
		// UI Guns
		TexSheet mGunSheet = new TexSheet(ImageLoader.loadImage("/textures/ui/gun.png"));
		machinegun = new BufferedImage[3];
		machinegun[0] = mGunSheet.crop(0, 0, 53, 72);
		machinegun[1] = mGunSheet.crop(53, 0, 53, 72);
		machinegun[2] = mGunSheet.crop(106, 0, 53, 72);
		
		// Walls
		wood = ImageLoader.loadImage("/textures/walls/Woodlike01.bmp");
		bricks = ImageLoader.loadImage("/textures/walls/Bricks01.bmp");
		
		// Tiles
		floor = ImageLoader.loadImage("/textures/tiles/Floor1.bmp");
		ceiling = ImageLoader.loadImage("/textures/tiles/Ceil1.bmp");
		grass = ImageLoader.loadImage("/textures/tiles/Floor2.bmp");
		
		// Enemies - guard
		npcGuard_s = new BufferedImage[8];
		npcGuard_s[0] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_7.bmp");
		npcGuard_s[1] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_8.bmp");
		npcGuard_s[2] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_1.bmp");
		npcGuard_s[3] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_2.bmp");
		npcGuard_s[4] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_3.bmp");
		npcGuard_s[5] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_4.bmp");
		npcGuard_s[6] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_5.bmp");
		npcGuard_s[7] = ImageLoader.loadImage("/textures/npc/guard/mguard_s_6.bmp");
		npcGuard_pain = ImageLoader.loadImage("/textures/npc/guard/mguard_pain2.bmp");
		
		npcGuard_shoot1 = ImageLoader.loadImage("/textures/npc/guard/mguard_shoot1.bmp");
		npcGuard_shoot2 = ImageLoader.loadImage("/textures/npc/guard/mguard_shoot2.bmp");
		npcGuard_shoot3 = ImageLoader.loadImage("/textures/npc/guard/mguard_shoot3.bmp");
		
		// Decoration - solid
		bronzepillar = ImageLoader.loadImage("/textures/decoration/BronzeCol.bmp");
		
		// Decoration - non-solid
		greenlamp = ImageLoader.loadImage("/textures/decoration/GreenC.bmp");
		
		// Sky
		sky = ImageLoader.loadImage("/textures/sky/Sky1.bmp");
	}

}
