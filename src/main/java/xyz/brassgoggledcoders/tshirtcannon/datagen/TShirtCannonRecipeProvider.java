package xyz.brassgoggledcoders.tshirtcannon.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.data.ShapedRecipeBuilder;
import net.minecraft.item.Items;
import net.minecraft.item.crafting.Ingredient;
import net.minecraftforge.common.Tags;
import xyz.brassgoggledcoders.tshirtcannon.TShirtCannonContent;
import xyz.brassgoggledcoders.tshirtcannon.item.TShirtCannonItem;

import javax.annotation.Nonnull;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class TShirtCannonRecipeProvider extends RecipeProvider {
    public TShirtCannonRecipeProvider(DataGenerator generatorIn) {
        super(generatorIn);
    }

    @Override
    protected void registerRecipes(@Nonnull Consumer<IFinishedRecipe> consumer) {
        registerTShirtCannon(TShirtCannonContent.STONE_CANNON, Ingredient.fromTag(Tags.Items.COBBLESTONE), consumer);
        registerTShirtCannon(TShirtCannonContent.IRON_CANNON, Ingredient.fromTag(Tags.Items.INGOTS_IRON), consumer);
        registerTShirtCannon(TShirtCannonContent.GOLD_CANNON, Ingredient.fromTag(Tags.Items.INGOTS_GOLD), consumer);
        registerTShirtCannon(TShirtCannonContent.DIAMOND_CANNON, Ingredient.fromTag(Tags.Items.GEMS_DIAMOND), consumer);
    }

    private void registerTShirtCannon(Supplier<TShirtCannonItem> tShirtCannonItem, Ingredient tierItem, Consumer<IFinishedRecipe> consumer) {
        ShapedRecipeBuilder.shapedRecipe(tShirtCannonItem.get())
                .patternLine("TTD")
                .patternLine("  S")
                .key('T', tierItem)
                .key('D', Ingredient.fromItems(Items.DISPENSER))
                .key('S', Tags.Items.RODS_WOODEN)
                .addCriterion("has_item", this.hasItem(Items.DISPENSER))
                .build(consumer);
    }
}
