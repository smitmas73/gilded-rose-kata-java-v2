package data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DefaultItem extends Item {
    public DefaultItem(String name, Integer sellIn, Integer quality) {
        super(name, sellIn, quality);
    }

    @Override
    public void updateItemQuality() {
        Integer quality = getQuality();
        Integer sellIn = getSellIn();
        log.info("Initial Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), sellIn, quality);
        if (quality.compareTo(0) > 0) {
            quality--;
        }

        sellIn--;

        if (sellIn.compareTo(0) < 0) {
            quality--;
        }

        setQuality(quality);
        setSellIn(sellIn);
        log.info("Updated Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), getSellIn(), getQuality());
    }
}
