package TFC.WorldGen.Generators;

import java.util.Random;

import cpw.mods.fml.common.IWorldGenerator;

import TFC.WorldGen.DataLayer;
import TFC.WorldGen.TFCBiome;
import TFC.WorldGen.TFCWorldChunkManager;

import net.minecraft.src.*;

public class WorldGenOreSurface implements IWorldGenerator
{
	int Min;
	int Max;
	public WorldGenOreSurface(int min, int max)
	{
		Min = min;
		Max = max;
	}

	@Override
	public void generate(Random rand, int chunkX, int chunkZ, World world,
			IChunkProvider chunkGenerator, IChunkProvider chunkProvider) 
	{
		int height = Min-Max;
        //============Copper
        createOre(TFCBlocks.terraOre.blockID, 0,new int[]{TFCBlocks.terraStoneIgEx.blockID,-1,Block.sandStone.blockID,-1},//IgEx and Sandstone, veins
                /*rarity*/35,/*veinSize*/20,/*veinAmt*/15,/*height*/height,/*diameter*/40,/*vDensity*/30,/*hDensity*/40,         world, rand, chunkX, chunkZ, Min, Max);

        //============Cassiterite
        createOre(TFCBlocks.terraOre.blockID, 5,new int[]{TFCBlocks.terraStoneIgIn.blockID,0},//Granite Veins
                /*rarity*/20,/*veinSize*/15,/*veinAmt*/20,/*height*/height,/*diameter*/40,/*vDensity*/30,/*hDensity*/50,         world, rand, chunkX, chunkZ, Min, Max);

        //============Cassiterite2
        createOre(TFCBlocks.terraOre.blockID, 5,new int[]{TFCBlocks.terraStoneIgEx.blockID,-1},//IgEx Veins
                /*rarity*/20,/*veinSize*/10,/*veinAmt*/15,/*height*/height,/*diameter*/40,/*vDensity*/30,/*hDensity*/60,         world, rand, chunkX, chunkZ, Min, Max);

        //============Bismuthinite
        createOre(TFCBlocks.terraOre.blockID, 7,new int[]{TFCBlocks.terraStoneIgIn.blockID,-1,TFCBlocks.terraStoneSed.blockID,-1},//Granite Veins
                /*rarity*/25,/*veinSize*/10,/*veinAmt*/25,/*height*/height,/*diameter*/40,/*vDensity*/30,/*hDensity*/60,         world, rand, chunkX, chunkZ, Min, Max);

        //============Sphalerite
        createOre(TFCBlocks.terraOre.blockID, 12,new int[]{TFCBlocks.terraStoneMM.blockID,-1},//mm, veins
                /*rarity*/20,/*veinSize*/10,/*veinAmt*/18,/*height*/height,/*diameter*/40,/*vDensity*/60,/*hDensity*/40,         world, rand, chunkX, chunkZ, Min, Max);
		
	}
	
	private static void createOre(int i, int j, int[] Layers, int rarity, int veinSize, 
            int veinAmount, int height, int diameter, int vDensity, int hDensity,World world, Random rand, int chunkX, int chunkZ, int min, int max)
    {
        for(int n = 0; n < Layers.length/2;)
        {
            DataLayer rockLayer1 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 0);
            DataLayer rockLayer2 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 1);
            DataLayer rockLayer3 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 2);
            if((rockLayer1.data1 == Layers[n] && (rockLayer1.data2 == Layers[n+1] || Layers[n+1] == -1)) || 
                    (rockLayer2.data1 == Layers[n] && (rockLayer2.data2 == Layers[n+1] || Layers[n+1] == -1)) ||
                    (rockLayer3.data1 == Layers[n] && (rockLayer3.data2 == Layers[n+1] || Layers[n+1] == -1)))
            {
                new WorldGenMinableTFC(i, j,Layers[n],Layers[n+1],rarity,veinSize,veinAmount,height,diameter,vDensity,hDensity).generate(
                        world, rand, chunkX, chunkZ, min, max);
            }
            n+=2;
        }
    }

    private static void createOreVein(int i, int j, int[] Layers, int rarity, int veinSize, 
            int veinAmount, int height, int diameter, int vDensity, int hDensity,World world, Random rand, int chunkX, int chunkZ, int min, int max)
    {
        for(int n = 0; n < Layers.length/2;)
        {
        	DataLayer rockLayer1 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 0);
            DataLayer rockLayer2 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 1);
            DataLayer rockLayer3 = ((TFCWorldChunkManager)world.getWorldChunkManager()).getRockLayerAt(chunkX, chunkZ, 2);
            if((rockLayer1.data1 == Layers[n] && (rockLayer1.data2 == Layers[n+1] || Layers[n+1] == -1)) || 
                    (rockLayer2.data1 == Layers[n] && (rockLayer2.data2 == Layers[n+1] || Layers[n+1] == -1)) ||
                    (rockLayer3.data1 == Layers[n] && (rockLayer3.data2 == Layers[n+1] || Layers[n+1] == -1)))
            {
                new WorldGenMinableTFC(i, j,Layers[n],Layers[n+1],rarity,veinSize,veinAmount,height,diameter,vDensity,hDensity).generateVein(
                        world, rand, chunkX, chunkZ, min, max);
            }
            n+=2;
        }
    }
}