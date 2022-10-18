package data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrieItem extends Item {
    public BrieItem(String name, Integer quality, Integer daysToExpiration) {
        super(name, quality, daysToExpiration);
    }

    @Override
    public void updateItemQuality() {
        Integer quality = getQuality();
        Integer sellIn = getSellIn();
        log.info("Initial Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), sellIn, quality);

        quality++;
        sellIn--;

        if (sellIn.compareTo(0) < 0) {
            quality++;
        }

        if (quality.compareTo(50) >= 0) {
            setQuality(50);
        } else {
            setQuality(quality);
        }

        setSellIn(sellIn);

        log.info("Updated Item Attributes: | Name: {} | SellIn: {} | Quality: {} |", getName(), getSellIn(), getQuality());
    }
}
