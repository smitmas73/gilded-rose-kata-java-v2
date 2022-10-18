import com.google.common.collect.ImmutableMap;
import data.BackstageTicketItem;
import data.BrieItem;
import data.ConjuredItem;
import data.DefaultItem;
import data.Item;
import data.LegendaryItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@AllArgsConstructor
@Slf4j
@Getter
public class GildedRose {
    private List<Item> items;

    private static final Map<String, Class<? extends Item>> SPECIAL_CLASSES = ImmutableMap.of(
            "Aged Brie", BrieItem.class,
            "Backstage passes", BackstageTicketItem.class,
            "Sulfuras", LegendaryItem.class,
            "Conjured", ConjuredItem.class
    );

    public void updateQuality() {
        List<Item> specialItems = items.stream()
                .filter(item -> itemNameInSpecialClasses(item.getName()))
                .flatMap(item -> SPECIAL_CLASSES.entrySet()
                        .stream()
                        .filter(Objects::nonNull)
                        .filter(entry -> item.getName().contains(entry.getKey()))
                        .map(Map.Entry::getValue)
                        .map(clazz -> castItemSuperClass(clazz, item)))
                .collect(Collectors.toList());

        List<Item> normalItems = items.stream()
                .filter(item -> !itemNameInSpecialClasses(item.getName()))
                .map(item -> castItemSuperClass(DefaultItem.class, item))
                .collect(Collectors.toList());

        items = Stream.concat(specialItems.stream(), normalItems.stream())
                .collect(Collectors.toList());

        items.forEach(Item::updateItemQuality);
    }

    private boolean itemNameInSpecialClasses(String name) {
        return SPECIAL_CLASSES.entrySet()
                .stream()
                .anyMatch(entry -> name.contains(entry.getKey()));
    }

    private Item castItemSuperClass(Class<? extends Item> specialItemClass, Item item) {
        try {
            return specialItemClass.getConstructor(String.class, Integer.class, Integer.class)
                    .newInstance(item.getName(), item.getSellIn(), item.getQuality());
        } catch (NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            log.error("Could not initialize target item | {}", item.getName(), e);
            return item;
        }
    }
}
