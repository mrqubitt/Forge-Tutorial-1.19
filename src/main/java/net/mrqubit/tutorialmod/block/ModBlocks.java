package net.mrqubit.tutorialmod.block;

import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.mrqubit.tutorialmod.TutorialMod;
import net.mrqubit.tutorialmod.block.custom.JumpyBlock;
import net.mrqubit.tutorialmod.block.custom.ZirconLampBlock;
import net.mrqubit.tutorialmod.item.ModCreativeModeTab;
import net.mrqubit.tutorialmod.item.ModItems;

import java.util.function.Supplier;

public class ModBlocks {

    //We create the deferred register here.
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, TutorialMod.MOD_ID);






    //Here we define an object to register as a block.
    public static final RegistryObject<Block> ZIRCON_BLOCK = registerBlock("zircon_block", () -> new Block(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> ZIRCON_ORE = registerBlock("zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops() , UniformInt.of(3,7)), ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> DEEPSLATE_ZIRCON_ORE = registerBlock("deepslate_zircon_ore", () -> new DropExperienceBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops() , UniformInt.of(3,7)), ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> JUMPY_BLOCK = registerBlock("jumpy_block", () -> new JumpyBlock(BlockBehaviour.Properties.of(Material.STONE).strength(6f).requiresCorrectToolForDrops()), ModCreativeModeTab.TUTORIAL_TAB);
    public static final RegistryObject<Block> ZIRCON_LAMP = registerBlock("zircon_lamp", () -> new ZirconLampBlock(BlockBehaviour.Properties.of(Material.GLASS).strength(3f).lightLevel(state -> state.getValue(ZirconLampBlock.LIT) ? 15:0)), ModCreativeModeTab.TUTORIAL_TAB);
    //--------------------------------------------------






    //Here we define an object to register as everything that extends to block.
    private static <T extends Block>RegistryObject<T> registerBlock(String name, Supplier<T> block, CreativeModeTab tab){
        RegistryObject<T> toReturn = BLOCKS.register(name,block);
        registerBlockItem(name,toReturn,tab);
        return toReturn;
    }

    //Here we define an object to register as Item. But we used name block and tab variables to pass on to other methods.
    private static <T extends  Block> RegistryObject<Item> registerBlockItem(String name, RegistryObject<T> block, CreativeModeTab tab){
        return ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().tab(tab)));
    }

    //This is the part gives the deferred registeries to the bus to register.
    public static void register(IEventBus eventBus){
        BLOCKS.register(eventBus);
    }
}
