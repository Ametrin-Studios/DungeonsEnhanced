package com.barion.dungeons_enhanced.world.structure.prefabs.utils;

import com.barion.dungeons_enhanced.DEUtil;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.SimpleWeightedRandomList;
import org.jetbrains.annotations.NotNull;

public class DEStructureTemplates {
    private final SimpleWeightedRandomList<Template> templates;

    public DEStructureTemplates(@NotNull final Builder builder) {this(builder.buildList());}
    public DEStructureTemplates(@NotNull final SimpleWeightedRandomList<Template> templates){
        if(templates.isEmpty()) {
            throw new IllegalArgumentException("The Structure Template builder is empty");
        }
        this.templates = templates;
    }

    public Template getRandom(RandomSource random) {return templates.getRandomValue(random).get();}

    public static class Template {
        public final ResourceLocation Resource;
        public final int yOffset;
        public Template(final ResourceLocation resource, final int offset){
            Resource = resource;
            yOffset = offset;
        }
    }

    public static class Builder{
        private final SimpleWeightedRandomList.Builder<Template> pieces;
        private int yOffset = 0;
        private int weight = 1;

        public Builder(){
            pieces = SimpleWeightedRandomList.builder();
        }

        public Builder weight(int weight){
            this.weight = weight;
            return this;
        }
        public Builder yOffset(int yOffset){
            this.yOffset = yOffset;
            return this;
        }

        public Builder add(String resource){
            pieces.add(new Template(DEUtil.location(resource), yOffset), weight);
            return this;
        }

        @NotNull
        private SimpleWeightedRandomList<Template> buildList() {
            return pieces.build();
        }

        public DEStructureTemplates build() {return new DEStructureTemplates(this);}
    }
}