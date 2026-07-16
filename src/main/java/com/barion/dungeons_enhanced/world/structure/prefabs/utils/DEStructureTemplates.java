package com.barion.dungeons_enhanced.world.structure.prefabs.utils;

import com.barion.dungeons_enhanced.DungeonsEnhanced;
import net.minecraft.resources.Identifier;
import net.minecraft.util.RandomSource;
import net.minecraft.util.random.WeightedList;
import org.jetbrains.annotations.NotNull;

public final class DEStructureTemplates {
    private final WeightedList<Template> templates;

    public DEStructureTemplates(@NotNull final Builder builder) {
        this(builder.buildList());
    }

    public DEStructureTemplates(@NotNull final WeightedList<Template> templates) {
        if (templates.isEmpty()) {
            throw new IllegalArgumentException("The Structure Template builder is empty");
        }
        this.templates = templates;
    }

    public Template getRandom(RandomSource random) {
        return templates.getRandomOrThrow(random);
    }

    public record Template(Identifier Resource, int yOffset){}

    public static class Builder {
        private final WeightedList.Builder<Template> pieces;
        private int yOffset = 0;
        private int weight = 1;

        public Builder() {
            pieces = WeightedList.builder();
        }

        public Builder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public Builder yOffset(int yOffset) {
            this.yOffset = yOffset;
            return this;
        }

        public Builder add(String resource) {
            pieces.add(new Template(DungeonsEnhanced.locate(resource), yOffset), weight);
            return this;
        }

        @NotNull
        private WeightedList<Template> buildList() {
            return pieces.build();
        }

        public DEStructureTemplates build() {
            return new DEStructureTemplates(this);
        }
    }
}