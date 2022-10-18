package data;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class LegendaryItem extends Item {
    public LegendaryItem(String name, Integer quality, Integer daysToExpiration) {
        super(name, quality, daysToExpiration);
    }

    @Override
    public void updateItemQuality() {
        log.info("Legendary items never has to be sold or decrease in quality");
    }
}
