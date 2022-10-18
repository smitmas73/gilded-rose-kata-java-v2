package data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ConjuredItem extends Item {
    public ConjuredItem(String name, Integer quality, Integer daysToExpiration) {
        super(name, quality, daysToExpiration);
    }

    @Override
    public void updateItemQuality() {
        Integer quality = getQuality();
        Integer sellIn = getSellIn();
        log.info("Initial Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), sellIn, quality);

        quality -= 2;
        sellIn--;

        if (sellIn.compareTo(0) < 0) {
            quality -= 2;
        }

        if (quality.compareTo(0) > 0) {
            setQuality(quality);
        } else {
            setQuality(0);
        }

        setSellIn(sellIn);
        log.info("Updated Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), getSellIn(), getQuality());
    }
}
